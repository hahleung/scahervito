package kyu3

import java.lang.reflect.Modifier
import java.util.Random

case class RichString(s: String) {
  def wut: String = "WUT"
}

case class RichMath(m: Math) {
  def randomWow: Double = 0.1D
}

object Psychic {
  implicit def stringToString(s: String): RichString = RichString(s)

  implicit def superRandom(m: Math): RichMath = RichMath(m)

  def guess(): Double = {
    println(Math.random)
    println("feae".wut)
    1D
  }
}

//object Psychic {
//  def guess: Double = {
//    try {
//      val cheatRandom = new Psychic.CheatRandom
//      val clazz: Class[_] = Class.forName("java.lang.Math$RandomNumberGeneratorHolder")
//      val field = clazz.getDeclaredField("randomNumberGenerator")
//      field.setAccessible(true)
//
//      val modifiersField = classOf[Nothing].getDeclaredField("modifiers")
//      modifiersField.setAccessible(true)
//      modifiersField.setInt(field, field.getModifiers & ~Modifier.FINAL)
//      field.set(null, cheatRandom)
//    } catch {
//      case e@(_: ClassNotFoundException | _: IllegalAccessException | _: NoSuchFieldException) =>
//        e.printStackTrace()
//    }
//    Math.random
//  }
//
//  private class CheatRandom extends Random {
//    override def nextDouble = 0.1
//  }
//
//}
