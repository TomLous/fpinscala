package fpinscala.testing

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 12/04/2018.
  * Copyright © 2018 Datlinq B.V..
  */
class PropTest extends FunSuite {

  test("test$amp$amp") {

    class PT extends Prop{
      override def check: Boolean = true
    }

    class PF extends Prop{
      override def check: Boolean = false
    }

    val p1 = new PT
    val p2 = new PT
    val p3 = new PF
    val p4 = new PF

    assert((p1 && p2).check)
    assert(!(p1 && p3).check)
    assert(!(p4 && p3).check)

  }

  test("testCheck") {

  }

}
