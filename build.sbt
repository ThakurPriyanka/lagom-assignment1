name := "practice-lagom"



organization in ThisBuild := "com.knoldus"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"
lagomServiceLocatorPort in ThisBuild := 10008

lagomServiceGatewayPort in ThisBuild := 9011
lagomUnmanagedServices in ThisBuild := Map("external-service" -> "https://jsonplaceholder.typicode.com")
val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `user-lagom` = (project in file("."))
  .aggregate(`user-lagom-api`, `user-lagom-impl`)

lazy val `user-lagom-api` = (project in file("user-lagom-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `user-lagom-impl` = (project in file("user-lagom-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`user-lagom-api`)

