package circuit

import io.circe._
import org.scalatest._

class FullCircuitTest extends FlatSpec {
  it should "parse a simple [Circuit]" in {
    val rawFullCircuit: String =
      """
      {
        "id": "C101",
        "v": 5,
        "definition": {
          "type": "R",
          "value": 42
        }
      }
      """

    val fullCircuit = parser.decode[FullCircuit](rawFullCircuit)

    val expectedCircuit = Resistance(42)
    assert(fullCircuit === Right(FullCircuit("C101", 5, expectedCircuit)))
  }
}
