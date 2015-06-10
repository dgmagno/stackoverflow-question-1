organization := "foo"

name := "sbt-plugin"

version := "1.0-SNAPSHOT"

sbtPlugin := true

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.0")

lazy val root = project.in(file(".")).dependsOn(core)
lazy val core = file("../core").getAbsoluteFile.toURI

//libraryDependencies += "foo" %% "core" % "1.0-SNAPSHOT"
