package redis.base

import java.util

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import redis.clients.jedis.Tuple
import utils.ConfigUtils

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by tamnb on 7/5/17.
  */
object RedisConnector {
  implicit val timeout = Timeout(2 * ConfigUtils.redisTimeout milliseconds)

  def set(redisPool: BaseMasterRedisPool, key: String, value: String)(implicit ec: ExecutionContext): Future[String] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisSet(key, value)).map(r => r.asInstanceOf[String])
  }

  def zrevrank(redisPool: BaseMasterRedisPool, key: String, member: String)(implicit ec: ExecutionContext): Future[Option[Long]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisZRevRank(key, member)).map(r => r.asInstanceOf[Option[Long]])
  }

  def del(redisPool: BaseMasterRedisPool, key: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisDel(key)).map(r => r.asInstanceOf[Long])
  }

  def zrem(redisPool: BaseMasterRedisPool, key: String, member: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisZRem(key, member)).map(r => r.asInstanceOf[Long])
  }

  def smembers(redisPool: BaseMasterRedisPool, key: String)(implicit ec: ExecutionContext): Future[util.List[String]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisSmembers(key)).map(r => r.asInstanceOf[util.List[String]])
  }

  def srandmember(redisPool: BaseMasterRedisPool, key: String, count: Int)(implicit ec: ExecutionContext): Future[util.List[String]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisSrandmember(key, count)).map(r => r.asInstanceOf[util.List[String]])
  }

  def sismember(redisPool: BaseMasterRedisPool, key: String, member: String)(implicit ec: ExecutionContext): Future[Boolean] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisSismember(key, member)).map(r => r.asInstanceOf[Boolean])
  }

  def zcount(redisPool: BaseMasterRedisPool, key: String, min: Double, max: Double)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisZCount(key, min, max)).map(r => r.asInstanceOf[Long])
  }

  def zrevrangeWithScores(redisPool: BaseMasterRedisPool, key: String, start: Int, stop: Int)(implicit ec: ExecutionContext): Future[util.List[Tuple]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisZRevRangeWithScores(key, start, stop)).map(r => r.asInstanceOf[util.List[Tuple]])
  }

  def zrevrangeByScoreWithScores(redisPool: BaseMasterRedisPool, key: String, max: Double, min: Double, offset: Int, length: Int)
                                (implicit ec: ExecutionContext): Future[util.List[Tuple]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisZRevRangeByScoresWithScores(key, max, min, offset, length)).map(r => r.asInstanceOf[util.List[Tuple]])
  }

  def zrevrangeByScore(redisPool: BaseMasterRedisPool, key: String, max: Double, min: Double, offset: Int, length: Int)
                      (implicit ec: ExecutionContext): Future[util.List[Tuple]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisZRevRangeByScore(key, max, min, offset, length)).map(r => r.asInstanceOf[util.List[Tuple]])
  }

  def hset(redisPool: BaseMasterRedisPool, key: String, field: String, value: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisHSet(key, field, value)).map(r => r.asInstanceOf[Long])
  }

  def exists(redisPool: BaseMasterRedisPool, key: String)(implicit ec: ExecutionContext): Future[Boolean] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisExists(key)).map(r => r.asInstanceOf[Boolean])
  }

  def zadd(redisPool: BaseMasterRedisPool, key: String, score: Double, element: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisZadd(key, element, score)).map(r => r.asInstanceOf[Long])
  }

  def zadd(redisPool: BaseMasterRedisPool, key: String, scoreMembers: util.Map[String, java.lang.Double])(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisMultiZadd(key, scoreMembers)).map(r => r.asInstanceOf[Long])
  }

  def zincrby(redisPool: BaseMasterRedisPool, key: String, score: Double, element: String)(implicit ec: ExecutionContext): Future[Double] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisZincr(key, element, score)).map(r => r.asInstanceOf[Long])
  }

  def hget(redisPool: BaseMasterRedisPool, key: String, field: String)(implicit ec: ExecutionContext): Future[Option[String]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisHGet(key, field)).map(r => r.asInstanceOf[Option[String]])
  }

  def scard(redisPool: BaseMasterRedisPool, key: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisScard(key)).map(r => r.asInstanceOf[Long])
  }

  def keys(redisPool: BaseMasterRedisPool, pattern: String)(implicit ec: ExecutionContext): Future[util.List[String]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisKeys(pattern)).map(r => r.asInstanceOf[util.List[String]])
  }

  def hmset(redisPool: BaseMasterRedisPool, key: String, hash: util.Map[String, String])(implicit ec: ExecutionContext): Future[String] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisMultiHset(key, hash)).map(r => r.asInstanceOf[String])
  }

  def get(redisPool: BaseMasterRedisPool, key: String)(implicit ec: ExecutionContext): Future[Option[String]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisGet(key)).map(r => r.asInstanceOf[Option[String]])
  }

  def zrange(redisPool: BaseMasterRedisPool, key: String, start: Int, end: Int)(implicit ec: ExecutionContext): Future[util.List[String]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisZRange(key, start, end)).map(r => r.asInstanceOf[util.List[String]])
  }

  def zrevrange(redisPool: BaseMasterRedisPool, key: String, start: Int, end: Int)(implicit ec: ExecutionContext): Future[util.List[String]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisZRevRange(key, start, end)).map(r => r.asInstanceOf[util.List[String]])
  }

  def zcard(redisPool: BaseMasterRedisPool, key: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisZCard(key)).map(r => r.asInstanceOf[Long])
  }

  def hgetAll(redisPool: BaseMasterRedisPool, key: String)(implicit ec: ExecutionContext): Future[util.Map[String, String]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisHGetAll(key)).map(r => r.asInstanceOf[util.Map[String, String]])
  }

  def hincrBy(redisPool: BaseMasterRedisPool, key: String, field: String, value: Long)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisHincr(key, field, value)).map(r => r.asInstanceOf[Long])
  }

  def lrange(redisPool: BaseMasterRedisPool, key: String, start: Long, end: Long)(implicit ec: ExecutionContext): Future[util.List[String]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisLRange(key, start, end)).map(r => r.asInstanceOf[util.List[String]])
  }


  def lpush(redisPool: BaseMasterRedisPool, key: String, elements: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisLPush(key, elements)).map(r => r.asInstanceOf[Long])
  }


  def lrem(redisPool: BaseMasterRedisPool, key: String, element: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisLrem(key, element)).map(r => r.asInstanceOf[Long])
  }

  def llen(redisPool: BaseMasterRedisPool, key: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisLLen(key)).map(r => r.asInstanceOf[Long])
  }

  def srem(redisPool: BaseMasterRedisPool, key: String, member: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisSrem(key, member)).map(r => r.asInstanceOf[Long])
  }

  def srem(redisPool: BaseMasterRedisPool, key: String, members: Array[String])(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisMultiSrem(key, members)).map(r => r.asInstanceOf[Long])
  }

  def sadd(redisPool: BaseMasterRedisPool, key: String, member: String)(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisSadd(key, member)).map(r => r.asInstanceOf[Long])
  }

  def sadd(redisPool: BaseMasterRedisPool, key: String, members: Array[String])(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisMultiSadd(key, members)).map(r => r.asInstanceOf[Long])
  }

  def hsetByte(redisPool: BaseMasterRedisPool, key: String, field: String, value: Array[Byte])(implicit ec: ExecutionContext): Future[Long] = {
    val jedis = getMasterJedis(redisPool)
    (jedis ? JedisHSetByte(key, field, value)).map(r => r.asInstanceOf[Long])
  }

  def hgetByte(redisPool: BaseMasterRedisPool, key: String, field: String)(implicit ec: ExecutionContext): Future[Option[Array[Byte]]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisHGetByte(key, field)).map(r => r.asInstanceOf[Option[Array[Byte]]])
  }

  def hmget(redisPool: BaseMasterRedisPool, key: String, fields: Array[String])(implicit ec: ExecutionContext): Future[util.List[String]] = {
    val jedis = getSalveJedis(redisPool)
    (jedis ? JedisMultiHGet(key, fields)).map(r => r.asInstanceOf[util.List[String]])
  }

  def getSalveJedis(pool: BaseMasterRedisPool)(implicit ec: ExecutionContext): ActorRef = {
    val slaves = pool.slaves;
    if (slaves.length == 0)
      return getMasterJedis(pool)
    pool.slaveIndex = (pool.slaveIndex + 1) % pool.slaveLength;
    val slavePools = slaves.get(pool.slaveIndex).get
    slavePools.poolIndex = (slavePools.poolIndex + 1) % slavePools.poolLength;
    slavePools.db(slavePools.poolIndex)
  }

  def getMasterJedis(pool: BaseMasterRedisPool)(implicit ec: ExecutionContext): ActorRef = {
    pool.master.poolIndex = (pool.master.poolIndex + 1) % pool.master.poolLength
    pool.master.db(pool.master.poolIndex)
  }
}
