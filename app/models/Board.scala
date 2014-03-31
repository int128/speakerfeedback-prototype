package models

import akka.actor.Actor
import play.api.libs.iteratee._
import play.api.libs.concurrent.Execution.Implicits._

case class Begin()
case class End()
case class Broadcast(text: String)

class Board extends Actor {

  val (out, channel) = Concurrent.broadcast[String]

  def receive = {
    case Begin() =>
      val in = Iteratee.foreach[String] { text =>
        self ! Broadcast(text)
      }.map { _ =>
        self ! End()
      }
      channel.push("hello")
      sender ! (in, out)

    case End() =>
      channel.push("bye")

    case Broadcast(text) =>
      channel.push(text)
  }

}
