package chapter4

// Product type pattern
trait BottledWater {
  def size: Int
  def source: Source
  def carbonated: Boolean
}

sealed trait Source
final case object Tap extends Source
final case object Well extends Source
final case object Spring extends Source
