package com.egangotri

import org.apache.spark.{SparkConf, SparkContext}

object SparkWithDatabricks {
  def main(args: Array[String]): Unit = {

    val conf =
      new SparkConf()
        .setAppName("IndicManipulator")
        .setMaster("spark://ip-10-172-251-0.us-west-2.compute.internal:46364")

    val sc = new SparkContext(conf)

    val listOfInts = Range(1,1000).toList
    sc.parallelize(listOfInts).take(10).foreach(println)
  }
}