package controllers

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import play.api.libs.iteratee._
import play.api.libs.concurrent._
import play.api.mvc.WebSocket
import play.api.Play.current
import play.api.mvc.Controller
import play.api.mvc.Action
import models._

object Application extends Controller {

  implicit val timeout = Timeout(10)

  val board = Akka.system.actorOf(Props[Board])

  def index = Action { implicit request =>
    Ok(views.html.index(Poll(Seq(
      Item("A", "Excellent"),
      Item("B", "Good"),
      Item("C", "Bad"),
      Item("D", "F*ck")
    ))))
  }

  def pollSocket(pollId: String) = WebSocket.async { request =>
    val future = board ? Begin()
    future.mapTo[(Iteratee[String, _], Enumerator[String])]
  }

}