package fpinscala.gettingstarted

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 23/02/17.
  * Copyright © 2017 Datlinq B.V..
  */
class PolymorphicFunctions$Test extends FunSuite {

  test("testUncurry") {

  }

  test("testCompose") {

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

  }

  test("testPartial1") {

  }

}
