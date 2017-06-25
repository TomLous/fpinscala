package fpinscala.laziness

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 25/06/17.
  * Copyright © 2017 Datlinq B.V..
  */
class StreamTest extends FunSuite {


//  lazy val testInts = Stream(1,4,5,6,3,9,10)

  test("testToList") {
    assert(Stream(1,4,5,6,3,9,10).toList === List(1,4,5,6,3,9,10))
    assert(Stream("a","b","c").toList === List("a","b","c"))
    assert(Stream().toList === Nil)
  }

  test("testDrop") {

  }

  test("testForAll") {

  }

  test("testTakeWhile") {

  }

  test("testFind") {

  }

  test("testTake") {

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
