package kyu4

import scala.collection.immutable.SortedSet

object TwiceLinear {
  def dblLinear(n: Int): Int = doIteration(SortedSet(1), n)

  @scala.annotation.tailrec
  private def doIteration(sortedSet: SortedSet[Int], index: Int): Int = index match {
    case 0 => sortedSet.head
    case _ => doIteration(sortedSet.tail + (2 * sortedSet.head + 1) + (3 * sortedSet.head + 1), index - 1)
  }
}

// Using streams
//import scala.collection.mutable
//
//object DblLinear {
//  def dblLinear(n: Int): Int = {
//    lazy val dbll: Stream[Int] = 1 #:: dbll.flatMap { n => Array(n * 2 + 1, n * 3 + 1) }
//    (collection.mutable.TreeSet[Int]() ++ dbll.take(n*9).toArray).slice(n, n+1).headOption.get
//  }
//}

// Using queue - little bit lengthy
//import scala.annotation.tailrec
//import scala.collection.immutable.Queue
//
//object DblLinear {
//
//  def y: Int => Int = x => 2 * x + 1
//  def z: Int => Int = x => 3 * x + 1
//
//  def dequeMin(yQueue: Queue[Int], zQueue: Queue[Int]): (Int, Queue[Int], Queue[Int]) = (yQueue, zQueue) match {
//    case (ys, zs) if zs isEmpty => (ys.head, ys.tail, zQueue)
//    case (ys, zs) if ys isEmpty => (zs.head, yQueue, zs.tail)
//    case (ys, zs) if ys.head == zs.head => (zs.head, ys.tail, zs.tail)
//    case (ys, zs) if Math.min(ys.head, zs.head) == ys.head => (ys.head, ys.tail, zs)
//    case (_, _) => (zQueue.head, yQueue, zQueue.tail)
//  }
//
//  def dblLinear(n: Int): Int = {
//
//    @tailrec
//    def go(current: Int, acc: Int, yQueue: Queue[Int], zQueue: Queue[Int]): Int = {
//      if (current == n) acc
//      else {
//        val (min, newYQueue, newZQueue) = dequeMin(yQueue, zQueue)
//        go(current + 1, min, newYQueue enqueue y(min), newZQueue enqueue z(min))
//      }
//    }
//
//    go(0, 1, Queue[Int](3), Queue[Int](4))
//
//  }
//
//}

// Using Vector and sequence, a little bit weird
//object DblLinear {
//  val values = sequence(19).distinct.sorted
//
//  def dblLinear(n: Int): Int = {
//    values(n)
//  }
//
//  def sequence(level:Int, next: Vector[Int] = Vector(1), acc: Vector[Int] = Vector.empty): Vector[Int] =
//    if(level == 0) acc
//    else sequence(level - 1, next.flatMap(generate), acc ++ next)
//
//  def generate(e: Int): Vector[Int] =
//    Vector(2 * e + 1, 3 * e + 1)
//}
