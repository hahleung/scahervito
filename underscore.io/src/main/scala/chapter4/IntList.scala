package chapter4

sealed trait IntList {
  def insideLength: Int = this match {
    case End           => 0
    case Pair(_, tail) => 1 + tail.insideLength
  }
}

case object End extends IntList

final case class Pair(head: Int, tail: IntList) extends IntList

object IntList {
  def length(intList: IntList): Int = intList match {
    case End           => 0
    case Pair(_, tail) => 1 + length(tail)
  }

  def lengthTail(intList: IntList): Int = {
    doLengthTail(intList, 0)

    @scala.annotation.tailrec
    def doLengthTail(intList: IntList, length: Int): Int = intList match {
      case End           => length
      case Pair(_, tail) => doLengthTail(tail, length + 1)
    }
  }

  def product(intList: IntList): Int = intList match {
    case End              => 1
    case Pair(head, tail) => head * product(tail)
  }

  def productTail(intList: IntList): Int = {
    doProductTail(intList, 1)

    @scala.annotation.tailrec
    def doProductTail(list: IntList, daProduct: Int): Int = intList match {
      case End              => daProduct
      case Pair(head, tail) => doProductTail(tail, head * daProduct)
    }
  }
}
