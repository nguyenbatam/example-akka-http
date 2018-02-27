package services.model

import scala.collection.mutable

case class ListVideosResponse
(
  var videos: mutable.MutableList[VideoDetailInfo] = null,
  var last: Double = -4.0
)

/*
 * Location:
 * /home/tamnb/Desktop/xkeam-service-db-0.5-jar-with-dependencies.jar!/com/xkeam
 * /database/service/model/ListVideosResponse.class Java compiler version: 7
 * (51.0) JD-Core Version: 0.7.1
 */ 