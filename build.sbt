
name := """sig-bk"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.12"
