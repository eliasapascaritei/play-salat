package controllers

import play.api._
import play.api.mvc._
import models._
import se.radley.plugin.salat._
import com.mongodb.casbah.Imports._
import com.novus.salat._

object Application extends Controller {

  def list() = Action {
    val users = UserDAO.all.toList
    Ok(views.html.list(users))
  }

  def listByCountry(country: String) = Action {
    val users = UserDAO.findByCountry(country).toList
    Ok(views.html.list(users))
  }

  def view(id: ObjectId) = Action {
    UserDAO.findOneByID(id).map( user =>
      Ok(views.html.user(user))
    ).getOrElse(NotFound)
  }

  def create(username: String) = Action {
    val user = User(
      username = username,
      password = "1234"
    )
    UserDAO.save(user)
    Ok(views.html.user(user))
  }

}
