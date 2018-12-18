import org.scalatest._

class GrasshopperTest extends FlatSpec with Matchers {
  "summation(1)" should "return 1" in {
    Grasshopper.summation(1) should be(1)
  }
  "summation(8)" should "return 36" in {
    Grasshopper.summation(8) should be(36)
  }
}
