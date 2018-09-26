package com.egangotri.dataFrame

import com.egangotri.dataFrame.ReadCSV.sparkX
import com.egangotri.utils.Util

object ReadCSV2 extends App{
  val sparkX = Util.getSparkSession()
  import sparkX.implicits._

  val df = sparkX.read.format("csv").option("header", "false").load("src/main/resources/auction.csv")

  df.printSchema()
  df.show()
}
