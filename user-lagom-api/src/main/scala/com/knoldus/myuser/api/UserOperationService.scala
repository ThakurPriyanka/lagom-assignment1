package com.knoldus.myuser.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}


trait UserOperationService extends Service {

  /**
    * Example: curl http://localhost:9000/api/hello/Alice
    */
  def insert(): ServiceCall[User, String]

  def getDetail(id: Int): ServiceCall[NotUsed, User]

  def getUpdate(id: Int): ServiceCall[User, String]

  def deleteDetail(id: Int): ServiceCall[NotUsed, String]

  def testUser(): ServiceCall[NotUsed, UserInfo]

  override final def descriptor = {
    import Service._
    // @formatter:off
    named("user-lagom")
      .withCalls(
        restCall(Method.POST, "/api/user", insert _),
        restCall(Method.GET, "/api/user/info/:id", getDetail _),
        restCall(Method.PUT, "/api/user/update/:id", getUpdate _),
        restCall(Method.DELETE, "/api/user/delete/:id", deleteDetail _),
        pathCall( "/api/external", testUser _)
      )
    // @formatter:on
  }
}



