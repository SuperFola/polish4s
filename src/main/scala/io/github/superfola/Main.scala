package io.github.superfola

import cats.effect.{ExitCode, IO, IOApp}
import io.github.superfola.types._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    if (args.length == 0) {
      IO(println("Not enough arguments")) *> IO.pure(ExitCode.Error)
    }
    else {
      IO(println(args(0))) *> IO.pure(ExitCode.Success)
    }
  }
}
