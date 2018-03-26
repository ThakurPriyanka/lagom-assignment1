package com.knoldus.myuser.api


import play.api.libs.json.{Format, Json}

import scala.collection.mutable.Map

case class User(id: Int, name: String)

object User {
  /**
    * Format for converting greeting messages to and from JSON.
    *
    * This will be picked up by a Lagom implicit conversion from Play's JSON format to Lagom's message serializer.
    */
  implicit val format: Format[User] = Json.format[User]
}


object UserData {
  val userData =  Map.empty[Int, User]
  val user1 = User(1,"priyanka")
  val user2 = User(2,"neha")
  val user3 = User(3,"himanshu")
  userData += (user1.id->user1)
  userData += (user2.id->user2)
  userData += (user3.id->user3)
}
