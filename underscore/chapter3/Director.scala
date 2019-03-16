import scala.reflect.io.File
// scala FP/underscore/chapter3/Director.scala

//class Director(val firstName: String, val lastName: String, val yearOfBirth: Int) {
//  def name: String = s"$firstName, $lastName"
//}
//
//object Director {
//  def apply(firstName: String, lastName: String, yearOfBirth: Int) =
//    new Director(firstName, lastName, yearOfBirth)
//
//  def older(firstDirector: Director, secondDirector: Director) =
//    if (firstDirector.yearOfBirth >= secondDirector.yearOfBirth) firstDirector
//    else secondDirector
//}

case class Director(firstName: String, lastName: String, yearOfBirth: Int) {
  def name: String = s"$firstName, $lastName"
}

object Director {
  def older(firstDirector: Director, secondDirector: Director) =
    if (firstDirector.yearOfBirth >= secondDirector.yearOfBirth) firstDirector
    else secondDirector
}

class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double, val director: Director) {
  def directorAge = yearOfRelease - director.yearOfBirth

  def isDirectedBy(directorGuess: Director) = director == directorGuess

  def copy(name: String = name, yearOfRelease: Int = yearOfRelease, imdbRating: Double = imdbRating, director: Director = director): Film = new Film(name, yearOfRelease, imdbRating, director)
}

object Film {
  def apply(name: String, yearOfRelease: Int, imdbRating: Double, director: Director): Film =
    new Film(name, yearOfRelease, imdbRating, director)

  def highestRating(film1: Film, film2: Film) =
    if (film1.imdbRating >= film2.imdbRating) film1
    else film2

  def oldestDirectorAtTheTime(film1: Film, film2: Film) =
    if (film1.directorAge >= film2.directorAge) film1.director
    else film2.director
}

object Runner {
  def main(args: Array[String]): Unit = {
    println(new Director("A", "B", 123).name)

    val eastwood = new Director("Clint", "Eastwood", 1930)
    val mcTiernan = new Director("John", "McTiernan", 1951)
    val nolan = new Director("Christopher", "Nolan", 1970)
    val someBody = new Director("Just", "Some Body", 1990)
    val memento = new Film("Memento", 2000, 8.5, nolan)
    val darkKnight = new Film("Dark Knight", 2008, 9.0, nolan)
    val inception = new Film("Inception", 2010, 8.8, nolan)
    val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7,
      eastwood)
    val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9,
      eastwood)
    val unforgiven = new Film("Unforgiven", 1992, 8.3, eastwood)
    val granTorino = new Film("Gran Torino", 2008, 8.2, eastwood)
    val invictus = new Film("Invictus", 2009, 7.4, eastwood)
    val predator = new Film("Predator", 1987, 7.9, mcTiernan)
    val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
    val huntForRedOctober = new Film("The Hunt for Red October", 1990,
      7.6, mcTiernan)
    val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8,
      mcTiernan)

    println(eastwood.yearOfBirth)
    // res16: Int = 1930
    println(dieHard.director.name)
    // res17: String = John McTiernan
    println(invictus.isDirectedBy(nolan))
    // res18: Boolean = false

    val a = highPlainsDrifter.copy(name = "L'homme des hautes plaines")
    println(a.name)
    // res19: Film = Film(L'homme des hautes plaines,1973,7.7,Director(Clint,Eastwood,1930))
    val b = thomasCrownAffair.copy(yearOfRelease = 1968,
      director = new Director("Norman", "Jewison", 1926))
    println(b.director.name)
    // res20: Film = Film(The Thomas Crown Affair,1968,6.8,Director(Norman,Jewison,1926))
    val c = inception.copy().copy().copy()
    println(c.name)
    // res21: Film = Film(Inception,2010,8.8,Director(Christopher,Nolan ,1970))

    val d1 = Director("John", "Wick", 1990)
    val d2 = Director("Tom", "Hanks", 1999)
    println(Director.older(d1, d2).name)
  }
}
