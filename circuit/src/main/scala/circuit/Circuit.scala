package circuit

import circuit.CircuitType._

sealed trait Circuit {
  //  TODO: Is it really useful? Maybe for JSON parsing?
  def circuitType: CircuitType

  def resistance: Float
}

// TODO: For Parallel and Series
sealed trait ComplexCircuit {
  val circuits: Seq[Circuit]
}

final case class Resistance(value: Int) extends Circuit {
  def circuitType: CircuitType = CircuitType.Resistance

  def resistance: Float = value.toFloat
}

final case class ParallelCircuit(override val circuits: Seq[Circuit]) extends Circuit with ComplexCircuit {
  def circuitType: CircuitType = CircuitType.Parallel

  def resistance: Float = circuits.length match {
    case 0 => 0F
    case _ => 1F / circuits.foldLeft(0F)(_ + 1F / _.resistance)
  }
}

final case class SeriesCircuit(override val circuits: Seq[Circuit]) extends Circuit with ComplexCircuit {
  def circuitType: CircuitType = CircuitType.Series

  def resistance: Float = circuits.foldLeft(0F)(_ + _.resistance)
}

object Resistance {
  def apply(value: Int = 0): Resistance = new Resistance(value)
}

object ParallelCircuit {
  def apply(circuits: Seq[Circuit] = Seq.empty): ParallelCircuit = new ParallelCircuit(circuits)
}

object SeriesCircuit {
  def apply(circuits: Seq[Circuit] = Seq.empty): SeriesCircuit = new SeriesCircuit(circuits)
}
