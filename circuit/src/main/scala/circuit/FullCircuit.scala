package circuit

import io.circe._
import io.circe.generic.semiauto._

case class FullCircuit(id: String, v: Int, definition: Circuit) {
  def resistance: Float = definition.resistance
}

//From: https://github.com/circe/circe/issues/561
object FullCircuit {
  implicit val fullCircuitDecoder: Decoder[FullCircuit] = new Decoder[FullCircuit] {
    fullCircuit =>
    final def apply(cursor: HCursor): Decoder.Result[FullCircuit] =
      for {
        id <- cursor.downField("id").as[String]
        v <- cursor.downField("v").as[Int]
        definition <- cursor.downField("definition").as[Circuit]
      } yield {
        new FullCircuit(id, v, definition)
      }
  }

  implicit val fullCircuitEncoder: Encoder[FullCircuit] = deriveEncoder[FullCircuit]
}
