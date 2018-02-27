import akka.actor.ActorSystem
import akka.stream._
import utils.ConfigUtils


object Test {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  val MAXIMUM_CAPACITY= 1 << 30
  private def tableSizeFor(c: Int) = {
    var n = c - 1
    n |= n >>> 1
    n |= n >>> 2
    n |= n >>> 4
    n |= n >>> 8
    n |= n >>> 16
    if (n < 0) 1
    else if (n >= MAXIMUM_CAPACITY) MAXIMUM_CAPACITY
    else n + 1
  }
  def main(args: Array[String]): Unit = {
    val initialCapacity=ConfigUtils.limitCacheVideo
    val loadFactor=1.0D


    val size = (1.0 + initialCapacity.toLong / loadFactor).toLong
    val cap = if (size >= MAXIMUM_CAPACITY.toLong) MAXIMUM_CAPACITY
    else tableSizeFor(size.toInt)
    println(cap)
  }
}
