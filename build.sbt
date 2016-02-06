name := """parkme"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.dbunit" % "dbunit" % "2.4.9",
  cache,
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.8.5"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
PlayKeys.externalizeResources := false
