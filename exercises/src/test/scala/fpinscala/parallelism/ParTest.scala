package fpinscala.parallelism

import java.util.concurrent.{ExecutorService, Executors}

import fpinscala.parallelism.Par.Par
import org.scalatest.FunSuite

import scala.concurrent.ExecutionContext

/**
  * Created by Tom Lous on 05/04/2018.
  * Copyright © 2018 Datlinq B.V..
  */
class ParTest extends FunSuite {


  val ec: ExecutorService = Executors.newFixedThreadPool(4)


  test("Par"){
    val p1 = Par.unit(1)
    var p2  = Par.unit(2)
    val x = Par.run(ec)(p1)
    val y = Par.run(ec)(p2)

    val p3:Par[Int] = Par.map2(p1, p2)(_ + _)
    val z = Par.run(ec)(p3)

    println(x.get, y.get ,z.get)



  }

  test("testToParOps") {

  }

  test("testRun") {

  }

  test("testAsyncF") {

  }

  test("testMap") {

  }

  test("testLazyUnit") {

  }

  test("testMap2") {

  }

  test("testEqual") {

  }

  test("testSortPar") {

  }

  test("testFork") {

  }

  test("testUnit") {

  }

  test("testDelay") {

  }

  test("testChoice") {

  }

}
