package com.ctv.x_keam.jetty.util

import java.util

import akka.actor.ActorSystem
import akka.event.Logging
import com.x_keam.protobuff.model._
import com.xkeam.database.util.HttpResponceCode
import services.model._

object ProtoBuffConverterUtil {
  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher
  val log = Logging(system, getClass)

  def encode(info: ListVideosResponse): PListVideosResponseOuterClass.PListVideosResponse = {
    if (info == null)
      return PListVideosResponseOuterClass.PListVideosResponse.newBuilder
        .setResponseCode(HttpResponceCode.SERVER_ERROR)
        .build
    var pInfo = PListVideosResponseOuterClass.PListVideosResponse.newBuilder()
    try {
      val pVideoInfos = new util.ArrayList[PVideoInfoOuterClass.PVideoInfo]
      var i = 0
      for (i <- 0 to info.videos.size - 1) {
        try {
          val video = info.videos(i)
          if ((video != null) && (video.baseVideo != null)) {
            val pVideoInfo = PVideoInfoOuterClass.PVideoInfo.newBuilder()
            pVideoInfo.setId(video.baseVideo.id)
              .setImage(video.baseVideo.cover)
              .setMd5(video.baseVideo.md5)
              .setTitle(video.baseVideo.title)
              .setLikeCount(video.like_count)
              .setViewCount(video.view_count)
              .setTimeStamp(video.baseVideo.timestamp)

            if (video.baseUser != null) {
              var pUser = PUserInfoOuterClass.PUserInfo.newBuilder()
              pUser.setId(video.baseUser.id)
                .setName(video.baseUser.name)
              pVideoInfo.setUser(pUser)
            }

            pVideoInfos.add(pVideoInfo.build())
          }
        } catch {
          case e: Exception =>
            log.error(e, e.getMessage)
        }
      }
      pInfo.addAllVideos(pVideoInfos)
        .setLast(info.last)
        .setResponseCode(HttpResponceCode.SUCCESS)
    } catch {
      case e: Exception => {
        log.error(e, e.getMessage)
        pInfo.setResponseCode(HttpResponceCode.SERVER_ERROR)
      }
    }
    pInfo.build()
  }


}

/*
 * Location:
 * /home/tamnb/Desktop/xkeam-service-jetty-ctv-2.0.war!/WEB-INF/classes/com/ctv/
 * x_keam/jetty/util/ProtoBuffConverterUtil.class Java compiler version: 7
 * (51.0) JD-Core Version: 0.7.1
 */ 