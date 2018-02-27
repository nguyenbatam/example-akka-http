package http

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import cache.CacheCleanActor
import com.typesafe.config.ConfigFactory
import http.app._
import rabbitmq.base.InteractPublisher
import rabbitmq.subcribers.MuvikCacheSub
import utils.ConfigUtils

import scala.concurrent.duration._

object MainHttp {
  implicit val system=ActorSystem("MainHttp")
  implicit val ec =system.dispatcher
  implicit val materializer = ActorMaterializer()

  def main(args: Array[String]): Unit = {

    InteractPublisher.start()

    val config = ConfigFactory.load(ConfigUtils.akkaConfigFile)
    val property = System.getProperties
    val httpPort = property.getProperty("http_port", "8877").toInt
    println(" http listen in " + httpPort)

    val cacheSub = system.actorOf(Props(classOf[MuvikCacheSub], this.getClass.getSimpleName + "_" + httpPort))
    val cacheCleanActor = system.actorOf(Props(classOf[CacheCleanActor]))

    val listRouters = VideoRouter.routes ~ UserRouter.routes

    Http().bindAndHandle(listRouters, "0.0.0.0", httpPort)
    system.scheduler.schedule(30 minutes, 30 minutes, cacheCleanActor, "clean_cache")
  }
}

