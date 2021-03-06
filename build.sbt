val commonSettings = Seq(
  scalaVersion := "2.12.4",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

lazy val root = (project in file("."))
  .aggregate(exercises, answers)
  .settings(commonSettings)
  .settings(
    name := "fpinscala"
  )

lazy val exercises = (project in file("exercises"))
  .settings(commonSettings)
  .settings(
    name := "exercises"

  )

lazy val answers = (project in file("answers"))
  .settings(commonSettings)
  .settings(
    name := "answers"
  )

