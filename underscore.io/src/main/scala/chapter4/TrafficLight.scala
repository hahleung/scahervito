package chapter4

// Sum type pattern
sealed trait TrafficLight
final case object Red extends TrafficLight
final case object Green extends TrafficLight
final case object Orange extends TrafficLight

object Next {
  def next(tl: TrafficLight): TrafficLight = tl match {
    case Red => Green
    case Green => Orange
    case Orange => Red
  }
}
