package fpinscala.datastructures

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 28/02/17.
  * Copyright © 2017 Datlinq B.V..
  */
class TreeTest extends FunSuite {

  test("testSize") {
    assert(Tree.size(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4)))) == 7)
    assert(Tree.size(Leaf(1)) == 1)
    assert(Tree.size(Branch(Leaf(1), Branch(Leaf(2), Branch(Leaf(3), Leaf(4))))) == 7)

  }

  test("testMaximum") {
    assert(Tree.maximum(Branch(Branch(Leaf(1), Leaf(8)), Branch(Leaf(2), Leaf(5)))) == 8)
    assert(Tree.maximum(Leaf(19)) == 19)
    assert(Tree.maximum(Branch(Leaf(12), Branch(Leaf(23), Branch(Leaf(3), Leaf(14))))) == 23)

  }


  test("testDepth") {
    assert(Tree.depth(Branch(Branch(Leaf(1), Leaf(8)), Branch(Leaf(2), Leaf(5)))) == 3)
    assert(Tree.depth(Leaf(19)) == 1)
    assert(Tree.depth(Branch(Leaf(12), Branch(Leaf(23), Branch(Leaf(3), Leaf(14))))) == 4)

  }

  test("testDepth2") {
    assert(Tree.depth2(Branch(Branch(Leaf(1), Leaf(8)), Branch(Leaf(2), Leaf(5)))) == 3)
    assert(Tree.depth2(Leaf(19)) == 1)
    assert(Tree.depth2(Branch(Leaf(12), Branch(Leaf(23), Branch(Leaf(3), Leaf(14))))) == 4)

  }


  test("testMap") {
    assert(Tree.map(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(2), Leaf(4))))(x => x * 2.2) == Branch(Branch(Leaf(2.2), Leaf(4.4)), Branch(Leaf(4.4), Leaf(8.8))))
    assert(Tree.map(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4))))(x => x.toString + "a") == Branch(Branch(Leaf("1a"), Leaf("2a")), Branch(Leaf("3a"), Leaf("4a"))))
    assert(Tree.map(Leaf("abcde"))(x => x.length) == Leaf(5))

  }

  test("testSizeFold") {
    assert(Tree.sizeFold(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4)))) == 7)
    assert(Tree.sizeFold(Leaf(1)) == 1)
    assert(Tree.sizeFold(Branch(Leaf(1), Branch(Leaf(2), Branch(Leaf(3), Leaf(4))))) == 7)

  }

  test("testMaximumFold") {
    assert(Tree.maximumFold(Branch(Branch(Leaf(1), Leaf(8)), Branch(Leaf(2), Leaf(5)))) == 8)
    assert(Tree.maximumFold(Leaf(19)) == 19)
    assert(Tree.maximumFold(Branch(Leaf(12), Branch(Leaf(23), Branch(Leaf(3), Leaf(14))))) == 23)

  }

  test("testDepthFold") {
    assert(Tree.depthFold(Branch(Branch(Leaf(1), Leaf(8)), Branch(Leaf(2), Leaf(5)))) == 3)
    assert(Tree.depthFold(Leaf(19)) == 1)
    assert(Tree.depthFold(Branch(Leaf(12), Branch(Leaf(23), Branch(Leaf(3), Leaf(14))))) == 4)

  }
}
