package http.app

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import cache.CacheManager
import com.ctv.x_keam.jetty.util.ProtoBuffConverterUtil
import common.EVideoStatus
import rabbitmq.base.InteractPublisher
import rabbitmq.interaction.{EInteract, VideoInteract}
import services.VideoService
import utils.ConfigUtils

import scala.concurrent.Promise
import scala.util.{Failure, Success}

object VideoRouter {
  implicit val system = ActorSystem("VideoRoute")
  implicit val ec=system.dispatcher
  val log = Logging(system, getClass)

  val _getTopVideo = path("ranking") {
    parameter('last.as[Double] ? -2.0, 'length.as[Int] ? 20,  'viewId.as[Long] ? 0) {
      (last, length, viewId) => {
        ctx => {
          log.info(ctx.request.toString())
          val fPromise = Promise[HttpResponse]
          VideoService.getTopVideo(last, length, viewId).onComplete {
            case Success(response) => {
                fPromise.success(HttpResponse(entity = ProtoBuffConverterUtil.encode(response).toByteArray))
            }
            case Failure(ex) => {
              log.error(ex, ctx.request.toString())
              fPromise.success(ConfigUtils.defaultHttpResponseServerError)
            }
          }
          ctx.complete(fPromise.future)
        }
      }
    }
  }


  val _actionPlayVideo = path(Segment / "playing") {
    (videoId) => {
      parameter('viewId.as[Long] ? 0,'ref.as[String] ? "") {
        (viewId,ref) => {
          ctx => {
            log.info(ctx.request.toString())
            CacheManager.getBaseVideoById(videoId)
              .onComplete {
                case Success(baseVideo) => {
                  if ((baseVideo != null) && (baseVideo.videoStatus != EVideoStatus._delete)) {
                    var interact = new VideoInteract
                    interact.authorActionId = viewId
                    interact.timeStamp = System.currentTimeMillis
                    interact.interaction = EInteract.view_video
                    interact.videoId = baseVideo.id
                    interact.content = ref
                    InteractPublisher.publishEvent(interact)
                  }
                }
                case Failure(ex) => {
                  log.error(ex, ex.getMessage)
                }
              }
            ctx.complete(ConfigUtils.defaultHttpResponseSuccess)
          }
        }
      }
    }
  }



  val routes: Route = {
    pathPrefix("example" / "v1.0" / "videos") {
        get {
            _getTopVideo
        } ~
          post {
            _actionPlayVideo
          }
    }
  }
}
