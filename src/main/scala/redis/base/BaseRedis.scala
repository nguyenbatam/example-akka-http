package redis.base

import java.io.FileInputStream
import java.util.Properties

import akka.actor.{ActorRef, ActorSystem, Props}
import utils.ConfigUtils

import scala.collection.mutable

/**
 * Created by tamnb on 7/4/17.
 */
case class BaseRedisConfig(host: String, port: Int,name:String)

case class RedisPoolModel(db: Seq[ActorRef], config: BaseRedisConfig){
  var poolIndex = 0
  var poolLength = db.length
}

case class BaseMasterRedisPool(master: RedisPoolModel, slaves: mutable.MutableList[RedisPoolModel]) {
  var slaveIndex = 0;
  var slaveLength = slaves.length;
}

object MuvikRedis {

  implicit val system  = ActorSystem("redis")
  implicit val executor = system.dispatcher

  def loadMasterConfig(configFile: String, keyHost: String, keyPort: String,
    defaultHost: String, defaultPort: Int): BaseRedisConfig = {
    val prop = new Properties()
    try {
      prop.load(new FileInputStream(configFile))
    } catch {
      case e: Exception => e.printStackTrace()
    }

    val port = prop.getProperty(keyPort, defaultPort.toString).toInt
    val host = prop.getProperty(keyHost, defaultHost)
    new BaseRedisConfig(host, port,"master")
  }

  def loadSlavesConfig(configFile: String, keySlaves: String, keyPort: String,
    defaultSlaves: String, defaultPort: Int): mutable.MutableList[BaseRedisConfig] = {
    val prop = new Properties()
    try {
      prop.load(new FileInputStream(configFile))
    } catch {
      case e: Exception => e.printStackTrace()
    }
    val port = new Integer(prop.getProperty(keyPort, defaultPort.toString))
    val slaves = prop.getProperty(keySlaves, defaultSlaves)

    val list: mutable.MutableList[BaseRedisConfig] = new mutable.MutableList[BaseRedisConfig]
    slaves.split(",").foreach(
      slave => list += (new BaseRedisConfig(slave, port,"slave"))
    )
    list
  }

  def creteSlaveJediPools(redisConfigs: mutable.MutableList[BaseRedisConfig]): mutable.MutableList[RedisPoolModel] = {
    val slaveModels = new mutable.MutableList[RedisPoolModel]
    redisConfigs.foreach(
      redisConfig => {
        slaveModels += (creteJediPool(redisConfig))
      }
    )
    slaveModels
  }

  def creteJediPool(redisConfig: BaseRedisConfig): RedisPoolModel = {
    if (redisConfig == null) return null;
    println("try create connect to " + redisConfig.host + " : " + redisConfig.port + " & name = " + redisConfig.name)
    val jedisPool = new Array[ActorRef](ConfigUtils.redisMaxPool)
    for (i <- 0 to jedisPool.length - 1) {
        jedisPool(i) = system.actorOf(Props(classOf[JedisActor], redisConfig,i))
    }
    new RedisPoolModel(jedisPool, redisConfig)
  }
}
