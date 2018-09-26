package com.egangotri.actor

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}

object ActorWithPoisonPill extends App {

  class ActorWithPoisonPill extends Actor {
    override def receive: Receive = {
      case s:String => println(s"recieved a String ${this.self.path} $s")
    }
    override def preStart { println("kenny: preStart") }
    override def postStop { println("kenny: postStop") }
    override def preRestart(reason: Throwable, message: Option[Any]) { println("kenny: preRestart") }
    override def postRestart(reason: Throwable) { println("kenny: postRestart") }
  }

  val system = ActorSystem("WithPP")
  val actor = system.actorOf(Props(new ActorWithPoisonPill()), "actorWithPP")
  actor ! "Hi"
  actor ! "hello"

  val aWPP = system.actorSelection("akka://WithPP/user/actorWithPP")
  aWPP ! "hello again"
  actor ! PoisonPill
  actor ! "---"
}
