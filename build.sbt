name := "SparkStreamingTool"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.0"
  exclude("org.spark-project.spark",
    "org.apache.spark.unused.UnusedStubClass")
)

test in assembly := {}

assemblyJarName in assembly := "AutoMessagePublisher.jar"

assemblyMergeStrategy in assembly := {
//  case PathList("org", "spark", xs @ _*) => MergeStrategy.last
  case "org/apache/spark/unused/UnusedStubClass.class" => MergeStrategy.last
  case x => val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("priv.Lumino.SparkStreamingTool.AutoMessageSender")

    