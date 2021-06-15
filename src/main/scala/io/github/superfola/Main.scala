package io.github.superfola

import cats.effect.{ExitCode, IO, IOApp}
import io.github.superfola.types.{Leaf, Node, Tree}

object Main extends IOApp {
  def reduceToEnd(tree: Tree, depth: Int = 0): IO[Unit] =
    tree match {
      case a: Node =>
        IO(println(s"$depth) $a")) *> reduceToEnd(a.reduce, depth + 1)
      case Leaf(value) => IO(println(s"$depth) $value"))
    }

  override def run(args: List[String]): IO[ExitCode] =
    if (args.isEmpty) {
      IO(println("Not enough arguments")) *> IO.pure(ExitCode.Error)
    } else {
      Parser(args.head) match {
        case Some(value) =>
          IO {
            println(s"Got ${args.head}\nInfix notation: ${value}\nPrefix notation: ${value.toPrefixNotation}")
            println(s"Computed value: ${value.computed}")
          } *> reduceToEnd(value) *> IO.pure(ExitCode.Success)
        case None => IO(println("Error: couldn't parse given expression")) *> IO.pure(ExitCode.Error)
      }
    }
}
