package com.egangotri

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

object SerializeTester extends App {

  @SerialVersionUID(100L)
  class Stock(val value:Double, val symbol:String) extends Serializable

  val stock = new Stock(120.5,"QWER")
  var fos = new ObjectOutputStream(new FileOutputStream("src/main/resources/ser2.txt"))
  fos.writeObject(stock)
  fos.close

  var fis = new ObjectInputStream(new FileInputStream("src/main/resources/ser2.txt"))
  val stock2 = fis.readObject().asInstanceOf[Stock]
  fis.close()
  println(stock2.value)
  println(stock2.symbol)
}
