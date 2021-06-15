package io.github.superfola

import io.github.superfola.types._

object Main extends App {
  def create: Tree =
    Node(
      Operators.add,
      Node(
        Operators.sub,
        Leaf(4),
        Leaf(3)
      ),
      Node(
        Operators.mul,
        Leaf(3),
        Leaf(2)
      )
    )

  val tree: Tree = create

  println(tree)
  println(tree.computed)
  println(tree.reduce)
  println(tree.reduce.reduce)

  println("=======")

  val testTree = tree.toPrefixNotation
    .replace("(", "")
    .replace(")", "")
  println(testTree)

  println(Parser(testTree))
}
