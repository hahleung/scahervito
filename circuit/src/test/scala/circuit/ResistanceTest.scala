package circuit

import org.scalatest._

class ResistanceTest extends FlatSpec {
  it should "instantiate a [Resistance]" in {
    val value = 12
    val resistance = new Resistance(value)

    assert(resistance.resistanceValue === value.toFloat)
  }
}
