package com.egangotri.utils

import org.apache.spark.sql.SparkSession

object Util {

  def getSparkSession(): SparkSession = {
    val sparkX = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    sparkX
  }
}
