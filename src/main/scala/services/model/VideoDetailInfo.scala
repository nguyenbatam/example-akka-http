package services.model

import api.model.{BaseUser, BaseVideo}
case class VideoDetailInfo
(
  var like_count: Long = 0L,
  var view_count: Long = 0L,
  var baseVideo: BaseVideo = null,
  var baseUser: BaseUser = null
)

/*
 * Location:
 * /home/tamnb/Desktop/xkeam-service-db-0.5-jar-with-dependencies.jar!/com/xkeam
 * /database/service/model/VideoDetailInfo.class Java compiler version: 7 (51.0)
 * JD-Core Version: 0.7.1
 */ 