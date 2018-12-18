import org.scalatest._

//(cd codewars; sbt "test:testOnly *ExesAndOhsTest")
class ExesAndOhsTest extends FlatSpec with Matchers {

  val tests = List(
    ("xo", true),
    ("adfbfgdf", true),
    ("xo0", true),
    ("xxxoo", false),
    ("xXxoo", false)
  )

  tests.foreach {
    case (input, expected) =>
      s"xo($input)" should s"return $expected" in {
        ExesAndOhs.xo(input) should be(expected)
      }
  }
}
