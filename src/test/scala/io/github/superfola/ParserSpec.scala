package io.github.superfola

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import io.github.superfola._
import io.github.superfola.types._

class ParserSpec extends AnyFlatSpec with Matchers {
  val simplestExpr = "- 4 3"
  val simpleExpr   = "+ 1 - 2 3"
  val biggerExpr   = "+ * 1 2 3"
  val bigExpr      = "+ + 1 - * 2 3 4 5"

  "A polish parser" should "parse simple expressions" in {
    val res: Option[Tree] = Parser(simplestExpr)
    res should be(Some(Node(Operators.sub, Leaf(4), Leaf(3))))
  }
  it should "parse operations with more than one operator" in {
    val res: Option[Tree] = Parser(simpleExpr)
    res should be(
      Some(
        Node(
          Operators.add,
          Leaf(1),
          Node(
            Operators.sub,
            Leaf(2),
            Leaf(3)
          )
        )
      )
    )
  }
  it should "parse expressions with contiguous operators" in {
    val res: Option[Tree] = Parser(biggerExpr)
    res should be(
      Some(
        Node(
          Operators.add,
          Node(
            Operators.mul,
            Leaf(1),
            Leaf(2)
          ),
          Leaf(3)
        )
      )
    )
  }
  it should "parse very big and complex expressions" in {
    val res: Option[Tree] = Parser(bigExpr)
    res should be(
      Some(
        Node(
          Operators.add,
          Node(Operators.add, Leaf(1), Node(Operators.sub, Node(Operators.mul, Leaf(2), Leaf(3)), Leaf(4))),
          Leaf(5)
        )
      )
    )
  }
}
