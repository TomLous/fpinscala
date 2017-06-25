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
    assert(Stream(1,4,5,6,3,9,10).headOption === Some(1))
    assert(Stream(4,5,6,3,9,10).headOption === Some(4))
    assert(Stream().headOption === None)
  }

  test("testMap") {
    assert(Stream(1,4,5,6,3,9,10).map(_ * 2).toList === List(2,8,10,12,6,18,20))
    assert(Stream(1,4,5,6,3,9,10).map(x => s"h$x").toList === List("h1","h4","h5","h6","h3","h9","h10"))
  }

  test("testMap_case") {
    assert(Stream(1,4,5,6,3,9,10).map_case(_ * 2).toList === List(2,8,10,12,6,18,20))
    assert(Stream(1,4,5,6,3,9,10).map_case(x => s"h$x").toList === List("h1","h4","h5","h6","h3","h9","h10"))
  }

  test("testFilter") {
    assert(Stream(1,4,5,6,3,9,10).filter(_ % 2 == 1).toList === List(1,5,3,9))
    assert(Stream(1,4,5,6,3,9,10).filter(_ < 1).toList === List())
    assert(Stream(1,4,5,6,3,9,10).filter(_ >= 1).toList === List(1,4,5,6,3,9,10))
  }

  test("testAppend") {
    assert(Stream(1,4,5).append(Stream(6,9,11)).toList === List(1,4,5,6,9,11))
    assert(Stream(1,4,5).append(Stream(8.9, 9.3)).toList === List(1.0,4.0,5.0,8.9,9.3))
    assert(Stream(1,4,5).append(Stream()).toList === List(1,4,5))
    assert(Stream().append(Stream(1,4,5)).toList === List(1,4,5))
  }

  test("testFlatMap") {
    assert(Stream(1,4,5).flatMap(x => Stream(x, x*2, x*3)).toList === List(1,2,3,4,8,12,5,10,15))
    assert(Stream(1,4,5).flatMap(x => Stream(x)).toList === List(1,4,5))
    assert(Stream(1,4,5).flatMap(_ => Stream()).toList === List())
    assert(Stream[Int]().flatMap(x => Stream(x, x*2, x*3)).toList === List())
  }

  test("testConstant") {
    assert(Stream.constant(3).take(4).toList === List(3, 3, 3, 3))
    assert(Stream.constant("x").take(2).toList === List("x", "x"))
  }


  test("testFrom") {
    assert(Stream.from(3).take(4).toList === List(3, 4, 5, 6))
    assert(Stream.from(123).take(2).toList === List(123,124))
  }



  test("testExists") {

  }

  test("testFoldRight") {

  }

  test("testStartsWith") {

  }

}
