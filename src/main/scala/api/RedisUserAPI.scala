package api

import java.util

import akka.actor.ActorSystem
import common.EUserStatus
import redis.UserInfoRedis
import redis.base.RedisConnector

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by tamnb on 7/6/17.
  */
object RedisUserAPI {

  import api.model.BaseUser
  import redis.base.RedisKey


  def getUserById(userId: Long, data: util.Map[String, String])(implicit system: ActorSystem, ec: ExecutionContext): BaseUser = {
    var user: BaseUser = null
    if ((data != null) && (data.size > 0) && (data.containsKey("name"))) {
      user = new BaseUser
      user.id = userId
      user.name = data.get("name")
      user.accountStatus =EUserStatus.getById(data.getOrDefault("status","0").toInt)
    }
    user
  }

  def getUserById(userId: Long)(implicit system: ActorSystem, ec: ExecutionContext): Future[BaseUser] = {
    var fUser: Future[BaseUser] = null
    if (userId > 0)
      fUser = RedisConnector.hgetAll(UserInfoRedis.getPoolConnection, RedisKey.userInfoHash(userId))
        .map(response => getUserById(userId, response))
    else fUser = Future(null)
    fUser
  }

}
