package kyu7

import org.scalatest.FlatSpec

class OrderedCountOfCharactersTest extends FlatSpec {
  it should "work with example tests" in {
    assert(OrderedCountOfCharacters.orderedCount("abracadabra") == List[(Char, Int)](('a', 5), ('b', 2), ('r', 2), ('c', 1), ('d', 1)))
    assert(OrderedCountOfCharacters.orderedCount("Code Wars") == List[(Char, Int)](('C', 1), ('o', 1), ('d', 1), ('e', 1), (' ', 1), ('W', 1), ('a', 1), ('r', 1), ('s', 1)))
  }
}
