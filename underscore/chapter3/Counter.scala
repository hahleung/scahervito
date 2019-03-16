// scala FP/underscore/chapter3/Counter.scala

class Counter(val count: Int = 21) {
  def inc = new Counter(count + 1)

  def dec = new Counter(count - 1)

  def incFast: Counter = incFast()

  def decFast: Counter = decFast()

  def incFast(increment: Int = 1) = new Counter(count + increment)

  def decFast(increment: Int = 1) = new Counter(count - increment)

  def adjust(adder: Adder) = new Counter(adder.add(count))
}

class Adder(amount: Int) {
  def add(in: Int) = in + amount
}

object Runner {
  def main(args: Array[String]): Unit = {
    println(new Counter().count)
    println(new Counter(10).inc.dec.inc.inc.count)
    println(new Counter(10).incFast(3).count)
    println(new Counter(10).incFast.count)
    println(new Counter(10).decFast(3).count)
    println(new Counter(10).decFast.count)

    val adder = new Adder(20)
    val count = new Counter(10).adjust(adder).count
    println(count)
  }
}
