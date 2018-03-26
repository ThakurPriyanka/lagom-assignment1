package com.knoldus.myuser.impl

import akka.NotUsed
import com.knoldus.myuser.api._
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.{ExecutionContext, Future}

class UserOperationServiceImpl(externalService: ExternalService)(implicit ec: ExecutionContext) extends UserOperationService{

  override def getDetail(id: Int) = ServiceCall { _ =>
    val userData = UserData.userData.get(id)

    // Look up the hello-lagom entity for the given ID.
    userData match {
        case Some(user) => Future.successful(user)
        case None =>  Future.successful(new User(0, "No User"))
      }
  }

 override def insert() = ServiceCall[User, String] { request =>
      val user = new User(request.id, request.name)
    UserData.userData += (request.id->user)
    Future.successful("stored")
  }

  override def getUpdate(id: Int) = ServiceCall[User, String] { request =>
  val oldUser = UserData.userData.get(id)

  val newUser = new User(request.id, request.name)
    UserData.userData -= request.id
    UserData.userData += (request.id->newUser)
    Future.successful("Updated")
  }

  override def deleteDetail(id: Int) = ServiceCall[NotUsed, String] { _ =>
    UserData.userData -= id
    Future.successful("Deleted")
  }

  override def testUser() = ServiceCall[NotUsed, UserInfo] { _ =>
      val result: Future[UserInfo] = externalService.getUser().invoke()
      result.map(response => response)
    }
}
