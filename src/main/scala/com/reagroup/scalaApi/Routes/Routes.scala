package com.reagroup.simpleApi

import cats.effect.Sync
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.EntityDecoder
import io.circe._, io.circe.parser._

object Routes {

  def jokeRoutes[F[_]: Sync](J: Jokes[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "joke" =>
        for {
          joke <- J.get
          resp <- Ok(joke)
        } yield resp
    }
  }

  def helloWorldRoutes[F[_]: Sync](H: HelloWorld[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "diagnose" => Ok("Ok")
      case GET -> Root / "hello" / name =>
        for {
          greeting <- H.hello(HelloWorld.Name(name))
          resp <- Ok(greeting)
        } yield resp
    }
  }

  def recordRoutes[F[_]: Sync](R: Record[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "record" / IntVar(id) =>
        for {
          content <- R.getContent(Record.Id(id))
          resp <- Ok(content)
        } yield resp
      case req @ POST -> Root / "record" =>
        req
          .decode[String] { request =>
            parse(request) match {
              case Right(json) => {
                Ok(request)
              }
              case Left(failure) => BadRequest("invalid json")
            }
          }
    }
  }

}
