package circuit

import circuit.CircuitType._
import circuit.ParallelCircuit._
import circuit.Resistance._
import circuit.SeriesCircuit._
import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder}

sealed trait Circuit {
  def circuitType: CircuitType

  def resistance: Float
}

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

object Circuit {
  implicit val circuitDecoder: Decoder[Circuit] = Decoder.instance(cursor =>
    cursor.downField("type").as[String].flatMap {
      case "R" => cursor.as[Resistance]
      case "P" => cursor.as[ParallelCircuit]
      case "S" => cursor.as[SeriesCircuit]
    }
  )

  implicit val circuitEncoder: Encoder[Circuit] = deriveEncoder[Circuit]
}

object Resistance {
  def apply(value: Int = 0): Resistance = new Resistance(value)

  implicit val resistanceDecoder: Decoder[Resistance] = deriveDecoder[Resistance]
  implicit val resistanceEncoder: Encoder[Resistance] = deriveEncoder[Resistance]
}

object ParallelCircuit {
  def apply(circuits: Seq[Circuit] = Seq.empty): ParallelCircuit = new ParallelCircuit(circuits)

  implicit val parallelCircuitDecoder: Decoder[ParallelCircuit] = deriveDecoder[ParallelCircuit]
  implicit val parallelCircuitEncoder: Encoder[ParallelCircuit] = deriveEncoder[ParallelCircuit]
}

object SeriesCircuit {
  def apply(circuits: Seq[Circuit] = Seq.empty): SeriesCircuit = new SeriesCircuit(circuits)

  implicit val seriesCircuitDecoder: Decoder[SeriesCircuit] = deriveDecoder[SeriesCircuit]
  implicit val seriesCircuitEncoder: Encoder[SeriesCircuit] = deriveEncoder[SeriesCircuit]
}

