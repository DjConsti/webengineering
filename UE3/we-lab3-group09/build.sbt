name := """we-lab3-group09"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
javaJdbc,
javaCore,
javaJpa,
"org.hibernate" % "hibernate-entitymanager" % "4.3.1.Final"
)


fork in run := true