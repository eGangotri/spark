package com.egangotri

import java.io.File

import org.apache.commons.io.FileUtils
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    // Create a Scala Spark Context.
    // val conf = new SparkConf().setAppName("wordCount")
    val conf = new SparkConf().setAppName("wordCount").setMaster("local[2]").set("spark.executor.memory", "1g")
    val sc = new SparkContext(conf)
//    System.setProperty("hadoop.home.dir", "C:\\hadoop-common-2.2.0-bin-master")
    System.setProperty("hadoop.home.dir", "C:\\Spark")


    println("hadoop.home.dir:hadoop.home.dir: " + System.getProperty("hadoop.home.dir"))
    try {
      /*      val input = sc.parallelize(List(1, 2, 3, 4))
            println("*****--******" + input.getClass)

            val result = input.map(x => x + 1)
            println("*****--******" + result.getClass)
            result.persist()
            println("*****--******" + result)*/

      // Load our input data.
      // val inputFile = "C:\\Spark\\README.md"
      val inputFile = "C:\\hw\\Study\\sparkAndscala\\bigDataForSkt\\a.txt"
      val input2 = sc.textFile(inputFile)
      // Split it up into words.
      val words = input2.flatMap(line => line.split(" "))
      println(s"----$words")
      words.collect().foreach(println)
      val outputFile = "output"
      rmFile(outputFile)
      words.saveAsTextFile(outputFile)

      /*      val outputFile2 = "output-2"

      var shivaOccurence = words.filter(_.contains("शिव")).map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
      shivaOccurence.saveAsTextFile(outputFile2)
      println("----<<<<<")
      // Transform into pairs and count.
      var counts = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
      println(s"${counts.getClass}")
      counts = counts.sortByKey()

      // Save the word count back out to a text file, causing evaluation.
      val countsOutput = "count-output"
      rmFile(countsOutput)
      counts.saveAsTextFile(countsOutput)
      println("----<<<<<")
    }*/
    }
    catch {
      case e: Throwable => println("Got some other kind of exception" + e.printStackTrace())
    }
    finally {
      println("**finally***")
      sc.stop
    }

  }
  def rmFile(fileName:String) = {
    val file: File = new File(fileName)
    if (file.exists()) {
      println(s" ${file.getName} exists, removing")
      println( FileUtils.deleteQuietly(file) )

    }
    if (file.exists()) {
      println(s"file not removed")
    }
  }


}
