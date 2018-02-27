package cache

import akka.actor.ActorSystem
import api._
import api.model._
import utils.ConfigUtils

import scala.concurrent.Future

/**
  * Created by tamnb on 7/14/17.
  */
object CacheManager {

  val _1_minutes = 60 * 1000
  val _12_hours = 12 * 60 * _1_minutes
  val _5_minutes = 5 * _1_minutes

  implicit val system = ActorSystem("CacheManager")
  implicit val executor = system.dispatcher

  // and a Cache for its result type
  val user: Cache[BaseUser] = LruCache.apply(maxCapacity = ConfigUtils.limitCacheUser, timeToLive = _12_hours)
  val video: Cache[BaseVideo] = LruCache.apply(maxCapacity = ConfigUtils.limitCacheVideo, timeToLive = _12_hours)
  val likeCount: Cache[Long] = LruCache.apply(maxCapacity = ConfigUtils.limitCacheVideo, timeToLive = _12_hours)
  val viewCount: Cache[Long] = LruCache.apply(maxCapacity = ConfigUtils.limitCacheVideo, timeToLive = _5_minutes)


  def getLikeCountByVideoId[T](key: T): Future[Long] = likeCount(key) {
    RedisVideoAPI.getLikeCountsByVideoId(key.asInstanceOf[String])
  }

  def getViewCountByVideoId[T](key: T): Future[Long] = viewCount(key) {
    RedisVideoAPI.getViewCountsByVideoId(key.asInstanceOf[String])
  }

  def getBaseUserById[T](key: T): Future[BaseUser] = user(key) {
    RedisUserAPI.getUserById(key.asInstanceOf[Long])
  }

  def updateBaseUserById(userId: Long): Unit = {
    val baseUser = RedisUserAPI.getUserById(userId)
    user.putIfPresent(userId, baseUser)
  }


  def getBaseVideoById[T](key: T): Future[BaseVideo] = video(key) {
    RedisVideoAPI.getVideoById(key.asInstanceOf[String])
  }

  def updateBaseVideoById(videoId: String): Unit = {
    val baseVideo = RedisVideoAPI.getVideoById(videoId)
    video.putIfPresent(videoId, baseVideo)
  }

}

