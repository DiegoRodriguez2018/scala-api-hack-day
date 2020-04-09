package com.reagroup.simpleApi

import doobie._
import doobie.implicits._
import doobie.util.ExecutionContexts
import cats._
import cats.effect._
import cats.implicits._


object Database {
    def main(args: Array[String]):Unit =
    {
        println("Connecting to database") 

        
        implicit val cs = IO.contextShift(ExecutionContexts.synchronous)
        
        val xa = Transactor.fromDriverManager[IO](
            "org.postgresql.Driver",     // driver classname
            "jdbc:postgresql:world",     // connect URL (driver-specific)
            "diego-personal",                  // user
            "",                          // password
            Blocker.liftExecutionContext(ExecutionContexts.synchronous) // just for testing
            )
            
        val program1 = 42.pure[ConnectionIO];
        
        val io = program1.transact(xa)

        io.unsafeRunSync




    }

}   