//package fpinscala.state

trait RNG {
  def nextInt: (Int, RNG) // Should generate a random `Int`. We'll later define other functions in terms of `nextInt`.
}

object RNG {

  // NB - this was called SimpleRNG in the book text

  case class Simple(seed: Long) extends RNG {
    def nextInt: (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL // `&` is bitwise AND. We use the current seed to generate a new seed.
      val nextRNG = Simple(newSeed) // The next state, which is an `RNG` instance created from the new seed.
      val n = (newSeed >>> 16).toInt // `>>>` is right binary shift with zero fill. The value `n` is our new pseudo-random integer.
      (n, nextRNG) // The return value is a tuple containing both a pseudo-random integer and the next `RNG` state.
    }
  }

  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] = rng => (a, rng)

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }

  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (integer, nextRng) = rng.nextInt

    val nextInteger = integer match {
      case Int.MinValue => Int.MaxValue
      case int => math.abs(int)
    }

    (nextInteger, nextRng)
  }

  def double(rng: RNG): (Double, RNG) = {
    val (integer, newRng) = nonNegativeInt(rng)
    val double = (integer - 1).toDouble / Int.MaxValue
    (double, newRng)
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (integer, newRng) = rng.nextInt
    val (anotherInteger, anotherNewRng) = newRng.nextInt

    ((integer, integer.toDouble / anotherInteger), anotherNewRng)
  }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val ((integer, double), newRng) = RNG.intDouble(rng)
    ((double, integer), newRng)
  }

  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
    val seed: (List[Double], RNG) = (List(), rng)

    val reduction = (accumulator: (List[Double], RNG), _item: Int) => {
      val (list, rng) = accumulator
      val (myDouble, newRng) = RNG.double(rng)
      (myDouble :: list, newRng)
    }

    // Hum... this is shit. (list to tuple mechanism)
    val (x :: y :: z, lastRng) = (1 to 3).foldLeft(seed)(reduction)

    ((x, y, z.head), lastRng)
  }

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
    @annotation.tailrec
    def go(count: Int, rng: RNG, intsList: List[Int]): (List[Int], RNG) =
      count match {
        case 0 => (intsList, rng)
        case _ =>
          val (newInt, newRng) = rng.nextInt
          go(count - 1, newRng, newInt :: intsList)
      }

    go(count, rng, List())
  }

  def doubleViaMap: Rand[Double] =
  // nonNegativeInt: RNG => Rand[Int]
  // the function  : Int => Double
  // Syntax sugar:
  // map(nonNegativeInt)((_ - 1).toDouble / Int.MaxValue)
    map(nonNegativeInt)(integer => (integer - 1).toDouble / Int.MaxValue)


  def map2[A, B, C](randA: Rand[A], randB: Rand[B])(f: (A, B) => C): Rand[C] =
    rng => {
      val (a, rngA) = randA(rng)
      val (b, rngB) = randB(rngA)
      (f(a, b), rngB)
    }

  //  The solution recommends `foldRight`: list order preserved and no RNG manipulation
  def sequence[A](rands: List[Rand[A]]): Rand[List[A]] =
    rng => {
      val seed: (List[A], RNG) = (List(), rng)
      val reductionFunction = (accumulator: (List[A], RNG), randA: Rand[A]) => {
        val (listA, currentRng) = accumulator
        val (a, nextRng) = randA(currentRng)
        (a :: listA, nextRng)
      }

      rands.foldLeft(seed)(reductionFunction)
    }

  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] =
    rng => {
      val (a, nextRng) = f(rng)
      g(a)(nextRng)
    }

  def nonNegativeLessThan(n: Int): Rand[Int] = {
    flatMap(nonNegativeInt) { i =>
      val mod = i % n
      if (i + (n - 1) - mod >= 0) unit(mod) else nonNegativeLessThan(n)
    }
    //    flatMap(nonNegativeInt) {
    //      x => if (x < n) unit(n) else nonNegativeLessThan(n)
    //    }
  }

  def mapViaFlatMap[A, B](s: Rand[A])(f: A => B): Rand[B] = flatMap(s)(identity)

  //    flatMap(s)(a => unit(f(a)))

  def map2ViaFlatMap[A, B, C](randA: Rand[A], randB: Rand[B])(f: (A, B) => C): Rand[C] =

  //    flatMap(ra)(a => map(rb)(b => f(a, b)))
}

case class State[S, +A](run: S => (A, S)) {
  //  def map[B](f: A => B): State[S, B] =
  //  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] =
  //  def flatMap[B](f: A => State[S, B]): State[S, B] =
}

sealed trait Input

case object Coin extends Input

case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

object State {
  type Rand[A] = State[RNG, A]

  //  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = ???
}

object Runner {
  // Just a boilerplate to help
  def displayTitle(title: String): Unit = println("\n\t" + title)

  def assert(subject: Any, expected: Any): Unit = {
    val result = subject == expected
    val display =
      result match {
        case true => "Passed\n\tReceived: " + subject.toString
        case false => "Failed\n\tReceived: " + subject.toString + "\n\tExpected: " + expected.toString
      }
    println(display)
  }

  def main(args: Array[String]): Unit = {
    val mySimpleRng = RNG.Simple(42)

    displayTitle("NON NEGATIVE INT")
    val (nonNegativeInt, _) = RNG.nonNegativeInt(mySimpleRng)
    assert(nonNegativeInt > 0, true)

    displayTitle("DOUBLE")
    val (doubleNumber, _) = RNG.double(mySimpleRng)
    println(RNG.double(mySimpleRng))
    assert(doubleNumber >= 0, true)
    assert(doubleNumber < 1, true)

    displayTitle("INT/DOUBLE, DOUBLE/INT, DOUBLE 3, INT")
    println(RNG.intDouble(mySimpleRng))
    println(RNG.doubleInt(mySimpleRng))
    println(RNG.double3(mySimpleRng))
    println(RNG.ints(5)(mySimpleRng))

    displayTitle("DOUBLE VIA MAP")
    println(RNG.doubleViaMap(mySimpleRng))

    displayTitle("SEQUENCE")
    val listOfRands = List.fill(4)(rng => RNG.nonNegativeInt(rng))
    println(RNG.sequence(listOfRands)(mySimpleRng))

    displayTitle("FLAT MAP, NON NEGATIVE LESS THAN")
    println(RNG.nonNegativeLessThan(5)(mySimpleRng))
  }
}
