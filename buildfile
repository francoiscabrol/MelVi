# Generated by Buildr 1.4.17, change to your liking
Buildr.settings.build['scala.version'] = "2.10.4"
# Buildr.settings.build['scalac.incremental'] = true
require 'buildr/scala'

Project.local_task :run

# Version number for this release
VERSION_NUMBER = "0.2.0"
# Group identifier for your projects
GROUP = "MelVi"
COPYRIGHT = "François Cabrol"

# Dependencies
LIB = "lib"
LIBJAMU  = "libjamu-0.5.0.jar"
JAVAFX   = "jfxrt.jar"
SCALAFX  = "scalafx_2.10-1.0.0-R8.jar"
SCALALIB = "scala-library.jar"

# Main class
MAIN_CLASS = 'com.cabrol.francois.melvi.Test'
 
# Specify Maven 2.0 remote repositories here, like this:
repositories.remote << "http://repo1.maven.org/maven2"

desc "The Melvi project"
define "MelVi" do
  project.version = VERSION_NUMBER
  manifest['Class-Path'] = SCALALIB + " " + LIBJAMU + " " + SCALAFX + " " + JAVAFX
  manifest['Main-Class'] = MAIN_CLASS
  project.group = GROUP
  manifest["Implementation-Vendor"] = COPYRIGHT
  compile.with Dir[LIB + "/*"]
  # FIXME: why it doesn't works by double click
  package(:jar)
  task :run => :compile do
    system 'scala -cp target/classes:Localism/target/classes:' + LIB + '/* ' + MAIN_CLASS
  end
end