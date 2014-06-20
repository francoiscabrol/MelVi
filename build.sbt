// Project name
name := "melvi"

// Project version
version := "0.2.0"

// Scala version
scalaVersion := "2.10.4"

// Add dependency on ScalaFX library from Maven repository
libraryDependencies += "org.scalafx" %% "scalafx" % "1.0.0-R8"

// Add dependency on JavaFX library based on JAVA_HOME variable
unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))
