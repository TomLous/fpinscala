package fpinscala.datastructures

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 24/02/17.
  * Copyright © 2017 Datlinq B.V..
  */
class List$Test extends FunSuite {

  test("testFoldLeft") {

  }

  test("testSetHead") {
    assert(List.setHead(List(1,2,3), 4) == List(4,2,3))
    assert(List.setHead(List(1), 4) == List(4))
    assertThrows[RuntimeException]{
      List.setHead(Nil, 3)
    }
  }

  test("testProduct2") {

  }

  test("testInit") {
    assert(List.init(List(1,2,3)) == List(1,2))
    assert(List.init(List(1,2,3,4,5,6)) == List(1,2,3,4,5))
    assert(List.init(List(1)) == Nil)
    assertThrows[RuntimeException]{
      List.init(Nil)
    }
  }

  test("testSum2") {

  }

  test("testProduct") {

  }

  test("testLength") {

  }

  test("testDrop") {
    assert(List.drop(List(1,2,3,4,5),2) == List(3,4,5))
    assert(List.drop(List(1,2,3,4,5),5) == Nil)
    assertThrows[RuntimeException]{
      List.drop(List(1,2),3)
    }
  }

  test("testAppend") {

  }

  test("testTail") {
    assert(List.tail(List(1,2,3)) == List(2,3))
    assert(List.tail(List(1)) == Nil)
    assertThrows[RuntimeException]{
      List.tail(Nil)
    }
  }

  test("testX") {
    assert(List.x === 3)
  }

  test("testDropWhile") {

    def f1 = (x:Int) => x %2 ==0

    assert(List.dropWhile(List(2,4,2,3,2,3,1), f1) == List(3,2,3,1))
    assert(List.dropWhile(List(3,2,3,1), f1) == List(3,2,3,1))
    assert(List.dropWhile(List(2,4,2), f1) == Nil)

  }

  test("testSum") {

  }

  test("testFoldRight") {

  }

  test("testApply") {

  }

  test("testMap") {

  }

}
