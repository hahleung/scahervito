package circuit

import org.scalatest._

class ResistanceTest extends FlatSpec {
  it should "instantiate a [Resistance]" in {
    val value = 12
    val resistance = Resistance(value)

    assert(resistance.resistance === value.toFloat)
  }
}
