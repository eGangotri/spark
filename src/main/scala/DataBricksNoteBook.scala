import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object DataBricjsNoteBook extends App {

//https://databricks-prod-cloudfront.cloud.databricks.com/public/4027ec902e239c93eaaa8714f173bcfc/7634416990070010/2359272641439254/2467177954574115/latest.html
val conf =
  new SparkConf()
    .setAppName("IndicManipulator")
    .setMaster("local[2]")

val sc = new SparkContext(conf)

//scala.io.Source.fromFile("https://raw.githubusercontent.com/eGangotri/egangotri_scala/master/src/main/resources/catalina.2016-04-16.log").getLines.toList

val textFile = sc.textFile("/databricks-datasets/samples/docs/README.md")

val txtAtUrl = "https://raw.githubusercontent.com/eGangotri/egangotri_scala/master/src/main/resources/ipv_dvn.txt"
val ipv = scala.io.Source.fromURL(txtAtUrl).mkString

 // val x: PairRDD
val list = ipv.split(",+|\\s+").filter(!List(",","|","||","-","", "?").contains(_) ).toList.sorted //
val words = sc.parallelize(list)
val wordTuples:RDD[(String,Int)] = words.map(word => word -> 1)
val count = wordTuples.count()

val aggregatedWordTuples = wordTuples.reduceByKey((accumulatedValue: Int, currentValue: Int) => accumulatedValue + currentValue)
val aggCount = aggregatedWordTuples.count()

wordTuples.collect().take(10).foreach(x => println(s"wordTuple: $x"))
aggregatedWordTuples.take(10).foreach(x => println(s"aggregatedWordTuples: $x"))

val sorted = aggregatedWordTuples.sortBy(_._2,false)
sorted.take(10).foreach(x => println(s"sorted: $x"))
// val aggDF = aggregatedWordTuples.toDF("word", "count")


// aggDF.createOrReplaceTempView("wordsCount")
// var dfApiAggDF = aggDF.select("word", "count").where("count > 500").orderBy($"count".desc)
// var wcDF = spark.sql("select * from wordsCount where count > 50")
// var maxDF = spark.sql("select * from wordsCount order by count DESC")
// maxDF.printSchema()
// dfApiAggDF.take(10).foreach(println)

// //
// //wcDF.take(10).foreach(println)
// //aggDF.take(5).foreach(println)
// println(s"count: $count")
// println(s"Unique Count: $aggCount")



}
