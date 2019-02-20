package circuit

import org.scalatest._

class ParallelCircuitTest extends FlatSpec {
  it should "instantiate a [ParallelCircuit]" in {
    val parallelCircuit = ParallelCircuit()

    assert(parallelCircuit.resistance === 0F)
  }

  it should "inverse the sum of the inverse of the resistances of the circuit" in {
    val resistance = Resistance(12)
    val parallelCircuit = ParallelCircuit(circuits = List(resistance, resistance))

    assert(parallelCircuit.resistance === 6F)
  }
}
