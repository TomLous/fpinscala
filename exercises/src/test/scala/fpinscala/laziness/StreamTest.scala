package fpinscala.laziness

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 25/06/17.
  * Copyright © 2017 Datlinq B.V..
  */
class StreamTest extends FunSuite {


//  lazy val testInts = Stream(1,4,5,6,3,9,10)

  test("testToList_StackUnsafe") {
    assert(Stream(1,4,5,6,3,9,10).toList_StackUnsafe === List(1,4,5,6,3,9,10))
    assert(Stream("a","b","c").toList_StackUnsafe === List("a","b","c"))
    assert(Stream().toList_StackUnsafe === Nil)
  }

  test("testToList") {
    assert(Stream(1,4,5,6,3,9,10).toList === List(1,4,5,6,3,9,10))
    assert(Stream("a","b","c").toList=== List("a","b","c"))
    assert(Stream().toList === Nil)
  }

  test("testDrop") {
    assert(Stream(1,4,5,6,3,9,10).drop(3).toList === List(6,3,9,10))
    assert(Stream("a","b","c").drop(3).toList === Nil)
    assert(Stream("a","b","c").drop(5).toList === Nil)
  }

  test("testForAll") {
    assert(Stream(1,5,7,3,9,11).forAll(_ % 2 == 1))
    assert(!Stream(1,5,7,4,9,11).forAll(_ % 2 == 1))
    assert(!Stream(1,5,7,4,9,11).forAll(_ > 1))
    assert(Stream(1,5,7,4,9,11).forAll(_ > 0))
  }

  test("testForAll_case") {
    assert(Stream(1,5,7,3,9,11).forAll_case(_ % 2 == 1))
    assert(!Stream(1,5,7,4,9,11).forAll_case(_ % 2 == 1))
    assert(!Stream(1,5,7,4,9,11).forAll_case(_ > 1))
    assert(Stream(1,5,7,4,9,11).forAll_case(_ > 0))
  }

  test("testTakeWhile") {
    assert(Stream(2,4,6,7,3,9,10).takeWhile(_ % 2 == 0).toList === List(2,4,6))
    assert(Stream(2,1,6,7,3,9,10).takeWhile(_ < 2).toList === List())
    assert(Stream(2,1,6,7,3,9,10).takeWhile(_ > 0).toList === List(2,1,6,7,3,9,10))
  }

  test("testTakeWhileFoldRight") {
    assert(Stream(2,4,6,7,3,9,10).takeWhileFoldRight(_ % 2 == 0).toList === List(2,4,6))
    assert(Stream(2,1,6,7,3,9,10).takeWhileFoldRight(_ < 2).toList === List())
    assert(Stream(2,1,6,7,3,9,10).takeWhileFoldRight(_ > 0).toList === List(2,1,6,7,3,9,10))
  }

  test("testFind") {

  }

  test("testTake") {
    assert(Stream(1,4,5,6,3,9,10).take(3).toList === List(1,4,5))
    assert(Stream(1,4,5,6,3,9,10).take(0).toList === Nil)
    assert(Stream("a","b","c").take(3).toList === List("a","b","c"))
    assert(Stream("a","b","c").take(5).toList === List("a","b","c"))
  }

  test("testHeadOption") {

  }

  test("testExists") {

  }

  test("testFoldRight") {

  }

  test("testStartsWith") {

  }

}
