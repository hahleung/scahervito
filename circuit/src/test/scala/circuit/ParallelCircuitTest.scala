package circuit

import org.scalatest._

class ParallelCircuitTest extends FlatSpec {
  it should "instantiate a [ParallelCircuit]" in {
    val parallelCircuit = new ParallelCircuit()

    assert(parallelCircuit.resistanceValue === 0F)
  }

  it should "inverse the sum of the inverse of the resistances of the circuit" in {
    val resistance = new Resistance(12)
    val parallelCircuit = new ParallelCircuit().copy(circuits = List(resistance, resistance))

    assert(parallelCircuit.resistanceValue === 6F)
  }
}
