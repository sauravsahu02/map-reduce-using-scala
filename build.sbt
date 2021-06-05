ThisBuild / scalaVersion := "2.13.3"

lazy val root = (project in file(".")).
  settings(
    name := "MapReduce Using Scala and Hadoop",
    version := "1.0",
    mainClass in Compile := Some("com.mapreduce.hadoop.WordCountUsingHadoop"),
    mainClass in assembly := Some("com.mapreduce.hadoop.WordCountUsingHadoop")
  )

libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-core" % "1.2.1",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

assemblyJarName in assembly := "WordCountUsingHadoop-1.0.jar"

// META-INF discarding
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}