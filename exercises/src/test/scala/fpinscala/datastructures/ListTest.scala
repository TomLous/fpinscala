package fpinscala.datastructures

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 24/02/17.
  * Copyright © 2017 Datlinq B.V..
  */
class ListTest extends FunSuite {

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

  test("testIncrement") {
    assert(List.increment(List(1,2,3)) === List(2,3,4))
  }

  test("testDoubleToString") {
    assert(List.doubleToString(List(1.3,2.4,3.5)) === List("1.3", "2.4", "3.5"))
  }

  test("testFilter") {
    assert(List.filter(List(1,2,3,4,5,6,7))(_ % 2 == 0) === List(2,4,6))
  }

  test("testFilter2") {
    assert(List.filter2(List(1,2,3,4,5,6,7))(_ % 2 == 0) === List(2,4,6))
  }

  test("testFlatMap") {
    assert(List.flatMap(List(1,2,3))(i => List(i,i)) === List(1,1,2,2,3,3))
  }

  test("testZipAndSum") {
    assert(List.zipAndSum(List(1,2,3),List(4,5,6)) === List(5,7,9))
  }

  test("testZipWith") {
    assert(List.zipWith(List(1,2,3),List(4.1,5.2,6.3))(_ * _) === List(4.1,10.4,18.9))
  }


  test("testMap") {
    assert(List.map(List(1,2,3))(_.toString) == List("1","2","3"))
    assert(List.map(List(1,2,3))(_ / 3) == List(1/3,2/3,3/3))
  }

  test("testHasSubsequence") {
    assert(List.hasSubsequence(List(1,2,3), List(1,2)))
    assert(List.hasSubsequence(List(1,2,3,4,5,6), List(3,4,5)))
    assert(!List.hasSubsequence(List(1,2,3,5,6), List(3,4,5)))

  }
}
