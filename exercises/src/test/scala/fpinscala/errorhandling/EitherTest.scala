package fpinscala.errorhandling

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 03/03/17.
  * Copyright © 2017 Datlinq B.V..
  */
class EitherTest extends FunSuite {

  test("testMap2") {
    def f(x:Int, y:Int):Int = x + y
    val l = Left("ERROR")
    val l2 = Left("ERROR2")
    val r = Right(3)
    val r2 = Right(5)

    assert(r.map2(r2)(f) == Right(8))
    assert(r.map2(l)(f) == l)
    assert(l.map2(r)(f) == l)
    assert(l2.map2(l)(f) == l2)


  }

  test("testFlatMap") {
    val l = Left("ERROR")
    val r = Right(3)
    val r2 = Right(0)
    def f(x:Int):Either[String,Int] = if(x==0) l else Right(4/x)

    assert(r.flatMap(f) == Right(4/3))
    assert(r2.flatMap(f) == l)
    assert(l.flatMap(f) == l)
  }

  test("testOrElse") {
    val l = Left("ERROR")
    val r = Right(3)
    val r2 = Right(0)
    def f(x:Int):Either[String,Int] = if(x==0) l else Right(4/x)

    assert(r.orElse(r2) == r)
    assert(l.orElse(r2) == r2)
  }

  test("testMap") {
    val l = Left("ERROR")
    val r = Right(3)
    def f(x:Int):Int = x+3

    assert(r.map(f) == Right(6))
    assert(l.map(f) == l)

  }

}
