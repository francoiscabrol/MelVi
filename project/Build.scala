import sbt.Keys._
import sbt._

object MelviBuild extends Build {

  //////////////////////////////////////////////////////////////////////////////
  // PROJECT INFO
  //////////////////////////////////////////////////////////////////////////////

  val ORGANIZATION = "francoiscabrol"
  val PROJECT_NAME = "melvi"
  val PROJECT_VERSION = "0.3.1"
  val SCALA_VERSION = "2.11.4"

  //////////////////////////////////////////////////////////////////////////////
  // PROJECTS
  //////////////////////////////////////////////////////////////////////////////

  lazy val root = Project(
    id = PROJECT_NAME,
    base = file("."),
    settings = melviSettings
  )

  //////////////////////////////////////////////////////////////////////////////
  // SETTINGS
  //////////////////////////////////////////////////////////////////////////////

  lazy val melviSettings = Project.defaultSettings ++ basicSettings

  lazy val basicSettings = Seq(
    version := PROJECT_VERSION,
    organization := ORGANIZATION,
    scalaVersion := SCALA_VERSION,

    javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint"),

    initialize := {
      val _ = initialize.value
      if (sys.props("java.specification.version") != "1.8")
        sys.error("Java 8 is required for this project.")
    },

    libraryDependencies += ("org.scalafx" %% "scalafx" % "8.0.20-R6"),

    unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar")),

    connectInput in run := true,

    fork in run := true
  )
}
