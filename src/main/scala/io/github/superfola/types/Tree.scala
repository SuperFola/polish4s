package io.github.superfola.types

import scala.math.Numeric

sealed trait Tree {
  def computed: Double
  def reduce: Tree
  def length: Int
  def depth: Int
  def isTerminal: Boolean
  def toPrefixNotation: String
}

final case class Leaf(value: Double) extends Tree {
  override def computed: Double = value

  override def reduce: Tree = this

  override def length: Int = 1

  override def depth: Int = 1

  override def isTerminal: Boolean = true

  override def toPrefixNotation: String = value.toString

  override def toString: String = value.toString
}

final case class Node(op: Operator, left: Tree, right: Tree) extends Tree {
  override def computed: Double = op.functor(left.computed, right.computed)

  override def reduce: Tree =
    if (left.isTerminal && right.isTerminal)
      Leaf(computed)
    else
      Node(op, left.reduce, right.reduce)

  override def length: Int =
    left.length + right.length

  override def depth: Int =
    1 + scala.math.max(left.depth, right.depth)

  override def isTerminal: Boolean = false

  override def toPrefixNotation: String = s"(${op.name} ${left.toPrefixNotation} ${right.toPrefixNotation})"

  override def toString: String = s"($left ${op.name} $right)"
}
