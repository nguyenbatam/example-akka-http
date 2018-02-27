package rabbitmq.base

import java.io.{File, FileInputStream}
import java.util.Properties

case class RabbitConfig(host: String, port: Int, user: String, password: String)

case class RabbitReload()

object RabbitBase {
  private val defaultHost = "192.168.9.91"
  private val defaultPort = "5672"
  private val defaultUser = "guest"
  private val defaultPassword = "guest"

  private val keyHost = "host"
  private val keyPort = "port"
  private val keyUser = "user"
  private val keyPassword = "password"

  def loadConfig(configFile: String): RabbitConfig = {
    val prop = new Properties()
    try {
      val file = new File(configFile)
      if (file.exists())
        prop.load(new FileInputStream(configFile))
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
    val host = prop.getProperty(keyHost, defaultHost)
    val port = prop.getProperty(keyPort, defaultPort).toInt
    val user = prop.getProperty(keyUser, defaultUser)
    val password = prop.getProperty(keyPassword, defaultPassword)
    new RabbitConfig(host, port, user, password)
  }
}