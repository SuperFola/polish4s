package io.github.superfola

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import io.github.superfola.types._

class TypesSpec extends AnyFlatSpec with Matchers {
  val simpleTree: Tree = Leaf(12)
  val smallTree: Tree = Node(
    Operators.sub,
    Leaf(1),
    Leaf(0)
  )
  val bigTree: Tree = Node(
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
    simpleTree.length should be(1)
  }
  it should "have a depth of one" in {
    simpleTree.depth should be(1)
  }
  it should "be reduced to itself" in {
    simpleTree.reduce should be(simpleTree)
  }

  "A small tree" should "hold two nodes" in {
    smallTree.length should be(2)
  }
  it should "have a depth of 1" in {
    smallTree.depth should be(2)
  }
  it should "be evaluated to (1 - 0)" in {
    smallTree.toString should be("(1 - 0)")
  }
  it should "be reduced to 1" in {
    smallTree.reduce should be(Leaf(1))
  }

  "A big tree" should "hold many nodes" in {
    bigTree.depth should be(3)
    bigTree.length should be(4)
  }
  it should "be evaluated to ((4 - 3) + (3 * 2))" in {
    bigTree.toString should be("((4 - 3) + (3 * 2))")
  }
  it should "have a value of 7" in {
    bigTree.computed should be(7)
  }
  it should "be reduced to the computation of its subnodes" in {
    bigTree.reduce.toString should be(
      Node(
        Operators.add,
        Leaf(1),
        Leaf(6)
      ).toString
    )
  }
  it should "be possible to reduce the tree to a single leaf" in {
    bigTree.reduce.reduce should be(Leaf(7))
  }

  "A big tree" should "have a prefix notation of (+ (- 4 3) (* 3 2))" in {
    bigTree.toPrefixNotation should be("(+ (- 4 3) (* 3 2))")
  }
}
