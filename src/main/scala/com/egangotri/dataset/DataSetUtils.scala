package com.egangotri.dataset

import org.apache.spark.sql.SparkSession

object DataSetUtils {

  def getSparkSession = {
    val sparkX = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config("spark.master", "local[2]")
      .getOrCreate()
    sparkX
  }
}
