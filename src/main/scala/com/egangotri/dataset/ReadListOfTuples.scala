package com.egangotri.dataset

object ReadListOfTuples extends App {
  var tuples = List((3,"C++"),(2,"Java"),(2,"Perl"),(1,"Abacus"),(3,"Python"),(1,"Django"), (4,"Scala"))

  val sparkX = DataSetUtils.getSparkSession
  import sparkX.implicits._

 val tupleDS = tuples.toDS()
 tupleDS.groupByKey(x=>x._1).mapGroups((k,v) => (k,v.foldLeft("")((acc,p) => acc + ", " + p._2 ))).sort($"_1").show()

  tupleDS.groupByKey(x=>x._1).keys.show()

  tupleDS.groupByKey(x=>x._1).mapValues(y=> y._2).reduceGroups((acc, str) => acc+str).show()
    //.mapGroups((k,v) => (k,v.foldLeft("")((acc,p) => acc + ", " + p._2 ))).sort($"_1").show()
}
