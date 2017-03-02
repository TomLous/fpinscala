package fpinscala.errorhandling

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 02/03/17.
  * Copyright © 2017 Datlinq B.V..
  */
class OptionTest extends FunSuite {

  test("testFlatMap") {

  }

  test("testFilter") {

  }

  test("testGetOrElse") {

  }

  test("testOrElse") {

  }

  test("testMap") {
    val s = Some(2)
    val n:Option[Int] = None
    assert(s.map(_ * 3) == Some(6))
    assert(n.map(_ * 3) == None)

  }

}
