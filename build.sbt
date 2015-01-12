name := "clustering"

organization := "com.mlh"

version := "0.3"

homepage := Some(url("https://github.com/mhamrah/clustering"))

startYear := Some(2013)

scmInfo := Some(
  ScmInfo(
    url("https://github.com/mhamrah/clustering"),
    "scm:git:https://github.com/mhamrah/clustering.git",
    Some("scm:git:git@github.com:mhamrah/clustering.git")
  )
)

/* scala versions and options */
scalaVersion := "2.11.4"

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation"
  ,"-unchecked"
  ,"-encoding", "UTF-8"
  ,"-Xlint"
  ,"-Yclosure-elim"
  ,"-Yinline"
  ,"-Xverify"
  ,"-feature"
  ,"-language:postfixOps"
)

/* dependencies */
libraryDependencies ++= {
  val slf4j = "1.7.7"
  val logback = "1.1.2"
  val akka = "2.3.8"

  Seq (
    "com.github.nscala-time" %% "nscala-time" % "1.6.0"
    // -- testing --
    , "org.scalatest" %% "scalatest" % "2.2.3" % "test"
    // -- Logging --
    ,"org.slf4j" % "slf4j-api" % slf4j
    ,"ch.qos.logback" % "logback-classic" % logback
    // -- Akka --
    ,"com.typesafe.akka" %% "akka-testkit" % akka % "test"
    ,"com.typesafe.akka" %% "akka-actor" % akka
    ,"com.typesafe.akka" %% "akka-slf4j" % akka
    ,"com.typesafe.akka" %% "akka-cluster" % akka
    // -- json --
    ,"org.scala-lang" % "scalap" % "2.11.4"
    ,"org.json4s" %% "json4s-jackson" % "3.2.11"
  )
}

maintainer := "Michael Hamrah <m@hamrah.com>"

dockerExposedPorts in Docker := Seq(1600)

dockerEntrypoint in Docker := Seq("sh", "-c", "CLUSTER_IP=`/sbin/ifconfig eth0 | grep 'inet addr:' | cut -d: -f2 | awk '{ print $1 }'` bin/clustering $*")

dockerRepository := Some("mhamrah")

enablePlugins(JavaAppPackaging)
