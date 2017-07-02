package fpinscala.laziness

import Stream._

import scala.annotation.tailrec
trait Stream[+A] {

  def toList_StackUnsafe: List[A] = foldRight(List.empty[A])((list, el) => list :: el)

  def toList: List[A] = {
    @tailrec
    def rec(stream: Stream[A], acc: List[A]): List[A] = stream match {
      case Cons(h, t) => rec(t(), h() :: acc)
      case _ => acc
    }

    rec(this, Nil).reverse
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = // The arrow `=>` in front of the argument type `B` means that the function `f` takes its second argument by name and may choose not to evaluate it.
    this match {
      case Cons(h,t) => f(h(), t().foldRight(z)(f)) // If `f` doesn't evaluate its second argument, the recursion never occurs.
      case _ => z
    }

  def exists(p: A => Boolean): Boolean = 
    foldRight(false)((a, b) => p(a) || b) // Here `b` is the unevaluated recursive step that folds the tail of the stream. If `p(a)` returns `true`, `b` will never be evaluated and the computation terminates early.

  @annotation.tailrec
  final def find(f: A => Boolean): Option[A] = this match {
    case Empty => None
    case Cons(h, t) => if (f(h())) Some(h()) else t().find(f)
  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 0 => cons(h(),t().take(n-1))
    case _ => empty
  }

  def take_unfold(n: Int): Stream[A] = unfold((this, n)){
    case (Cons(h, _), n) if n == 1 => Some((h(), (empty[A], 0)))
    case (Cons(h, t), n) if n > 1 => Some((h(), (t(), n-1)))
    case _ => None
  }

  def drop(n: Int): Stream[A] = this match {
    case Cons(_, t) if n > 0 => t().drop(n-1)
    case _ => this
  }

  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => cons(h(),t().takeWhile(p))
    case _ => empty
  }

  def takeWhile_foldRight(p: A => Boolean): Stream[A] = foldRight(empty[A])((h, t) => if(p(h)) cons(h, t) else empty)

  def takeWhile_unfold(p: A => Boolean): Stream[A] = unfold(this){
    case Cons(h,t) if p(h()) => Some((h(), t()))
    case _ => None
  }


  def forAll_case(p: A => Boolean): Boolean = this match {
    case Cons(h, t) => p(h()) && t().forAll(p)
    case _ => true
  }

  def forAll(p: A => Boolean): Boolean = foldRight(true)((a,b) => p(a) && b)

  def headOption: Option[A] = foldRight(Option.empty[A])((h,_) => Some(h))

  // 5.7 map, filter, append, flatmap using foldRight. Part of the exercise is
  // writing your own function signatures.

  def map_case[B](f: A => B):Stream[B] = this match {
    case Cons(h, t) => cons(f(h()), t().map(f))
    case _ => empty
  }

  def map_unfold[B](f: A => B): Stream[B] = unfold(this){
    case Cons(h, t) => Some(f(h()), t())
    case _ => None
  }

  def map[B](f: A => B): Stream[B] = foldRight(empty[B])((h,t) => cons(f(h), t))

  def filter(p: A => Boolean): Stream[A] = foldRight(empty[A])((h,t) => if(p(h)) cons(h,t) else t)

  def append[B>:A](b: => Stream[B]): Stream[B] = foldRight(b)((h,t) => cons(h,t))

  def flatMap[B](f: A => Stream[B]): Stream[B] = foldRight(empty[B])((h,t) => f(h).append(t))

  def zipWith[B,C](s2: Stream[B])(f: (A,B) => C): Stream[C] = unfold((this, s2)){
    case (Cons(h1, t1), Cons(h2, t2)) => Some((f(h1(), h2()), (t1(), t2())))
    case _ => None
  }

  def zipAll[B](s2: Stream[B]): Stream[(Option[A],Option[B])] = unfold((this, s2)){
    case (Cons(h1, t1), Cons(h2, t2)) => Some( ( (Some(h1()), Some(h2())) , (t1(), t2()) ) )
    case (_, Cons(h2, t2)) => Some( ( (None, Some(h2())) , (empty, t2()) ) )
    case (Cons(h1, t1), _) => Some( ( (Some(h1()), None) , (t1(), empty) ) )
    case _ => None
  }

  def startsWith[B](s: Stream[B]): Boolean = ???
}
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty 
    else cons(as.head, apply(as.tail: _*))

  val ones: Stream[Int] = Stream.cons(1, ones)

  def constant[A](a: A): Stream[A] = cons(a, constant(a))

  def from(n: Int): Stream[Int] = cons(n, from(n+1))

  val fibs: Stream[Int] = {
    def next(a: Int, b: Int):Stream[Int] = cons(b, next(b, a+b))
    cons(0, next(0,1))
  }

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case Some((a,b)) => cons(a, Stream.unfold(b)(f))
    case None => empty
  }

  def constant_unfold[A](a: A): Stream[A] = unfold(a)(_ => Some(a, a))

  def from_unfold(n: Int): Stream[Int] = unfold(n)(x => Some(x, x+1))

  val fibs_unfold: Stream[Int] = cons(0, unfold((0,1)) {
    case (a,b) => Some((b, (b, a+b)))
  })

  val ones_unfold: Stream[Int] = unfold(1)(_ => Some(1, 1))

}