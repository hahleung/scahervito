import org.scalatest._, prop._

class RemoveFirstAndLastCharactersTest extends FunSuite with PropertyChecks with Matchers {
  import RemoveFirstAndLastCharacters.removeChars

  val fixedTests = Table[String, String](
    ("s", "expected"),
    ("eloquent", "loquen"),
    ("country", "ountr"),
    ("person", "erso"),
    ("place", "lac"),
  )

  test("Fixed tests") {
    forAll(fixedTests) {
      removeChars(_) shouldBe _
    }
  }
}
