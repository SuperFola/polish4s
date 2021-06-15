package io.github.superfola

import cats.implicits.toTraverseOps
import io.github.superfola.types._

sealed trait Token

final case class TokenNumber(value: Double)     extends Token
final case class TokenOperator(value: Operator) extends Token

final case class PartialTree(tree: Tree, rest: List[Token])

object Parser {
  def tokensListToTree(tokens: List[Token]): Option[PartialTree] = {
    println(s"rec: $tokens")

    tokens match {
      case head :: tail =>
        head match {
          case TokenNumber(value) => Some(PartialTree(Leaf(value), tail))
          case TokenOperator(value) =>
            tokensListToTree(tail)
              .flatMap { case PartialTree(leftTree, rest) =>
                tokensListToTree(rest)
                  .map { case PartialTree(rightTree, _) =>
                    val pt = PartialTree(Node(value, leftTree, rightTree), Nil)
                    println(s"computed: $pt")
                    pt
                  }
              }
        }
      case Nil => None
    }
  }

  def apply(source: String): Option[Tree] =
    source.trim
      .split(" ")
      .toList
      .map { token =>
        token.toDoubleOption
          .map(TokenNumber(_))
          .orElse(
            Operators
              .operatorFromStringOption(token)
              .map(TokenOperator(_))
          )
      }
      .sequence
      .flatMap(a => tokensListToTree(a).map(_.tree))
}
