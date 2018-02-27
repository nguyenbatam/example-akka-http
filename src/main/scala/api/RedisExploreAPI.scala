package api

import akka.actor.ActorSystem
import redis.TopListRedis
import redis.base.{RedisConnector, RedisKey}

import scala.concurrent.{ExecutionContext, Future}

/**
 * Created by tamnb on 7/20/17.
 */
object RedisExploreAPI {
  def getHotVideoList(startIndex: Int, end: Int)(implicit system:ActorSystem, ec:ExecutionContext ): Future[Array[String]] = {
    RedisConnector.zrevrange(TopListRedis.getPoolConnection, RedisKey.topVideoZSet, startIndex, end)
      .map(response => {
        val strings = new Array[String](response.size())
        for (i <- 0 to response.size() - 1) {
          strings(i) = response.get(i)
        }
        strings
      })

  }
}
