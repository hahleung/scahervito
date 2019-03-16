// scala FP/underscore/chapter3/Counter.scala

class Person(val firstName: String, val lastName: String)

object Person {
  def apply(fullName: String): Person = {
    val parsedFullName = fullName.split(" ")
    new Person(parsedFullName.head, parsedFullName.tail.mkString(" "))
  }
}

object Runner {
  def main(args: Array[String]): Unit = {
    println(Person("John Doe").firstName)
    println(Person.apply("John Doe").lastName)
    println(Person("Jane Smith Something").lastName)
  }
}
