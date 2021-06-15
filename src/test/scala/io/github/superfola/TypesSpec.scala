package io.github.superfola

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import io.github.superfola.types._

class TypesSpec extends AnyFlatSpec with Matchers {
  val simpleTree: Tree[Int] = Leaf[Int](12)
  val bigTree: Tree[Int] = Node(
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

  "A simple tree" should "hold a node" in {
    simpleTree.computed should be(12)
  }
  it should "have a depth of one" in {
    simpleTree.depth should be(1)
  }
  it should "be reduced to itself" in {
    simpleTree.reduce should be(simpleTree)
  }

  "A bigger tree" should "hold many nodes" in {
    bigTree.depth should be(3)
  }
  it should "be evaluated to ((4 - 3) + (3 * 2))" in {
    bigTree.toString should be("((4 - 3) + (3 * 2))")
  }
  it should "have a value of 7" in {
    bigTree.computed should be(7)
  }
  it should "be reduced to the computation of its subnodes" in {
    bigTree.reduce.toString should be(
      Node[Int](
        Operators.add,
        Leaf(1),
        Leaf(6)
      ).toString)
  }
}
