package circuit

import org.scalatest._

class SeriesCircuitTest extends FlatSpec {
  it should "instantiate a [SeriesCircuit]" in {
    val seriesCircuit = SeriesCircuit()

    assert(seriesCircuit.resistance === 0F)
  }

  it should "sum the resistances of the circuit" in {
    val resistance = Resistance(12)
    val seriesCircuit = SeriesCircuit(List(resistance, resistance))

    assert(seriesCircuit.resistance === 24F)
  }
}
