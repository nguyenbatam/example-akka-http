package http.app

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import cache.CacheManager
import common.EVideoStatus
import services.VideoService
import utils.ConfigUtils

import scala.concurrent.Promise
import scala.util.{Failure, Success}

object UserRouter {
  implicit val system = ActorSystem("UserRoute")
  implicit val ec = system.dispatcher
  val log = Logging(system, getClass)

  val _postRatingLikeVideo = path(LongNumber / "videos" / Segment / "like") {
    (userId, videoId) => {
      ctx => {
        log.info(ctx.request.toString())
        val fPromise = Promise[HttpResponse]
        if ((userId <= 0) || (videoId.length == 0)) {
          fPromise.success(ConfigUtils.defaultHttpResponseSuccess)
        } else CacheManager.getBaseVideoById(videoId)
          .onComplete {
            case Success(baseVideo) =>
              if ((baseVideo == null) || (baseVideo.videoStatus == EVideoStatus._hidden))
                fPromise.success(ConfigUtils.defaultHttpResponseServerError)
              else {
                VideoService.likeVideo(videoId, userId).onComplete {
                  case Success(x) => fPromise.success(ConfigUtils.defaultHttpResponseSuccess)
                  case Failure(ex) => fPromise.failure(ex)
                }
              }
            case Failure(ex) => fPromise.failure(ex)
          }
        ctx.complete(fPromise.future)
      }
    }
  }


  val routes: Route = {
    pathPrefix("example" / "v1.0" / "users") {
        post {
          _postRatingLikeVideo
        }
    }
  }
}
