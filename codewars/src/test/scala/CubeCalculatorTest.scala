import org.scalatest.WordSpec
import org.scalatest.concurrent.Eventually
import org.scalatest.time.{Seconds, Span}

class CubeCalculatorTest extends WordSpec
  with Eventually {

  def delayResponse: String = {
    println("hey")
    Thread.sleep(3000)
    "done"
  }

  "CubeCalculator" should {
    ".cube" in {
      val timeoutDuration = timeout(Span(1, Seconds))
      val intervalDuration = interval(Span(1, Seconds))

      eventually(timeoutDuration, intervalDuration) {
        assert(delayResponse == "done")
        //        assert(CubeCalculator.cube(3) === 27)
      }
    }
  }
}
