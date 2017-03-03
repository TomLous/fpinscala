package fpinscala.errorhandling

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 02/03/17.
  * Copyright © 2017 Datlinq B.V..
  */
class OptionTest extends FunSuite {

  test("testFlatMap") {
    val s = Some(2)
    val s2 = Some(0)
    val n:Option[Int] = None

    val f = (x:Int) => if(x!=0) Some(5/x) else None

    assert(s.flatMap(f) == Some(5/2))
    assert(s2.flatMap(f) == None)
    assert(n.flatMap(f) == None)

  }

  test("testFilter") {
    val s = Some(2)
    val s3 = Some(3)
    val n:Option[Int] = None
    assert(s.filter(_ % 2 ==0) == Some(2))
    assert(s3.filter(_ % 2 ==0) == None)
    assert(n.filter(_ % 2 ==0) == None)
  }

  test("testGetOrElse") {
    val s = Some(2)
    val n:Option[Int] = None
    assert(s.getOrElse(3) == 2)
    assert(n.getOrElse(3) == 3)
  }

  test("testOrElse") {

    val s = Some(2)
    val n:Option[Int] = None
    assert(s.orElse(Some(3)) == Some(2))
    assert(n.orElse(Some(3)) == Some(3))
  }

  test("testMap") {
    val s = Some(2)
    val n:Option[Int] = None
    assert(s.map(_ * 3) == Some(6))
    assert(n.map(_ * 3) == None)

  }

}
