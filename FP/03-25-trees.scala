sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def size[A](tree: Tree[A]): Int =
    tree match {
      case Leaf(_) => 1
      case Branch(leftTree, rightTree) => 1 + size(leftTree) + size(rightTree)
    }

  def maximum(tree: Tree[Int]): Int =
    tree match {
      case Leaf(value) => value
      case Branch(leftTree, rightTree) => maximum(leftTree) max maximum(rightTree)
    }

  def depth[A](tree: Tree[A]): Int = {
    // def go[A](subTree: Tree[A], pathDepth: Int): Int = {
    //   val newPathDepth = pathDepth + 1
    //   subTree match {
    //     case Leaf(_) => newPathDepth
    //     case Branch(leftTree, rightTree) =>
    //       go(leftTree, newPathDepth) max go(rightTree, newPathDepth)
    //   }
    // }
    // go(tree, 0)
    tree match {
      case Leaf(_) => 1
      case Branch(l, r) => 1 + (depth(l) max depth(r))
    }
  }

  def map[A, B](tree: Tree[A])(mapFunction: A => B): Tree[B] =
    tree match {
      case Leaf(value) => Leaf(mapFunction(value))
      case Branch(leftTree, rightTree) =>
        Branch(map(leftTree)(mapFunction), map(rightTree)(mapFunction))
    }

  def fold[A, B](tree: Tree[A])(leafTransform: A => B)(branchTransform: (B, B) => B): B =
    tree match {
      case Leaf(value) => leafTransform(value)
      case Branch(leftTree, rightTree) =>
        branchTransform(
          fold(leftTree)(leafTransform)(branchTransform),
          fold(rightTree)(leafTransform)(branchTransform)
        )
    }

  def sizeComFold[A](tree: Tree[A]) = fold(tree)(_ => 1)(1 + _ + _)

  def maximumComFold(tree: Tree[Int]) = fold(tree)(v => v)((l, r) => l max r)

  def depthComFold[A](tree: Tree[A]) = fold(tree)(_ => 1)((l, r) => 1 + (l max r))

  def mapComFold[A, B](tree: Tree[A])(mapFunction: A => B): Tree[B] =
    // fold(tree)(v => Leaf(mapFunction(v)))((l, r) => Branch(l, r))
    // previous does not work as it does know what Leaf(mapFunction()) is returning
    fold(tree)(v => Leaf(mapFunction(v)): Tree[B])((l, r) => Branch(l, r))
}

object Runner {
  def main(args: Array[String]) : Unit = {
    val leaf = Leaf(42)
    val branch = Branch(leaf, leaf)
    val bigBranch = Branch(branch, branch)
    val tree = Branch(bigBranch, Leaf(89))
    println(tree)

    println(Tree.size(tree))
    println(Tree.maximum(tree))
    println(Tree.depth(tree))
    println(Tree.map(tree)(_ + 1))

    println(Tree.sizeComFold(tree))
    println(Tree.maximumComFold(tree))
    println(Tree.depthComFold(tree))
    println(Tree.mapComFold(tree)(_ + 2))
  }
}
