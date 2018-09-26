package com.egangotri.actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object PingPong extends App{

  case class PingMessage(pong:ActorRef)
  case class PongMessage(ping:ActorRef)

  class Ping extends Actor {

    var count = 0
    override def receive = {
    case PingMessage(pong) => {
        count += 1
      if(count < 10){
        println(s"recieved pingMsge: $count")
        pong ! PongMessage(this.self)
       }
      }
      case _ => println("Not Recognized")
    }
  }

  class Pong extends Actor {
    override def receive = {
      case PongMessage(ping) => println("recieved pongMsge"); ping ! PingMessage(self)
      case _ => println("Not Recognized")
    }
  }

  val actorSystem = ActorSystem("systemActor")
  val ping = actorSystem.actorOf(Props(new Ping()), "ping")
  val pong = actorSystem.actorOf(Props(new Pong()), "pong")

  ping ! PingMessage(pong)
}
