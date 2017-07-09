package fpinscala.state

import fpinscala.state.RNG.Rand
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
    assert(RNG.nonNegativeInt(rng)._1.toDouble / (Int.MaxValue + 1) === RNG.double(rng)._1)
  }

  test("testDoubleMap") {
    val rng = RNG.Simple(11081979)

    val x:Rand[Double] = RNG.doubleMap
    assert(RNG.nonNegativeInt(rng)._1.toDouble / (Int.MaxValue + 1) === RNG.get(x)(rng))

  }

  test("testInts") {
    val rng = RNG.Simple(11081979)
    assert(RNG.ints(4)(rng)._1.size === 4 )
    assert(RNG.ints(1)(rng)._1.size === 1 )
    assert(RNG.ints(0)(rng)._1.size === 0 )
    assert(RNG.ints(1)(rng)._2 !== rng )

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
    val rng = RNG.Simple(11081979)

    val res= RNG.map2(RNG.int, RNG.int)(_ + _)(rng)

    val (a, rng2) = rng.nextInt
    val (b, rng3) = rng2.nextInt

    assert(a+b === res._1)
    assert(rng3 === res._2)

  }

  test("testMap2FlatMap") {
    val rng = RNG.Simple(11081979)

    val res= RNG.map2(RNG.int, RNG.int)(_ + _)(rng)

    val (a, rng2) = rng.nextInt
    val (b, rng3) = rng2.nextInt

    val res2 = RNG.map2FlatMap(RNG.int, RNG.int)(_ + _)(rng)

    assert(a+b === res._1 )
    assert(a+b === res2._1)
    assert(rng3 === res._2)
    assert(rng3 === res2._2)

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
