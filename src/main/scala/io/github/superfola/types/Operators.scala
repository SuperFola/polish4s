package io.github.superfola.types

import scala.math.Numeric
import scala.math.Numeric.Implicits._

final case class Operator[A: Numeric](name: String, functor: (A, A) => A)

object Operators {
  def add[A: Numeric]: Operator[A] =
    Operator[A]("+", (x: A, y: A) => x + y)

  def sub[A: Numeric]: Operator[A] =
    Operator[A]("-", (x: A, y: A) => x - y)

  def mul[A: Numeric]: Operator[A] =
    Operator[A]("*", (x: A, y: A) => x * y)
}
