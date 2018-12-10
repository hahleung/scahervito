// You can test using ScalaTest (http://www.scalatest.org/).
import org.scalatest._

// TODO: replace this example test with your own, this is just here to demonstrate usage.
// See http://www.scalatest.org/at_a_glance for example usages
class SolTest extends FlatSpec with Matchers {
  "twiceAsOld(36, 7)" should "return 22" in {
    Sol.twiceAsOld(36, 7) should be (22)
  }
  "twiceAsOld(55, 30)" should "return 5" in {
    Sol.twiceAsOld(55, 30) should be (5)
  }
  "twiceAsOld(42, 21)" should "return 0" in {
    Sol.twiceAsOld(42, 21) should be (0)
  }
  "twiceAsOld(22, 1)" should "return 20" in {
    Sol.twiceAsOld(22, 1) should be (20)
  }
  "twiceAsOld(29, 0)" should "return 29" in {
    Sol.twiceAsOld(29, 0) should be (29)
  }
}
