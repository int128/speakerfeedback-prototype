package models

import akka.actor.Actor
import play.api.libs.iteratee._
import play.api.libs.concurrent.Execution.Implicits._

case class Begin(poll: Poll)
case class Broadcast(item: Item)

class Board extends Actor {

  val (out, channel) = Concurrent.broadcast[String]

  def receive = {
    case Begin(poll) =>
      val in = Iteratee.foreach[String] { itemId =>
        poll.items.find(_.id == itemId) match {
          case Some(item) =>
            item.countUp()
            self ! Broadcast(item)

          case None =>
        }
      }

      poll.items.foreach(item => self ! Broadcast(item))
      sender ! (in, out)

    case Broadcast(item) =>
      channel.push(s"${item.id},${item.count}")
  }

}
