package circuit

import circuit.CircuitType._

trait Circuit {
//  TODO: Is it really useful? Maybe for JSON parsing?
  def circuitType: CircuitType

  def resistanceValue: Float
}

// TODO: For Parallel and Series
//trait ComplexCircuit {
//  val circuits: Seq[Circuit] = Seq.empty[Circuit]
//}

case class Resistance(value: Int) extends Circuit {
  def circuitType: CircuitType = CircuitType.Resistance

  def resistanceValue: Float = value.toFloat
}

case class ParallelCircuit(circuits: Seq[Circuit] = Seq.empty) extends Circuit {
  def circuitType: CircuitType = CircuitType.Parallel

  def resistanceValue: Float = circuits.length match {
    case 0 => 0F
    case _ => 1F / circuits.foldLeft(0F)(_ + 1F / _.resistanceValue)
  }
}

case class SeriesCircuit(circuits: Seq[Circuit] = Seq.empty) extends Circuit {
  def circuitType: CircuitType = CircuitType.Series

  def resistanceValue: Float = circuits.foldLeft(0F)(_ + _.resistanceValue)
}
