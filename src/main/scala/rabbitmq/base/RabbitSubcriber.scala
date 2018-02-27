package rabbitmq.base

import akka.actor.{Actor, ActorRef}
import com.rabbitmq.client._
import net.arnx.jsonic.JSON
import rabbitmq.interaction.{EInteract, _}

case class RabbitSubcriber(subcriber: ActorRef, config: RabbitConfig, queueName: String, durable: Boolean = true, exchangeNames: Array[String]) extends Actor {
  var factory: ConnectionFactory = null
  var connection: Connection = null
  var channel: Channel = null

  start

  def start = {
    try {
      println(config)
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
        println(" try declare queue = " + queueName + " sub exchanges = " + exchangeNames)
        channel = connection.createChannel
        val queue = channel.queueDeclare(queueName, durable, false, false, null).getQueue
        for (i <- 0 to exchangeNames.length - 1) {
          channel.queueBind(queue, exchangeNames(i), "")
        }
        val consumer = new DefaultConsumer(channel) {
          override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
            try {
              val data = new String(body, "UTF-8")
              val message = parserMessage(envelope.getExchange, data)
              if (message != null)
                subcriber ! message
            } catch {
              case e: Exception => {
                e.printStackTrace()
                if (!connection.isOpen)
                  self ! RabbitReload
              }
            }
          }
        }
        if ((channel != null) && (consumer != null)) {
          channel.basicConsume(queueName, true, consumer)
        }
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
  }

  //  var mapInteract = mutable.HashMap[String, EInteract]()
  //  for (interact <- EInteract.values()) {
  //    mapInteract.put(interact.name(), interact)
  //  }
  def parserMessage(exchange: String, data: String): Message = {
    var message: Message = null
    try {
      message = JSON.decode(data, classOf[Message])
      message.interaction match {
        case EInteract.update_user_info => message = JSON.decode(data, classOf[Message])
        case EInteract.view_video => message = JSON.decode(data, classOf[VideoInteract])
        case EInteract.like_video => message = JSON.decode(data, classOf[VideoInteract])
        case EInteract.update_video_info => message = JSON.decode(data, classOf[VideoInteract])

        // catch the default with a variable so you can print it
        case _ => message = JSON.decode(data, classOf[Message])
      }
    } catch {
      case ex: Exception => {
        println(" error when processing message = " + exchange + " \t " + data)
        ex.printStackTrace()
      }
    }
    message
  }
}
