package com.reagroup.scalaApi

import io.circe._
import org.http4s._
import org.http4s.circe._
import org.http4s.server._
import org.http4s.dsl._

object HelloWorld {
  val service = HttpService {
    case GET -> Root / "diagnose" =>
      Ok(Json.obj("message" -> Json.fromString(s"Ok")))
    case GET -> Root / "hello" =>
      Ok(Json.obj("message" -> Json.fromString(s"Hello World")))
    case req @ POST -> Root / "record" =>{
      println(req)
      println("hola")
      Ok(Json.obj("message" -> Json.fromString(s"RECORD")))
    }
  }
}
