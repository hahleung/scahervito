import org.scalatest._
import org.scalatest.Assertions._

import RotationsTest._

//(cd codewars; sbt "test:testOnly *RotationsTest")
class RotationsTest extends FlatSpec {
  it should "pass basic tests" in {
    var a = List("bsjq", "qbsj", "sjqb", "twZNsslC", "jqbs")
    testing(Rotations.containAllRots("bsjq", a), true);
    a = List("TzYxlgfnhf", "yqVAuoLjMLy", "BhRXjYA", "YABhRXj", "hRXjYAB", "jYABhRX", "XjYABhR", "ABhRXjY")
    testing(Rotations.containAllRots("XjYABhR", a), false);
    a = List("hQmSQJA", "QJAhQmS", "QmSQJAh", "yUgUXoQE", "AhQmSQJ", "mSQJAhQ", "SQJAhQm", "JAhQmSQ")
    testing(Rotations.containAllRots("QJAhQmS", a), true);
    a = List("nVOETcaxdvuk", "shpEts", "hpEtss", "Etsshp", "OuIiQ", "sXrDdPXIaW", "tsshpE", "pEtssh")
    testing(Rotations.containAllRots("Etsshp", a), false);

  }
}

object RotationsTest {
  def testing(act: Boolean, exp: Boolean): Unit = {
    assertResult(exp) {
      act
    }
  }
}
