package redis

/**
 * Created by tamnb on 7/5/17.
 */
object TopListRedis extends BaseObjectPool {

  override protected val defaultPort = 9708
  override protected val defaultMasterHost = "192.168.9.91"
  override protected val defaultSlaveHosts = "192.168.9.91"
  override protected val keyMasterHost = "top_list.host"
  override protected val keySlaveHost = "top_list.slave"
  override protected val keyPort = "top_list.port"

}
