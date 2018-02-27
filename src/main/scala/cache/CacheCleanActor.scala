package cache

import akka.actor.Actor
import akka.event.Logging

class CacheCleanActor extends Actor {
  val log = Logging(context.system, getClass)

  override def receive = {
    case "clean_cache" => {
      log.error("try clean cache")
      try {
        var cleaned = CacheManager.user.limitSize()
        if (cleaned > 0) log.error(" cleaned user " + cleaned + " objects")
        cleaned = CacheManager.video.limitSize()
        if (cleaned > 0) log.error(" cleaned video " + cleaned + " objects")
        cleaned = CacheManager.likeCount.limitSize()
        if (cleaned > 0) log.error(" cleaned likeCount " + cleaned + " objects")
        cleaned = CacheManager.viewCount.limitSize()
        if (cleaned > 0) log.error(" cleaned viewCount " + cleaned + " objects")

      } catch {
        case e: Exception => log.error(e, "error when try clean cache ")
      }
      log.error("clean cache done")
    }
  }
}
