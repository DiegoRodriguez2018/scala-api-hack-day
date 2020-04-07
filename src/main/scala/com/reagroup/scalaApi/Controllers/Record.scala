package com.reagroup.simpleApi

import cats.Applicative
import cats.implicits._
import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe._

trait Record[F[_]]{
  def getContent(n: Record.Id): F[Record.Message]
}

object Record {
  implicit def apply[F[_]](implicit ev: Record[F]): Record[F] = ev

  final case class Id(id: Int) extends AnyVal
  /**
    * More generally you will want to decouple your edge representations from
    * your internal data structures, however this shows how you can
    * create encoders for your data.
    **/
  final case class Message(message: String) extends AnyVal
  object Message {
    implicit val messageEncoder: Encoder[Message] = new Encoder[Message] {
      final def apply(a: Message): Json = Json.obj(
        ("message", Json.fromString(a.message)),
      )
    }
    implicit def messageEntityEncoder[F[_]: Applicative]: EntityEncoder[F, Message] =
      jsonEncoderOf[F, Message]
  }

  def impl[F[_]: Applicative]: Record[F] = new Record[F]{
    def getContent(n: Record.Id): F[Record.Message] = {
      val id = n.id
      val content = id * 2;
      Message(s"Content of Record # ${id} is: ${content}").pure[F]
    }
  }
}