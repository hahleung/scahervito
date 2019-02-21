package circuit

import org.scalatest._
import io.circe._, io.circe.parser._

class CircuitTest extends FlatSpec {
  it should "parse a [Circuit] of type [Resistance]" in {
    val rawCircuit: String =
      """
      {
        "type": "R",
        "value": 42
      }
      """

    val circuit = parser.decode[Circuit](rawCircuit)

    val expectedCircuit = Resistance(42)
    assert(circuit === Right(expectedCircuit))
    assert(circuit.map(_.resistance) === Right(42F))
  }

  it should "parse a [Circuit] of type [ParallelCircuit]" in {
    val rawCircuit: String =
      """
      {
        "type": "P",
        "circuits": [
          {
            "type": "R",
            "value": 3
          },
          {
            "type": "R",
            "value": 3
          }
        ]
      }
      """

    val circuit = parser.decode[Circuit](rawCircuit)

    val expectedCircuit = ParallelCircuit(List(Resistance(3), Resistance(3)))
    assert(circuit === Right(expectedCircuit))
    assert(circuit.map(_.resistance) === Right(1.5F))
  }

  it should "parse a [Circuit] of type [SeriesCircuit]" in {
    val rawCircuit: String =
      """
      {
        "type": "S",
        "circuits": [
          {
            "type": "R",
            "value": 5
          },
          {
            "type": "R",
            "value": 6
          }
        ]
      }
      """

    val circuit = parser.decode[Circuit](rawCircuit)

    val expectedCircuit = SeriesCircuit(List(Resistance(5), Resistance(6)))
    assert(circuit === Right(expectedCircuit))
    assert(circuit.map(_.resistance) === Right(11F))
  }
}
