package com.egangotri.utils

case class Auction(auctionid: String,
                   bid: Double, bidtime: Double,
                   bidder: String,
                   bidderrate: Integer,
                   openbid: Double,
                   price: Double,
                   item: String,
                   daystolive: Integer)

object Auction {
  def apply(auctionid: String,
            bid: String,
            bidtime: String,
            bidder: String,
            bidderrate: String,
            openbid: String,
            price: String,
            item: String,
            daystolive: String):Auction = {
    Auction(auctionid,
      bid.toDouble,
      bidtime.toDouble,
      bidder,
      bidderrate.toInt,
      openbid.toDouble,
      price.toDouble,
      item,
      daystolive.toInt)
  }

  def apply(csvArray:Array[String]):Auction = {
    Auction(csvArray(0).toString,
      csvArray(1).toString,
      csvArray(2).toString,
      csvArray(3).toString,
      csvArray(4).toString,
      csvArray(5).toString,
      csvArray(6).toString,
      csvArray(7).toString,
      csvArray(8).toString)
  }
}