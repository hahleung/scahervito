package circuit

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._, io.circe.generic.semiauto._

case class FullCircuit(id: String, v: Int) {
}

object FullCircuit {
  implicit val fullCircuitDecoder: Decoder[FullCircuit] = deriveDecoder[FullCircuit]
  implicit val fullCircuitEncoder: Encoder[FullCircuit] = deriveEncoder[FullCircuit]
}
