package chapter4

sealed trait Tree

final case class Leaf(value: Int) extends Tree

final case class Node(left: Tree, right: Tree) extends Tree

object Tree {
  def sum(tree: Tree): Int = tree match {
    case Leaf(value)       => value
    case Node(left, right) => sum(left) + sum(right)
  }

  def double(tree: Tree): Tree = tree match {
    case Leaf(value)       => Leaf(2 * value)
    case Node(left, right) => Tree(double(left), double(right))
  }
}
