package rabbitmq.subcribers

import akka.actor.{Actor, Props}
import akka.event.Logging
import cache.CacheManager
import rabbitmq.base.{RabbitBase, RabbitSubcriber}
import rabbitmq.interaction._
import utils.ConfigUtils

class MuvikCacheSub(queueName: String) extends Actor {
  val log = Logging(context.system, getClass)
  val exchangeNames = Array[String](
    EInteract.update_user_info.name(),
    EInteract.update_video_info.name(),
    EInteract.like_video.name()
  )

  //  def start(queueName: String) = {
  val config = RabbitBase.loadConfig(ConfigUtils.rabbitConfigFile)
  context.system.actorOf(Props(classOf[RabbitSubcriber], self, config, queueName, false, exchangeNames))

  //  }

  override def receive = {
    case message: Message => {
      try {
        message.interaction match {
          /**
           * user
           */
          case EInteract.update_user_info => {
            CacheManager.updateBaseUserById(message.authorActionId)
          }

          /**
           * video
           */
          case EInteract.update_video_info => {
            val info = message.asInstanceOf[VideoInteract]
            CacheManager.updateBaseVideoById(info.videoId)
          }

          case EInteract.like_video => {
            val info = message.asInstanceOf[VideoInteract]
            CacheManager.likeCount.remove(info.videoId)
          }

          case _ =>
        }
      } catch {
        case ex: Exception =>
          log.error(ex, ex.getMessage)
      }
    }
  }
}
