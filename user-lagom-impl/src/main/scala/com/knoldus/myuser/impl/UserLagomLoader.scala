package com.knoldus.myuser.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.knoldus.myuser.api.{ExternalService, UserOperationService}
import com.softwaremill.macwire._

class UserLagomLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new UserLagomApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new UserLagomApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[UserOperationService])
}

abstract class UserLagomApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[UserOperationService](wire[UserOperationServiceImpl])

  // Register the JSON serializer registry
//  override lazy val jsonSerializerRegistry = UserlagomSerializerRegistry

  // Register the User-lagom persistent entity
//  persistentEntityRegistry.register(wire[UserlagomEntity])
lazy val externalService = serviceClient.implement[ExternalService]


}
