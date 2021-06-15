package io.github.superfola.types

import scala.math.Numeric

sealed trait Tree[A] {
  def computed: A
  def reduce: Tree[A]
  def depth: Int
  def isTerminal: Boolean
}

final case class Leaf[A: Numeric](value: A) extends Tree[A] {
  override def computed: A = value

  override def reduce: Tree[A] = this

  override def depth: Int = 1

  override def isTerminal: Boolean = true

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

  override def depth: Int =
    1 + scala.math.max(left.depth, right.depth)

  override def isTerminal: Boolean = false

  override def toString: String = s"($left ${op.name} $right)"
}
