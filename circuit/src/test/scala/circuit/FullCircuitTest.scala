package circuit

import io.circe._
import org.scalatest._
import samples._

class FullCircuitTest extends FlatSpec {
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

  it should "parse a simple [FullCircuit]" in {
    val fullCircuit = parser.decode[FullCircuit](rawFullCircuit)

    val expectedCircuit = Resistance(42)
    assert(fullCircuit === Right(FullCircuit("C101", 5, expectedCircuit)))
  }

  it should "return the resistance of a [FullCircuit]" in {
    val fullCircuit = parser.decode[FullCircuit](rawFullCircuit)

    val fullCircuitResistance = fullCircuit.map(_.resistance)

    assert(fullCircuitResistance === Right(42F))
  }

//  it should "return the resistance of a complex [FullCircuit]" in {
//    val complexCircuit = Sample.Circuit.complex
//
//    val expectedCircuit = Resistance(42)
//    assert(complexCircuit === Right(expectedCircuit))
//  }
}
