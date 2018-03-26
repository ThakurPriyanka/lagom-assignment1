package com.knoldus.myuser.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

trait ExternalService extends Service {

      def getUser(): ServiceCall[NotUsed, UserInfo]

      override final def descriptor = {
        import Service._
        // @formatter:off
        named("external-service")
          .withCalls(
            pathCall("/posts/1", getUser _)
          ).withAutoAcl(true)
        // @formatter:on
      }
}
