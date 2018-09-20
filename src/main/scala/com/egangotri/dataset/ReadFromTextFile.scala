package com.egangotri.dataset

import org.apache.spark.sql.SparkSession

object ReadFromTextFile extends App {

  val sparkX = DataSetUtils.getSparkSession
  import sparkX.implicits._

  case class Person(name: String, age: Int)

  val text = sparkX.read.text("src/main/resources/people.txt")
  text.collect.foreach(println)

  val peopleDF = text.map(_.toString.replaceAll("]", "") split (",")).map(x => Person(x(0), x(1).trim.toInt)).toDF
  peopleDF.take(10).foreach(println)

  peopleDF.createOrReplaceTempView("people")

  val teenagersDF = sparkX.sql("SELECT name, age FROM people WHERE age BETWEEN 13 AND 19")
  teenagersDF.map(teenager => "Name: " + teenager(0)).show()

  val adultDF = sparkX.sql("select name,age from people where age BETWEEN 20 AND 100 ")
  adultDF.map(adult => s"Name(${adult(0)}) age(${adult(1)})").show()

}
