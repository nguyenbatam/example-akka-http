package redis

import redis.base.{BaseMasterRedisPool, MuvikRedis}
import utils.ConfigUtils

trait BaseObjectPool{


  protected val defaultPort = 9701
  protected val defaultMasterHost = "192.168.9.91"
  protected val defaultSlaveHosts = "192.168.9.92"
  protected val keyMasterHost = "audio_info.host"
  protected val keySlaveHost = "audio_info.slave"
  protected val keyPort = "audio_info.port"

  protected var _redisPool: BaseMasterRedisPool = null

  def getPoolConnection: BaseMasterRedisPool = {
    if (_redisPool == null) {
      createJedisPool
    }
    _redisPool
  }

  def createJedisPool = synchronized {
    if (_redisPool == null) {
      val _redisMaster = MuvikRedis.creteJediPool(MuvikRedis.loadMasterConfig(ConfigUtils.redisConfigFile, keyMasterHost, keyPort, defaultMasterHost, defaultPort))
      val _redisSlaves = MuvikRedis.creteSlaveJediPools(MuvikRedis.loadSlavesConfig(ConfigUtils.redisConfigFile, keySlaveHost, keyPort, defaultSlaveHosts, defaultPort))
      _redisPool = new BaseMasterRedisPool(_redisMaster, _redisSlaves)
    }
  }
}
