package rabbitmq.base

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.event.Logging
import akka.util.Timeout
import rabbitmq.interaction.{EInteract, Message}
import utils.ConfigUtils

import scala.concurrent.duration._

object InteractPublisher {
  implicit val timeout = Timeout(Int.MaxValue milliseconds)
  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher

  val log = Logging(system, getClass)
//  implicit val system = ConfigUtils.system
//  implicit val executor = ConfigUtils.executor
//  val log=ConfigUtils.log

  var publisher: ActorRef = null
  def start() = {
    val rabbitConfig = RabbitBase.loadConfig(ConfigUtils.rabbitConfigFile)
    publisher = system.actorOf(Props(classOf[RabbitPublisher], rabbitConfig))
  }

  def publishEvent(msg: Message) = {
    publisher ! msg
  }

  def main(args: Array[String]): Unit = {

    InteractPublisher.start()
    val msg = new Message
    msg.interaction = EInteract.update_user_info
    msg.content = "test"
    msg.authorActionId = 100
    InteractPublisher.publishEvent(msg)
  }
}
