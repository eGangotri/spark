name := "spark"

version := "1.0"

scalaVersion := "2.11.7"

fork := true

//mainClass in Compile := Some("com.egangotri.WordCount")
mainClass in Compile := Some("com.egangotri.WordCount")

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.1"

// additional libraries
libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % "2.11.7",
  "org.scala-lang" % "scala-compiler" % "2.11.7",
  "org.scala-lang" % "scala-reflect" % "2.11.7",
  "jline"  % "jline" % "2.12.1",
  "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.4",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4"

)

// Select which Hadoop version to use
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.2.0"

libraryDependencies += "org.mongodb" % "mongo-java-driver" % "2.11.4"

retrieveManaged := true

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"
// important to use ~= so that any other initializations aren't dropped
// the _ discards the meaningless () value previously assigned to 'initialize'
initialize ~= { _ =>
  System.setProperty( "hadoop.home.dir", "C:\\hadoop-common-2.2.0-bin-master" )
}
