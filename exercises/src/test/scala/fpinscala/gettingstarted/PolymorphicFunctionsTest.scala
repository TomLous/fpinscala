package fpinscala.gettingstarted

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 23/02/17.
  * Copyright © 2017 Datlinq B.V..
  */
class PolymorphicFunctionsTest extends FunSuite {

  test("testUncurry") {
    def f1(x: String) = (y: String) => x.length + y.length
    def f2(x: Int) = (y: Long) => x * y / 3.0


    val cf1:(String, String) => Int = PolymorphicFunctions.uncurry(f1)
    val cf2:(Int, Long) => Double = PolymorphicFunctions.uncurry(f2)

    assert(cf1("hoi","hoe") == 6)
    assert(cf2(9,9) == 27.0)
  }

  test("testCompose") {
    def f1(x:String):Int = x.length
    def f2(y:Int): Double = y / 3

    val cf1:(String) => Double = PolymorphicFunctions.compose(f2, f1)

    assert(cf1("how") === 1.0)
    assert(cf1("howhow") === 2.0)

  }

  test("testBinarySearch") {

  }

  test("testIsSorted") {
    def lt = (x:Int, y:Int) => x < y

    assert(PolymorphicFunctions.isSorted(Array(1,2,3,4,5,6,7), lt))
    assert(! PolymorphicFunctions.isSorted(Array(1,2,1,4,5,6,7), lt))
    assert(PolymorphicFunctions.isSorted(Array(1,3,6,7), lt))
    assert(PolymorphicFunctions.isSorted(Array(-10,-2,5,6,7), lt))
  }

  test("testCurry") {
    def f1(x: String, y: String):Int = x.length + y.length
    def f2(x: Int, y: Long):Double = x * y / 3


    val cf1:(String) => (String) => Int = PolymorphicFunctions.curry(f1)
    val cf2:(Int) => (Long) => Double = PolymorphicFunctions.curry(f2)

    assert(cf1("hoi")("hoe") == 6)
    assert(cf2(9)(9) == 27.0)

  }

  test("testPartial1") {

  }

}
