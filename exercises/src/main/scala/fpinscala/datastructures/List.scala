package fpinscala.datastructures

import scala.annotation.tailrec

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar


  def tail[A](l: List[A]): List[A] =  l match {
    case Nil => sys.error("undefined")
    case Cons(_, t) => t
  }

  def setHead[A](l: List[A], h: A): List[A] = l match {
    case Nil => sys.error("undefined")
    case Cons(_, t) => Cons(h, t)
  }

  def drop[A](l: List[A], n: Int): List[A] = l match {
    case _ if n==0 => l
    case Nil => sys.error("undefined")
    case Cons(_, t) if n > 0 => drop(t, n-1)
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhile(t, f)
    case _ => l

  }


  def init[A](l: List[A]): List[A] = l match {
    case Nil => sys.error("undefined")
    case Cons(_, Nil) => Nil
    case Cons(h, t) => Cons(h, init(t))
  }

  def length[A](l: List[A]): Int = foldRight(l,0)((_,b)=>b+1)

  @tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(h, t) => foldLeft(t,  f(z, h))(f)
  }

  def sumLeft(l:List[Int]):Int = foldLeft(l, 0)(_ + _)
  def productLeft(l:List[Double]):Double = foldLeft(l, 1.0)(_ * _)
  def lengthLeft[A](l:List[A]):Int = foldLeft(l, 0)((b, _) => b + 1)
  def reverse[A](l:List[A]):List[A] = foldLeft(l, List[A]())((b, a) => Cons(a, b))


  def foldRightViaFoldLeft[A,B](l: List[A], z: B)(f: (A,B) => B): B = foldLeft(reverse(l),z)((b,a)=>f(a,b))
  def foldLeftViaFoldRight[A,B](l: List[A], z: B)(f: (B,A) => B): B = foldRight(l, (b:B) => b)((a,g) => b => g(f(b,a)))(z)

  def appendRight[A](a1: List[A], a2: List[A]): List[A] = foldRight(a1, a2)(Cons(_, _))

  def concat[A](l: List[List[A]]): List[A] = foldLeft(l, List[A]())(appendRight)

  def increment(l: List[Int]):List[Int] = foldRight(l, List[Int]())((i,nl) => Cons(i+1, nl))

  def doubleToString(l: List[Double]):List[String] = foldRight(l, List[String]())((d,nl)=>Cons(d.toString, nl))

  def map[A,B](l: List[A])(f: A => B): List[B] = foldRightViaFoldLeft(l, List[B]())((a,b)=>Cons(f(a),b))

  def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] = foldRightViaFoldLeft(l, List[B]())((a,b)=>appendRight(f(a),b))

  def filter[A](l: List[A])(f: A => Boolean): List[A] = foldRightViaFoldLeft(l, List[A]())((a,b) => if(f(a)) Cons(a, b) else b)

  def filter2[A](l: List[A])(f: A => Boolean): List[A] = flatMap(l)(a => if(f(a)) List(a) else Nil)

  def zipAndSum(l1: List[Int], l2: List[Int]): List[Int] = (l1, l2) match {
    case (Nil, b) => b
    case (a, Nil) => a
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, zipAndSum(t1, t2))
  }

  def zipWith[A,B,C](l1: List[A], l2: List[B])(f: (A,B) => C): List[C] = (l1, l2) match {
    case (Nil, b) => Nil
    case (a, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
  }


  @tailrec
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = (sup,sub) match {
    case (_, Nil) => true
    case (Nil, _) => false
    case (Cons(_, supTail), _)  => {
      @tailrec
      def sameFromNow(ssup: List[A], ssub: List[A]): Boolean = (ssup,ssub) match {
        case (_, Nil) => true
        case (Nil, _) => false
        case (Cons(ssupHead, ssupTail), Cons(ssubHead, ssubTail)) =>
          if(ssupHead==ssubHead) sameFromNow(ssupTail, ssubTail)
          else false
      }
      sameFromNow(sup, sub) || hasSubsequence(supTail, sub)
    }
  }
  /* = (sup, sub) match {
    case (_, Nil) => true
    case (Nil, _) => false
    case (Cons(ha,ta), Cons(hb,tb)) => {
      def ss(sup2: List[A], sub2:List[A]): Boolean = (sup2, sub2) match {
        case (_, Nil) => true
        case (Nil, _) => false
        case (Cons(ha2,ta2), Cons(hb2,tb2)) if ha2==hb2 => ss(ta2, tb2)
      }
      if(ha == ha) ss(ta,tb) else hasSubsequence(ta, sub)
    }
  }*/
}
