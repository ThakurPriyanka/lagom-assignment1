package com.knoldus.myuser.api

import play.api.libs.json.{Format, Json}

case class UserInfo(userId: Int, id: Int, title:String, body: String)

  object UserInfo {
    /**
      * Format for converting user messages to and from JSON.
      *
      * This will be picked up by a Lagom implicit conversion from Play's JSON format to Lagom's message serializer.
      */
    implicit val format: Format[UserInfo] = Json.format[UserInfo]
  }

