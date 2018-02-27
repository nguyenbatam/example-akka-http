lazy val akkaHttpVersion = "10.0.10"
lazy val akkaVersion    = "2.5.6"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.example",
      scalaVersion    := "2.12.2"
    )),
    name := "muvik-services",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor"         % akkaVersion,
      "com.typesafe.akka" %% "akka-testkit"       % akkaVersion % Test,
      "com.typesafe.akka" %% "akka-http"          % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"        % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j"         % akkaVersion,

      "org.scalatest"     %% "scalatest"          % "3.0.1"         % Test,
      "redis.clients"     % "jedis"     % "2.9.0",
      "org.json"          % "json"      % "20170516",
      "net.arnx"          % "jsonic"    % "1.3.10",
      "org.nutz"          % "ssdb4j"    % "9.4",
      "com.rabbitmq"      % "amqp-client" % "4.2.0",
      "ch.qos.logback"    % "logback-classic" % "1.2.3",
      "com.google.protobuf" % "protobuf-java" % "3.3.0"
    ),
    mainClass in assembly := Some("http.MainHttp"),
    assemblyJarName in assembly := "example-akka-http-dependencies.jar",
    exportJars :=true
  )
