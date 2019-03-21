package chapter4

// polymorphism inside the class
sealed trait TrafficLightP {
  def next: TrafficLightP

//  I think this is the best, because it's expressive when consumed
  def nextTrafficLight: TrafficLightP = this match {
    case RedP => GreenP
    case OrangeP => RedP
    case GreenP => OrangeP
  }
}

// Too many lines in my opinion
final case object RedP extends TrafficLightP {
  def next: TrafficLightP = GreenP
}

final case object GreenP extends TrafficLightP {
  def next: TrafficLightP = OrangeP
}

final case object OrangeP extends TrafficLightP {
  def next: TrafficLightP = RedP
}
