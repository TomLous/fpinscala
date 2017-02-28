package fpinscala.datastructures

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]


object Tree {

  def size[A](t:Tree[A]):Int = t match {
    case Leaf(_) => 1
    case Branch(l,r) => 1 + size(l) + size(r)
  }

  def maximum(t:Tree[Int]):Int = t match {
    case Leaf(a) => a
    case Branch(l,r) =>  maximum(l) max maximum(r)
  }

  def depth[A](t:Tree[A]):Int = {
    def dsub(st:Tree[A], currentDepth:Int):Int = st match {
      case Leaf(_) => currentDepth+1
      case Branch(l,r) => dsub(l,currentDepth+1) max dsub(r, currentDepth+1)
    }
    dsub(t,0)
  }

  def depth2[A](t:Tree[A]):Int = t match {
    case Leaf(_) => 1
    case Branch(l,r) => 1 + (depth(l) max depth(r))
  }
}