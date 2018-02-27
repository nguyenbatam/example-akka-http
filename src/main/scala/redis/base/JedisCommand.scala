package redis.base

import java.util

/**  Key -- Value */
//case class JedisGet(key: String)

case class JedisGet(key: String)

case class JedisSet(key: String, value: String)

case class JedisDel(key: String)

case class JedisExists(key: String)

case class JedisKeys(pattern: String)

/** Set */
case class JedisSmembers(key: String)

case class JedisScard(key: String)

case class JedisSrandmember(key: String,count: Int)

case class JedisSismember(key: String,member:String)

case class JedisSadd(key: String,member:String)

case class JedisMultiSadd(key: String,members:Array[String])

case class JedisSrem(key: String,member:String)

case class JedisMultiSrem(key: String,members:Array[String])

// Hash Map
//check

// get
case class JedisHGet(key: String, field: String)

case class JedisHGetByte(key: String, field: String)

case class JedisMultiHGet(key: String, fields: Array[String])

case class JedisHGetAll(key: String)

//case class JedisHLen(key: String)

//case class JedisHKeys(key: String)

//set
case class JedisHSet(key: String, field: String, value: String)

case class JedisHSetByte(key: String, field: String, value: Array[Byte])

case class JedisMultiHset(key: String, map: util.Map[String, String])

// change
case class JedisHincr(key: String, field: String, value: Long)

//delete

//case class JedisHdel(key: String, field: String)

//case class JedisMultiHdel(key: String, fields: Array[String])

// Zset
// check
//get
case class JedisZCard(key: String)

case class JedisZCount(key: String,min: Double , max:Double)

case class JedisZRange(key: String, start: Int, end: Int)

case class JedisZRevRange(key: String, start: Int, end: Int)


case class JedisZRevRank(key: String, element: String)


case class JedisZRevRangeWithScores(key: String, start: Int, end: Int)


case class JedisZRevRangeByScoresWithScores(key: String, max: Double, min: Double, offset: Int, length: Int)

case class JedisZRevRangeByScore(key: String, max: Double, min: Double, offset: Int, length: Int)


//set
case class JedisZadd(key: String, element: String, score: Double)

case class JedisMultiZadd(key: String, map: util.Map[String, java.lang.Double])

//change
case class JedisZincr(key: String, element: String, value: Double)

//delete
case class JedisZRem(key: String, element: String)

//case class JedisMultiZRem(key: String, elements: Array[String])

//List

case class JedisLPush(key: String,value:String)


case class JedisLRange(key: String,start:Long ,end:Long)

case class JedisLrem(key: String,value:String)

case class JedisLLen(key: String)
