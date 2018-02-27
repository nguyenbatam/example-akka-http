package api.model

import common.EUserStatus
import common.EUserStatus.EUserStatus

/**
 * Created by tamnb on 7/10/17.
 */
case class BaseUser(
  var id: Long = 0L,
  var name: String = "",
  var accountStatus:EUserStatus = EUserStatus._blacklist
)
