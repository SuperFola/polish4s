# Polish4s

A short program to convert a polish notation string to a Tree, and operates on it.

## Example

Either compile it and use the command line:

`java -cp polish-notation io.github.superfola.Main "+ * 1 2 3"`

Or use Scala to explore the tree:

```scala
import io.github.superfola._
import io.github.superfola.types._

object Test extends App {
  val tree: Option[Tree] = Parser("+ * 1 2 3")

  tree.foreach { value =>
    println(value.computed)  // 5.0
    println(value.reduce)    // (2.0 + 3.0)
    println(value.depth, value.length)  // (3, 3)
  }
}
```