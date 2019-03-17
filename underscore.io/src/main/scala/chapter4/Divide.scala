//scala underscore/chapter4/Divide.scala
package chapter4

object divide {
  def apply(num: Int, denom: Int): DivisionResult =
    denom match {
      case 0 => Infinite
      case _ => Finite(num / denom)
    }
}

sealed trait DivisionResult {}
case object Infinite extends DivisionResult {}
final case class Finite(i: Int) extends DivisionResult {}

println(divide(1, 2))
println(divide(1, 0))
