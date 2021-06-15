package io.github.superfola

object Main extends App {
  def create: Tree[Int] =
    Node[Int](
      Operators.add,
      left=Node(
        Operators.sub,
        Leaf(1),
        Leaf(4)
      ),
      right=Leaf(2)
    )

  val tree: Tree[Int] = create

  println(tree)
  println(tree.computed)
}
