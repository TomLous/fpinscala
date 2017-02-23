package fpinscala.gettingstarted

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 23/02/17.
  * Copyright © 2017 Datlinq B.V..
  */
class MyModule$Test extends FunSuite {

  test("abs") {
    assert(MyModule.abs(-43) === 43)
    assert(MyModule.abs(43) === 43)
    assert(MyModule.abs(0) === 0)
    assert(MyModule.abs(-5) === 5)
  }

}
