package api

import akka.actor.ActorSystem
import redis.VideoInfoRedis
import redis.base.RedisConnector

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by tamnb on 7/6/17.
  */
object RedisVideoAPI {

  import java.util

  import api.model.BaseVideo
  import common.EVideoStatus
  import redis.base.{RedisKey, RedisKeyField}

  def getVideoById(videoId: String)(implicit system: ActorSystem, ec: ExecutionContext): Future[BaseVideo] = {
    var fResult: Future[BaseVideo] = null
    if (videoId.length == 0)
      fResult = Future(null)
    else
      fResult = RedisConnector.hgetAll(VideoInfoRedis.getPoolConnection, RedisKey.videoInfoHash(videoId))
        .map(response => getVideoById(videoId, response))
    fResult
  }

  def getVideoById(videoId: String, map: util.Map[String, String])(implicit system: ActorSystem, ec: ExecutionContext): BaseVideo = {
    var video= new BaseVideo
    video.id = videoId
    if ((map.size >= 0) && map.containsKey("user_id")) {
      video.author_id = map.getOrDefault("user_id", "0").toLong
      video.description = map.getOrDefault("description_new", "")
      video.timestamp = map.getOrDefault("timestamp", "0").toLong
      video.cover = map.getOrDefault("cover", "")
      video.title = map.getOrDefault("title", "")
      video.md5 = map.getOrDefault("md5", "")
      video.videoStatus = EVideoStatus.getById(map.getOrDefault("status", "0"))
    }
    video
  }

  def getLikeCountsByVideoId(videoId: String)(implicit system: ActorSystem, ec: ExecutionContext): Future[Long] = {
    RedisConnector.scard(VideoInfoRedis.getPoolConnection, RedisKey.userLikeVideoSet(videoId))
  }

  def getViewCountsByVideoId(videoId: String)(implicit system: ActorSystem, ec: ExecutionContext): Future[Long] = {
    RedisConnector.hget(VideoInfoRedis.getPoolConnection, RedisKey.videoInfoHash(videoId), RedisKeyField.totalView)
      .map {
        case response => {
          var view = 0L
          try {
            if ((response != None) && (response.get.length > 0))
              view = response.get.toLong
          } catch {
            case e: Exception =>
          }
          view
        }
      }
  }
}