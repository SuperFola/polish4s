package io.github.superfola

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import io.github.superfola.types._

class TypesSpec extends AnyFlatSpec with Matchers {
  "A simple tree" should "hold a node" in {
    val simpleTree: Tree[Int] = Leaf[Int](12)
    simpleTree.reduce should be(simpleTree)
    simpleTree.computed should be(12)
  }
  it should "have a depth of one" in {
    val simpleTree: Tree[Int] = Leaf[Int](12)
    simpleTree.depth should be(1)
  }
}
