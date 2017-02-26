package fpinscala.datastructures

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 24/02/17.
  * Copyright © 2017 Datlinq B.V..
  */
class List$Test extends FunSuite {

  test("testFoldLeft") {
    assert(List.foldLeft(List(1,2,3), 1)(_ * _) == 6)
    assert(List.foldLeft(List(1,2,3), 0)(_ + _) == 6)
  }

  test("testSetHead") {
    assert(List.setHead(List(1,2,3), 4) == List(4,2,3))
    assert(List.setHead(List(1), 4) == List(4))
    assertThrows[RuntimeException]{
      List.setHead(Nil, 3)
    }
  }

  test("testProductLeft") {
    assert(List.productLeft(List(1,2,3))== 6)
    assert(List.productLeft(List(1,2,3,4)) == 24)
    assert(List.productLeft(Nil) == 1)
  }

  test("testInit") {
    assert(List.init(List(1,2,3)) == List(1,2))
    assert(List.init(List(1,2,3,4,5,6)) == List(1,2,3,4,5))
    assert(List.init(List(1)) == Nil)
    assertThrows[RuntimeException]{
      List.init(Nil)
    }
  }

  test("testSumLeft") {
    assert(List.sumLeft(List(1,2,3))== 6)
    assert(List.sumLeft(List(1,2,3,4)) == 10)
    assert(List.sumLeft(Nil) == 0)
  }

  test("testLengthLeft") {
    assert(List.lengthLeft(List(1,2,3,4,5)) == 5)
    assert(List.lengthLeft(Nil) == 0)
    assert(List.lengthLeft(List(1,2,3)) == 3)
  }

  test("testLength") {
    assert(List.length(List(1,2,3,4,5)) == 5)
    assert(List.length(Nil) == 0)
    assert(List.length(List(1,2,3)) == 3)
  }

  test("testDrop") {
    assert(List.drop(List(1,2,3,4,5),2) == List(3,4,5))
    assert(List.drop(List(1,2,3,4,5),5) == Nil)
    assertThrows[RuntimeException]{
      List.drop(List(1,2),3)
    }
  }

  test("testAppendRight") {
    assert(List.appendRight(List(1,2,3,4,5),List(6,7,8)) == List(1,2,3,4,5,6,7,8))
    assert(List.appendRight(List(1,2,3,4,5),Nil) == List(1,2,3,4,5))
    assert(List.appendRight(Nil,List(1,2,3,4,5)) == List(1,2,3,4,5))
    assert(List.appendRight(List(1),List(2)) == List(1,2))
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

  test("testReverse") {
    assert(List.reverse(List(1,2,3,4,5)) == List(5,4,3,2,1))
    assert(List.reverse(Nil) == Nil)
    assert(List.reverse(List(1,2,3)) == List(3,2,1))
  }

  test("testConcat") {
    assert(List.concat(List(List(1,2,3),List(4,5),Nil,List(6))) == List(1,2,3,4,5,6))
    assert(List.concat(List(List(1),List(4))) == List(1,4))
  }

  test("testApply") {

  }

  test("testMap") {

  }

}
