// Project name
name := "melvi"

// Project version
version := "0.3.0"

// Scala version
scalaVersion := "2.11.4"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}

// Fix bug with MidiSystem under MacOs
connectInput in run := true
fork in run := true

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))

// Add dependency on ScalaFX library from Maven repository
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.20-R6"
