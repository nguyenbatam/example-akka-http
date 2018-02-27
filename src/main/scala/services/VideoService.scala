package services

import akka.actor.ActorSystem
import akka.event.Logging
import api.RedisExploreAPI
import api.model.BaseVideo
import cache.CacheManager
import common._
import http.app.VideoRouter.getClass
import rabbitmq.base.InteractPublisher
import rabbitmq.interaction._
import redis.VideoInfoRedis
import redis.base.{RedisConnector, RedisKey, RedisKeyField}
import services.model._
import utils.ConfigUtils

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}

object VideoService {
  implicit val system = ActorSystem("VideoService")
  implicit val ec=system.dispatcher
  val log = Logging(system, getClass)

  def getBasicInfoOfVideos(videoIds: Array[String], viewId: Long)(implicit system: ActorSystem, ec: ExecutionContext): Future[mutable.MutableList[VideoDetailInfo]] = {
    var list = mutable.MutableList[Future[VideoDetailInfo]]()
    for (i <- 0 to videoIds.length - 1) {
      val videoId = videoIds(i)
      if ((videoId != null) && (videoId.length > 0)) {
        list += getBasicInfoOfVideo(videoId, viewId).recover {
          case ex: Exception => {
            log.error(ex, "error when get info video Id = " + videoId)
            null
          }
        }
      }
    }
    Future.sequence(list)
  }

  def getBasicInfoOfVideo(videoId: String, viewId: Long)(implicit system: ActorSystem, ec: ExecutionContext): Future[VideoDetailInfo] = {
    CacheManager.getBaseVideoById(videoId).map(
      baseVideo => {
        var videoDetail: Future[VideoDetailInfo] = Future(null)
        if (baseVideo != null)
          videoDetail = getBasicInfoOfVideo(baseVideo, viewId)
        videoDetail
      }
    ).flatMap(a => a)
  }

  def getBasicInfoOfVideo(baseVideo: BaseVideo, viewId: Long)(implicit system: ActorSystem, ec: ExecutionContext): Future[VideoDetailInfo] = {
    val fLikeCount = CacheManager.getLikeCountByVideoId(baseVideo.id).recover {
      case ex: Exception => {
        log.error(ex, "error when count like by video Id =" + baseVideo.id)
        0L
      }
    }
    val fViewCount = CacheManager.getViewCountByVideoId(baseVideo.id).recover {
      case ex: Exception => {
        log.error(ex, "error when count videw by video Id =" + baseVideo.id)
        0L
      }
    }
    var fUser = CacheManager.getBaseUserById(baseVideo.author_id)
    for {
      likeCount <- fLikeCount
      viewCount <- fViewCount
      baseUser <- fUser
    } yield {
      var videoInfo: VideoDetailInfo = null
      var checkHidden = true
      if ((baseUser != null) && (baseVideo.videoStatus != EVideoStatus._hidden)) {
        if (viewId == baseVideo.author_id) {
          checkHidden = false
        } else if ((baseVideo.videoStatus == EVideoStatus._nomarl) && (baseUser.accountStatus == EUserStatus._nomarl))
          checkHidden = false
      }

      if (!checkHidden) {
        val now = System.currentTimeMillis()
        videoInfo = new VideoDetailInfo
        videoInfo.baseVideo = baseVideo
        videoInfo.baseUser = baseUser
        videoInfo.like_count = likeCount
        videoInfo.view_count = viewCount
      }
      videoInfo
    }
  }

  def getTopVideo(last: Double, length: Int, viewId: Long)(implicit system: ActorSystem, ec: ExecutionContext) = {
    var start = 0
    if (last <= RedisKeyField.firstGet) start = 0
    else start = last.toInt
    for {
      videoIds <- RedisExploreAPI.getHotVideoList(start, start + length - 1)
      videoInfos <- getBasicInfoOfVideos(videoIds, viewId)
    } yield {
      val response = new ListVideosResponse
      response.videos = videoInfos
      if (videoIds.size < length)
        response.last = RedisKeyField.endData
      else
        response.last = start + length
      response
    }
  }


  def likeVideo(videoId: String, userId: Long)(implicit system: ActorSystem, ec: ExecutionContext): Future[Boolean] = {
    val timestamp = System.currentTimeMillis
    RedisConnector.sadd(VideoInfoRedis.getPoolConnection, RedisKey.userLikeVideoSet(videoId), userId.toString)
      .map {
        case 1 => {
          CacheManager.likeCount.remove(videoId)
          val interact = new VideoInteract
          interact.authorActionId = userId
          interact.timeStamp = timestamp
          interact.interaction = EInteract.like_video
          interact.videoId = videoId
          InteractPublisher.publishEvent(interact)
          true
        }
        case 0 => true
      }
  }

}
