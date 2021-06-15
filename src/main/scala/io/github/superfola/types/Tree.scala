package io.github.superfola.types

import scala.math.Numeric

sealed trait Tree[A] {
  def computed: A
  def reduce: Tree[A]
  def length: Int
  def depth: Int
  def isTerminal: Boolean
  def toPrefixNotation: String
}

final case class Leaf[A: Numeric](value: A) extends Tree[A] {
  override def computed: A = value

  override def reduce: Tree[A] = this

  override def length: Int = 1

  override def depth: Int = 1

  override def isTerminal: Boolean = true

  override def toPrefixNotation: String = value.toString

  override def toString: String = value.toString
}

final case class Node[A: Numeric](op: Operator[A], left: Tree[A], right: Tree[A]) extends Tree[A] {
  override def computed: A = op.functor(left.computed, right.computed)

  override def reduce: Tree[A] = {
    if (left.isTerminal && right.isTerminal)
      Leaf[A](computed)
    else
      Node[A](op, left.reduce, right.reduce)
  }

  override def length: Int =
    left.length + right.length

  override def depth: Int =
    1 + scala.math.max(left.depth, right.depth)

  override def isTerminal: Boolean = false

  override def toPrefixNotation: String = s"(${op.name} ${left.toPrefixNotation} ${right.toPrefixNotation})"

  override def toString: String = s"($left ${op.name} $right)"
}
