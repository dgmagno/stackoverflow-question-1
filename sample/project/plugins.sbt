addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.0")

lazy val root = project.in(file(".")).dependsOn(sbtplugin)
lazy val sbtplugin = file("../plugin").getAbsoluteFile.toURI

//addSbtPlugin("foo" % "sbt-plugin" % "1.0-SNAPSHOT")
