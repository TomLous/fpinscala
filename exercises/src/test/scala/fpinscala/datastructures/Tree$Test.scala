package fpinscala.datastructures

import org.scalatest.FunSuite

/**
  * Created by Tom Lous on 28/02/17.
  * Copyright © 2017 Datlinq B.V..
  */
class Tree$Test extends FunSuite {

  test("testSize"){
    assert(Tree.size(Branch(Branch(Leaf(1),Leaf(2)),Branch(Leaf(3),Leaf(4))))==7)
    assert(Tree.size(Leaf(1))==1)
    assert(Tree.size(Branch(Leaf(1),Branch(Leaf(2),Branch(Leaf(3),Leaf(4)))))==7)

  }

  test("testMax"){
    assert(Tree.maxInt(Branch(Branch(Leaf(1),Leaf(8)),Branch(Leaf(2),Leaf(5))))==8)
    assert(Tree.maxInt(Leaf(19))==19)
    assert(Tree.maxInt(Branch(Leaf(12),Branch(Leaf(23),Branch(Leaf(3),Leaf(14)))))==23)

  }
}
