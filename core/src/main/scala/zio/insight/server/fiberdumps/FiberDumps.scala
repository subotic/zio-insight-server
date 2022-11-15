package zio.insight.server.fiberdumps

import zio._

object FiberDumps extends ZIOAppDefault {

  val run = for {
    q   <- Queue.bounded[Fiber.Dump](1000)
    _   <- Fiber.dumpAllWith(d => q.offer(d))
    all <- q.takeAll.map(_.toString())
    _   <- Console.printLine(all)
  } yield ()

}
