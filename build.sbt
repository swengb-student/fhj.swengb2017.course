import sbt._
import Dependencies._
import BuildConstants._

lazy val course = (project in file(".")).
  settings(
    organization := org,
    scalaVersion := scalaVer,
    version := buildVer,
    name := "swengb-course",
    libraryDependencies += scalaTest
  )

// ------------------------------------------------------
// lab 01

lazy val lab01_helloworld = (project in file("labs/lab01/helloworld/")).
 settings(
    organization := org,
    scalaVersion := scalaVer,
    version := buildVer,
    name := "lab01-helloworld",
    libraryDependencies += scalaTest,
    fork := true
  )

// END lab01 ----------------------------------------------