package io.github.superfola.types

import scala.math.Numeric

sealed trait Tree[A] {
  def computed: A
  def reduce: Tree[A]
}

final case class Leaf[A: Numeric](value: A) extends Tree[A] {
  override def computed: A = value

  override def reduce: Tree[A] = this

  override def toString: String = value.toString
}

final case class Operator[A: Numeric](name: String, functor: (A, A) => A)

final case class Node[A: Numeric](op: Operator[A], left: Tree[A], right: Tree[A]) extends Tree[A] {
  override def computed: A = op.functor(left.computed, right.computed)

  override def reduce: Tree[A] = Node[A](op, left.reduce, right.reduce)

  override def toString: String = s"($left ${op.name} $right)"
}
