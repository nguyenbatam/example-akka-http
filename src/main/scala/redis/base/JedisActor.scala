package redis.base

import akka.actor.Actor
import akka.actor.Status.Failure
import akka.event.Logging
import redis.clients.jedis.Tuple
import utils.ConfigUtils

class JedisActor(jedisConfig: BaseRedisConfig, index: Int) extends Actor {
  val log = Logging(context.system, getClass)
  val configInfo = jedisConfig.toString + "\t" + index
  //class jedisActor extends Actor{
  var jedis: MuvikJedis = null
  createConnect

  def createConnect() = {
    try {
      jedis = new MuvikJedis(jedisConfig.host, jedisConfig.port, ConfigUtils.redisTimeout)
      if (jedis.ping().equals("PONG"))
        println(" create success jedis = " + configInfo)
      else
        println(" create fail jedis = " + configInfo)
    } catch {
      case e: Exception => {
        log.error(e, "error when create jedis = " + configInfo)
        try {
          jedis.close()
        } catch {
          case e: Exception =>
        }
        jedis = null
      }
    }

  }

  def receive = {
    // ********************* Key --Value
    case info: JedisKeys =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.keys(info.pattern)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }

      }

    case info: JedisGet =>
      try {
        if(jedis==null) createConnect
        sender() ! Option(jedis.get(info.key))
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    case info: JedisSet =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.set(info.key, info.value)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    case info: JedisDel =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.del(info.key)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    case info: JedisExists =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.exists(info.key)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    // ******************* Set
    // get
    case info: JedisSmembers =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.smembers(info.key).asInstanceOf[MuvikSetFromList[String]].toList
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    case info: JedisScard =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.scard(info.key)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    case info: JedisSrandmember =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.srandmember(info.key, info.count)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    case info: JedisSismember =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.sismember(info.key, info.member)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    case info: JedisMultiSrem =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.sremArray(info.key, info.members)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    case info: JedisSrem =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.srem(info.key, info.member)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    case info: JedisSadd =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.sadd(info.key, info.member)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    case info: JedisMultiSadd =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.saddArray(info.key, info.members)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    //set
    //check
    // ********************* Hash Map
    //Check

    //Get
    case info: JedisHGet =>
      try {
        if(jedis==null) createConnect
        sender() ! Option(jedis.hget(info.key, info.field))
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    case info: JedisHGetByte =>
      try {
        if(jedis==null) createConnect
        sender() ! Option(jedis.hget(info.key.getBytes, info.field.getBytes))
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    //    case info: JedisHLen =>
    //      try {
    //        sender() ! jedis.hlen(info.key)
    //      } catch {
    //        case e: Exception => {
    //          sender() ! Failure(e)
    //          log.error("error when execute " + info + "\t" + configInfo)
    //          checkAndReConnect
    //        }
    //      }
    case info: JedisHGetAll =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.hgetAll(info.key)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    case info: JedisMultiHGet =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.hmgetArray(info.key, info.fields)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    //Set
    case info: JedisHSet =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.hset(info.key, info.field, info.value)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }

    case info: JedisHSetByte =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.hset(info.key.getBytes, info.field.getBytes, info.value)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    case info: JedisMultiHset => try {
      if(jedis==null) createConnect
      sender() ! jedis.hmset(info.key, info.map)
    } catch {
      case e: Exception => {
        sender() ! Failure(e)
        log.error("error when execute " + info + "\t" + configInfo)
        checkAndReConnect
      }
    }
    //Change
    case info: JedisHincr =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.hincrBy(info.key, info.field, info.value)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    //Delete
    //    case info: JedisHdel =>
    //      try {
    //        jedis.hdel(info.key, info.field)
    //      } catch {
    //        case e: Exception => {
    //          sender() ! Failure(e)
    //          log.error("error when execute " + info + "\t" + configInfo)
    //          checkAndReConnect
    //        }
    //      }
    //    case info: JedisMultiHdel =>
    //      try {
    //        jedis.hdel(info.key, info.fields)
    //      } catch {
    //        case e: Exception => {
    //          sender() ! Failure(e)
    //          log.error("error when execute " + info + "\t" + configInfo)
    //          checkAndReConnect
    //        }
    //      }
    //*********************Zset
    //Check
    //Get
    //    case info: JedisZscore =>
    //      try {
    //        sender() ! jedis.zscore(info.key, info.element)
    //      } catch {
    //        case e: Exception => {
    //          sender() ! Failure(e)
    //          log.error("error when execute " + info + "\t" + configInfo)
    //          checkAndReConnect
    //        }
    //      }

    //    case info: JedisMultiZscore =>
    //      try {
    //        val tx = jedis.multi()
    //        for (i <- 0 to info.elements - 1)
    //          tx.zscore(info.key, info.elements(i))
    //        val result = tx.exec()
    //
    //        val list = new Array[Double]
    //        for (i <- 0 to info.elements - 1)
    //          list(i) = result(i).asInstanceOf[Double]
    //        sender() ! list
    //      } catch {
    //        case e: Exception => {
    //          sender() ! Failure(e)
    //          log.error("error when execute " + info + "\t" + configInfo)
    //          checkAndReConnect
    //        }
    //      }
    case info: JedisZCount =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.zcount(info.key, info.min, info.max)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    case info: JedisZRange =>
      try {
        if(jedis==null) createConnect
        val response = jedis.zrange(info.key, info.start, info.end)
        sender() ! response.asInstanceOf[MuvikSetFromList[String]].toList
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    case info: JedisZRevRange =>
      try {
        if(jedis==null) createConnect
        val response = jedis.zrevrange(info.key, info.start, info.end)
        sender() ! response.asInstanceOf[MuvikSetFromList[String]].toList
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    //    case info: JedisZRangeWithScores =>
    //      try {
    //        val response = jedis.zrangeWithScores(info.key, info.start, info.end - info.start + 1)
    //        sender() ! response.asInstanceOf[MuvikSetFromList].toList
    //      } catch {
    //        case e: Exception => {
    //          sender() ! Failure(e)
    //          log.error("error when execute " + info + "\t" + configInfo)
    //          checkAndReConnect
    //        }
    //
    //      }
    case info: JedisZRevRangeWithScores =>
      try {
        if(jedis==null) createConnect
        val response = jedis.zrevrangeWithScores(info.key, info.start, info.end)
        sender() ! response.asInstanceOf[MuvikSetFromList[Tuple]].toList
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    case info: JedisZCard => try {
      if(jedis==null) createConnect
      sender() ! jedis.zcard(info.key)
    } catch {
      case e: Exception => {
        sender() ! Failure(e)
        log.error("error when execute " + info + "\t" + configInfo)
        checkAndReConnect
      }
    }

    //    case info: JedisZRank =>
    //      try {
    //        val result = jedis.zrank(info.key, info.element)
    //        sender() ! result
    //      } catch {
    //        case e: Exception => {
    //          sender() ! Failure(e)
    //          log.error("error when execute " + info + "\t" + configInfo)
    //          checkAndReConnect
    //        }
    //      }
    case info: JedisZRevRank =>
      try {
        if(jedis==null) createConnect
        sender() ! Option(jedis.zrevrank(info.key, info.element))
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    case info: JedisZRevRangeByScoresWithScores =>
      try {
        if(jedis==null) createConnect
        val response = jedis.zrevrangeByScoreWithScores(info.key, info.max, info.min, info.offset, info.length)
        sender() ! response.asInstanceOf[MuvikSetFromList[Tuple]].toList
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    //    case info: JedisZRangeByScoresWithScores =>
    //      try {
    //        val response = jedis.zrangeByScoreWithScores(info.key, 0, info.start, info.end, info.limit)
    //        sender() ! response.asInstanceOf[MuvikSetFromList].toList
    //      } catch {
    //        case e: Exception => {
    //          sender() ! Failure(e)
    //          log.error("error when execute " + info + "\t" + configInfo)
    //          checkAndReConnect
    //        }
    //      }

    //    case info: JedisZRangeByScore =>
    //      try {
    //        val response = jedis.zrangeByScore(info.key, info.min, info.max, info.offset, info.length)
    //        sender() ! response.asInstanceOf[MuvikSetFromList].toList
    //      } catch {
    //        case e: Exception => {
    //          sender() ! Failure(e)
    //          log.error("error when execute " + info + "\t" + configInfo)
    //          checkAndReConnect
    //        }
    //      }

    case info: JedisZRevRangeByScore =>
      try {
        if(jedis==null) createConnect
        val response = jedis.zrevrangeByScore(info.key, info.max, info.min, info.offset, info.length)
        sender() ! response.asInstanceOf[MuvikSetFromList[String]].toList
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    //Set
    case info: JedisZadd =>
      try {
        if(jedis==null) createConnect
        sender() ! jedis.zadd(info.key, info.score, info.element)
      } catch {
        case e: Exception => {
          sender() ! Failure(e)
          log.error("error when execute " + info + "\t" + configInfo)
          checkAndReConnect
        }
      }
    case info: JedisMultiZadd => try {
      if(jedis==null) createConnect
      sender() ! jedis.zadd(info.key, info.map)
    } catch {
      case e: Exception => {
        sender() ! Failure(e)
        log.error("error when execute " + info + "\t" + configInfo)
        checkAndReConnect
      }
    }
    //Change
    case info: JedisZincr => try {
      if(jedis==null) createConnect
      sender() ! jedis.zincrby(info.key, info.value, info.element)
    } catch {
      case e: Exception => {
        sender() ! Failure(e)
        log.error("error when execute " + info + "\t" + configInfo)
        checkAndReConnect
      }
    }
    //Delete
    case info: JedisZRem => try {
      if(jedis==null) createConnect
      sender() ! jedis.zrem(info.key, info.element)
    } catch {
      case e: Exception => {
        sender() ! Failure(e)
        log.error("error when execute " + info + "\t" + configInfo)
        checkAndReConnect
      }
    }
    //    case info: JedisMultiZRem => try {
    //      sender() ! jedis.zrem(info.key, info.elements)
    //    } catch {
    //      case e: Exception => {
    //        sender() ! Failure(e)
    //        log.error("error when execute " + info + "\t" + configInfo)
    //        checkAndReConnect
    //      }
    //    }

    /** **********************************  List */

    case info: JedisLRange => try {
      if(jedis==null) createConnect
      sender() ! jedis.lrange(info.key, info.start, info.end)
    } catch {
      case e: Exception => {
        sender() ! Failure(e)
        log.error("error when execute " + info + "\t" + configInfo)
        checkAndReConnect
      }
    }


    case info: JedisLPush => try {
      if(jedis==null) createConnect
      sender() ! jedis.lpush(info.key, info.value)
    } catch {
      case e: Exception => {
        sender() ! Failure(e)
        log.error("error when execute " + info + "\t" + configInfo)
        checkAndReConnect
      }
    }

    case info: JedisLrem => try {
      if(jedis==null) createConnect()
      sender() ! jedis.lrem(info.key, 0, info.value)
    } catch {
      case e: Exception => {
        sender() ! Failure(e)
        log.error("error when execute " + info + "\t" + configInfo)
        checkAndReConnect
      }
    }


    case info: JedisLLen => try {
      if(jedis==null) createConnect()
      sender() ! jedis.llen(info.key)
    } catch {
      case e: Exception => {
        sender() ! Failure(e)
        log.error("error when execute " + info + "\t" + configInfo)
        checkAndReConnect
      }
    }

    case _ => log.error("received unknown message = " + configInfo)
  }

  def checkAndReConnect() = {
    var check = false
    try {
      if (jedis != null)
        check = jedis.ping().equals("PONG")
    } catch {
      case e: Exception => {
        log.error(e, "error when ping jedis = " + configInfo)
        check = false
      }
    }
    if (!check) {
      closeAndReConnect()
    }
  }

  def closeAndReConnect() = {
    try {
      if (jedis != null)
        jedis.close()
    } catch {
      case e: Exception =>
        log.error(e, "error when close jedis = " + configInfo)
    }
    try {
      jedis = new MuvikJedis(jedisConfig.host, jedisConfig.port, ConfigUtils.redisTimeout)
      if (jedis.ping().equals("PONG"))
        log.error(" reconnect success jedis = " + configInfo)
      else
        log.error(" reconnect fail jedis = " + configInfo)
    } catch {
      case e: Exception => {
        log.error(e, "error when create jedis = " + configInfo)
        try {
          jedis.close()
        } catch {
          case e: Exception =>
        }
        jedis = null
      }
    }
  }
}

