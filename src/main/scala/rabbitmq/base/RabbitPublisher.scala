package rabbitmq.base

import akka.actor.Actor
import com.rabbitmq.client._
import rabbitmq.interaction.Message

case class RabbitPublisher(config: RabbitConfig) extends Actor {
  var factory: ConnectionFactory = null
  var connection: Connection = null
  var channel: Channel = null

  start
  def start = {
    try {
      factory = new ConnectionFactory
      factory.setHost(config.host)
      factory.setPort(config.port)
      factory.setUsername(config.user)
      factory.setPassword(config.password)
      connection = factory.newConnection
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
    if (connection != null)
      try {
        channel = connection.createChannel
      } catch {
        case e: Exception => {
          e.printStackTrace()
          try {
            connection.close()
          } catch {
            case ex: Exception =>
              ex.printStackTrace()
          }
        }
      }
  }

  override def receive: Receive = {
    case RabbitReload => {
      if (connection != null)
        try {
          connection.close()
        } catch {
          case ex: Exception =>
            ex.printStackTrace()
        }
      start
    }
    case message: Message =>
      try {
        channel.basicPublish(message.interaction.name, "", new AMQP.BasicProperties().builder().build(), message.toString.getBytes)
      } catch {
        case ex: Exception => {
          if ((channel == null) || (!channel.isOpen))
            self ! RabbitReload
          ex.printStackTrace()
        }
      }
  }
}
