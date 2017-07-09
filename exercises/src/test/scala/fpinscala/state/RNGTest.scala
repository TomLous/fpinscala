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

  test("testIntDouble == testDoubleInt") {
    val rng = RNG.Simple(11081979)
    val ((idI, idD), idRNG) = RNG.intDouble(rng)
    val ((diD, diI), diRNG) = RNG.doubleInt(rng)
    assert(idI === diI)
    assert(diD === idD)
    assert(diRNG === idRNG)
  }

  test("testMap2") {

  }



  test("testFlatMap") {

  }

  test("testSequence") {

  }

  test("testUnit") {

  }

  test("testDouble3") {
    val rng = RNG.Simple(11081979)
    val ((d1, d2, d3), _) = RNG.double3(rng)
    assert(d1 !== d2)
    assert(d1 !== d3)
    assert(d2 !== d3)
  }

  // @todo bookmark p.83  / 6.3
}
