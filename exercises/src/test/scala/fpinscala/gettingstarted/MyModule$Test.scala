package fpinscala.gettingstarted

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 23/02/17.
  * Copyright © 2017 Datlinq B.V..
  */
class MyModule$Test extends FunSuite {

  test("testAbs") {
    assert(MyModule.abs(-43) === 43)
    assert(MyModule.abs(43) === 43)
    assert(MyModule.abs(0) === 0)
    assert(MyModule.abs(-5) === 5)
  }

  test("testFactorial"){
    assert(MyModule.factorial(3) === 1*2*3)
    assert(MyModule.factorial(5) === 1*2*3*4*5)
    assert(MyModule.factorial(7) === 1*2*3*4*5*6*7)
    assert(MyModule.factorial(1) === 1)
    assert(MyModule.factorial(0) === 1)
    assert(MyModule.factorial(-3) === 1)
  }

  test("testFib"){
    assert(MyModule.fib(1) === 0)
    assert(MyModule.fib(2) === 1)
    assert(MyModule.fib(3) === 1)
    assert(MyModule.fib(4) === 2)
    assert(MyModule.fib(5) === 3)
    assert(MyModule.fib(6) === 5)
    assert(MyModule.fib(7) === 8)
  }

}
