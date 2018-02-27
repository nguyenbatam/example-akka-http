package api.model

import common.EVideoStatus
import common.EVideoStatus.EVideoStatus

/**
  * Created by tamnb on 7/10/17.
  */
case class BaseVideo
(
  var id: String = "",
  var title: String = "",
  var author_id: Long = 0,
  var md5: String = "",
  var description: String = "",
  var cover: String = "",
  var timestamp: Long = 0L,
  var videoStatus :EVideoStatus =EVideoStatus._hidden
)