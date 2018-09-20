package com.egangotri.dataFrame
import com.egangotri.utils.{Auction, Util}
import org.apache.spark.{SparkConf, SparkContext}


//https://mapr.com/blog/using-apache-spark-dataframes-processing-tabular-data/
object ReadCSV extends App{

  val conf = new SparkConf().setAppName("readCSV").setMaster("local[2]").set("spark.executor.memory", "1g")
  val sc = new SparkContext(conf)
  val csvRDD = sc.textFile("src/main/resources/auction.csv")

  val sparkX = Util.getSparkSession
  import sparkX.implicits._

  val auction = csvRDD.map(_.split(",")).map(col => Auction(col)).toDF()
  println("***")
  auction.show()

  println("***")
  auction.printSchema()
  println("*** count: " + auction.select("auctionid").distinct.count)
  println("***")
  auction.groupBy("auctionid", "item").count.show
  //auction.groupBy("item", "auctionid").agg(min("count"), avg("count"),max("count")).show
  val highprice= auction.filter("price > 100")
  println("***")
  highprice.show()
  println("***")
  auction.createOrReplaceTempView("auction")
  // SQL statements can be run
  // How many  bids per auction?
  val results =sparkX.sql("SELECT auctionid, item,  count(bid) FROM auction GROUP BY auctionid, item")
  // display dataframe in a tabular format
  println("***")
  results.show()
  // auctionid  item    count
  // 3016429446 palm    10
  // 8211851222 xbox    28\. . .

  val results2 =sparkX.sql("SELECT auctionid, MAX(price) FROM auction  GROUP BY item,auctionid")
  results2.show()
  println(" results2.show()***")

  /* println(auction.first())
   println(auction.collect().foreach(println))*/
  Thread.sleep(100000)
}
