organization := "foo"

name := "sample"

version := "1.0-SNAPSHOT"

lazy val root = project.in(file("."))
	.enablePlugins(PlayJava)