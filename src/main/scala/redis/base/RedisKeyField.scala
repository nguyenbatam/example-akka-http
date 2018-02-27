package redis.base

/**
 * Created by tamnb on 7/13/17.
 */
object RedisKeyField {
  val separatorKey = "::"
  val separatorID = "_"
  val separatorCacheID = "-"
  val LONG_MAX_VALUE = 9.223372036854776E18D
  val MAX_USER_VIEW_IN_VIDEO = 5
  val title = "title"
  val id = "id"
  val userId = "user_id"
  val audioId = "audio_id"
  val videoId = "video_id"
  val parentId = "parent_id"
  val timestamp = "timestamp"
  val lastSave = "last_save"
  val name = "name"
  val trunks = "trunks"
  val length = "length"
  val likeCount = "like_count"
  val videoCount = "video_count"
  val storeId = "store_id"
  val messageTags = "message_tags"
  val osId = "os_id"
  val registerId = "register_id"
  val data = "data"
  val status = "status"
  val appStatus = "app_status"
  val totalSent = "total_sent"
  val totalReceived = "total_received"
  val totalError = "total_error"
  val link = "link"
  val videosOfAudio = "db::audio::%s::videos::%s"
  val usersUsingAudio = "db::audio::%s::users"
  val titleAudio = "db::audio::%s::title::%s"
  val uploadedVideoOfUser = "db::users::%s::videos::upload"
  val likedVideosByUser = "db::users::%s::videos::like"
  val followers = "db::users::%s::followers"
  val followingUsers = "db::users::%s::following"
  val likedAudiosByUser = "db::users::%s::audios::like"
  val commentsOfVideo = "db::videos::%s::comments::time"
  val commentInfo = "db::comments::{comment_id}"
  val image = "image"
  val audio = "audio"
  val sub = "sub"
  val md5 = "md5"
  val level = "level"
  val message = "message"
  val content = "content"
  val hotValue = "1"
  val text = "text"
  val priority = "priority"
  val video = "video"
  val description = "description"
  val `type` = "type"
  val trunksInfo = "trunks_info"
  val statusNotify = "status_notify"
  val lastGetNotify = "last_get_notify"
  val avatar = "avatar"
  val gender = "gender"
  val googleApiKey = "googleApiKey"
  val firstGet: Double = -1.5D
  val endData: Double = -4.0D
  val increaseDefault = 1L
  val decreaseDefault: Long = -1L
  val followValue = "1"
  val unfollowValue = "-1"
  val likeValue = 1
  val unlikeValue = 0
  val objectTypeCommentOfVideoValue = 1L
  val objectTypeReplyOfCommentValue = 0L
  val newNotifyValue = "1"
  val TIME_EXPIRE_VIDEO_FOLLOWING = 300
  val lastUserId = "last_user_id"
  val objectId = "object_id"
  val messageType = "message_type"
  val objectDescription = "object_description"
  val numberUser = "number_user"
  val hashTags = "hash_tags"
  val totalUpload = "total_upload"
  val totalView = "total_view"
  val totalLike = "total_like"
  val timeEnd = "time_end"
  val timeStart = "time_start"
  val eventId = "event_id"
  val init = "init"
  val newNotifyFriend = "new_notify_friend"
  val newNotifyPersonal = "new_notify_personal"

  val weightUserTopTrend = "weight_user_top_trend"
  val exp        ="exp"
  val location   ="location"
  val realName   ="real_name"
  val cmnd       ="cmnd"
  val phoneNumber="phone_number"
  val address    ="address"
}
