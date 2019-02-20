package circuit

import org.scalatest._
import io.circe._, io.circe.parser._

class FullCircuitTest extends FlatSpec {
  it should "instantiate a simple [FullCircuit]" in {
    val rawFullCircuit: String =
      """
      {
        "id": "C101",
        "v": 5,
        "definition": {
          "type": "S",
          "value": 42
        }
      }
      """

    val fullCircuit = parser.decode[FullCircuit](rawFullCircuit)

    assert(fullCircuit === Right(FullCircuit("C101", 5)))
  }
}
