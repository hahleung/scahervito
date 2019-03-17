package chapter4

// Sum type pattern
sealed trait TrafficLight
final case object Red extends TrafficLight
final case object Green extends TrafficLight
final case object Orange extends TrafficLight

sealed trait Calculator
final case class Success(result: Int) extends Calculator
final case class Failure(error: String) extends Calculator

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
