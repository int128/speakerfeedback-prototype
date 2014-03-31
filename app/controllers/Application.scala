package controllers

import play.api.libs.iteratee._
import play.api.mvc.WebSocket
import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object Application extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.index(request))
  }

  def pollSocket(pollId: String) = WebSocket.using[String] { request =>
    val (out, channel) = Concurrent.broadcast[String]

    val in = Iteratee.foreach[String] {
      message => channel.push(message)
    }

    (in, out)
  }

}