package circuit

import org.scalatest._

class SeriesCircuitTest extends FlatSpec {
  it should "instantiate a [SeriesCircuit]" in {
    val seriesCircuit = new SeriesCircuit()

    assert(seriesCircuit.resistanceValue === 0F)
  }

  it should "sum the resistances of the circuit" in {
    val resistance = new Resistance(12)
    val seriesCircuit = new SeriesCircuit().copy(circuits = List(resistance, resistance))

    assert(seriesCircuit.resistanceValue === 24F)
  }
}
