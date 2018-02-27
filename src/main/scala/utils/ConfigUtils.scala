package utils

/**
  * Created by tamnb on 7/5/17.
  */

import akka.http.scaladsl.model.HttpResponse
import akka.util.ByteString
import com.x_keam.protobuff.model.PResponse.PResPonseInfo
import com.xkeam.database.util.HttpResponceCode

object ConfigUtils {

  var redisTimeout = 1000
  var ssdbTimeout = 1000
  var redisMaxPool = 10
  var ssdbMaxPool = 10
  var redisConfigFile = "redis.properties"
  var ssdbConfigFile = "ssdb.properties"
  var rabbitConfigFile = "rabbit.properties"
  var mysqlConfigFile = "mysql.properties"
  var akkaConfigFile = "src/main/resources/application.conf"
  val byteStringDefault = ByteString("")
  val byteString0 = ByteString("0")
  val optionByteString0 = Option(ByteString("0"))
  val optionString = Option("")

  var limitCacheVideo = 500000
  var limitCacheUser = 300000

  val defaultHttpResponseSuccess = HttpResponse(entity = PResPonseInfo.newBuilder()
    .setResponseCode(HttpResponceCode.SUCCESS)
    .build().toByteArray)


  val defaultHttpResponseServerError = HttpResponse(entity = PResPonseInfo.newBuilder()
    .setResponseCode(HttpResponceCode.SERVER_ERROR)
    .build().toByteArray)


}
