package redis.base



/**
  * Created by tamnb on 7/6/17.
  */
object RedisKey {
  def topVideoZSet: String = "top:video"

  def videoInfoHash(videoId: String): String = {
    val sb = new StringBuilder("v:")
    sb.append(videoId)
    sb.toString
  }

  def userInfoHash(userId: Long): String = {
    val sb = new StringBuilder("u:")
    sb.append(userId)
    sb.toString
  }

  def userLikeVideoSet(videoId:String) = {
    val sb = new StringBuilder("v:")
    sb.append(videoId)
    sb.append(":l")
    sb.toString
  }
}
