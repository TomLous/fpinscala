package fpinscala.state

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 02/07/17.
  * Copyright © 2017 Datlinq B.V..
  */
class RNGTest extends FunSuite {

  test("testNonNegativeInt") {
    val rng = RNG.Simple(11081979)
    assert(RNG.nonNegativeInt(rng)._1 === -rng.nextInt._1 + 1)
    val rng2 = RNG.Simple(17071979)
    assert(RNG.nonNegativeInt(rng2)._1 === rng2.nextInt._1)

  }

  test("testDouble") {
    val rng = RNG.Simple(11081979)
    assert(RNG.nonNegativeInt(rng)._1.toDouble / (Int.MaxValue + 1) == RNG.double(rng)._1)
  }

  test("testMap") {

  }

  test("testIntDouble") {

  }

  test("testMap2") {

  }

  test("testDoubleInt") {

  }

  test("testFlatMap") {

  }

  test("testSequence") {

  }

  test("testUnit") {

  }

  test("testDouble3") {

  }

  // @todo bookmark p.83  / 6.3
}
