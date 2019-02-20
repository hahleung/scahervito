package circuit

import org.scalatest._

class CircuitIntegrationTest extends FlatSpec {
  it should "compute correctly a complex circuit" in {
    val resistance = Resistance(12)
    val seriesCircuit = SeriesCircuit(circuits = List(resistance, resistance))
    val parallelCircuit = ParallelCircuit(circuits = List(seriesCircuit, resistance))

    val mainCircuit = SeriesCircuit(List(parallelCircuit, seriesCircuit, resistance))

    assert(mainCircuit.resistance === 44F)
  }
}
