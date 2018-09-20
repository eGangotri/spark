package com.egangotri

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object DataSetTut extends App {

  val conf = new SparkConf().setAppName("DataSetTut").setMaster("local[2]").set("spark.executor.memory", "1g")
  val sc = new SparkContext(conf)

  val list = List("Dataset", "Spark","Dataframe", "Databricks")
  val rdd = sc.parallelize(list)
  rdd.take(2).foreach(println)


  // Encoders are created for case classes

  val sparkX = SparkSession
    .builder()
    .appName("Spark SQL basic example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()

  import sparkX.implicits._
  case class Person(name: String, age: Long)

  val caseClassDS = Seq(Person("Andy", 32)).toDS()
  caseClassDS.show()
  // For implicit conversions like converting RDDs to DataFrames

    val integerDS = rdd.toDS()
    integerDS.show()

    val dataset = Seq(1, 2, 3).toDS()
    dataset.show()

  // Encoders for most common types are automatically provided by importing spark.implicits._
  val primitiveDS = Seq(1, 2, 3).toDS()
  primitiveDS.map(_ + 1).collect() // Returns: Array(2, 3, 4)

  // DataFrames can be converted to a Dataset by providing a class. Mapping will be done by name
  val path = "src/main/resources/people.json"
  val peopleDS = sparkX.read.json(path).as[Person]
  peopleDS.show()

  /*
    val personDS = Seq(Person("Max", 33), Person("Adam", 32), Person("Muller", 62)).toDS()
    personDS.show()*/
}
