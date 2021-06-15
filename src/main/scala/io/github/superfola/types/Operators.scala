package io.github.superfola.types

final case class Operator(name: String, functor: (Double, Double) => Double) {
  override def toString: String = s"Operator($name)"
}

object Operators {
  def add: Operator =
    Operator("+", (x: Double, y: Double) => x + y)

  def sub: Operator =
    Operator("-", (x: Double, y: Double) => x - y)

  def mul: Operator =
    Operator("*", (x: Double, y: Double) => x * y)

  def operatorFromStringOption(name: String): Option[Operator] =
    name match {
      case "+" => Some(Operators.add)
      case "-" => Some(Operators.sub)
      case "*" => Some(Operators.mul)
      case _   => None
    }
}
