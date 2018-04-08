package fpinscala.state

import scala.annotation.tailrec


trait RNG {
  def nextInt: (Int, RNG) // Should generate a random `Int`. We'll later define other functions in terms of `nextInt`.
}

object RNG {

  // NB - this was called SimpleRNG in the book text

  case class Simple(seed: Long) extends RNG {
    def nextInt: (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL // `&` is bitwise AND. We use the current seed to generate a new seed.
      val nextRNG = Simple(newSeed) // The next state, which is an `RNG` instance created from the new seed.
      val n = (newSeed >>> 16).toInt // `>>>` is right binary shift with zero fill. The value `n` is our new pseudo-random integer.
      (n, nextRNG) // The return value is a tuple containing both a pseudo-random integer and the next `RNG` state.
    }
  }

  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] =
    rng => (a, rng)

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }

  def get[A](s: Rand[A])(rng: RNG): A = s(rng)._1

  def nonNegativeInt(rng: RNG): (Int, RNG) = rng.nextInt match {
    case (n, rng2) if n >= 0 => (n, rng2)
    case (n, rng2) if n < 0 => (-n + 1, rng2)
  }

  def double(rng: RNG): (Double, RNG) = nonNegativeInt(rng) match {
    case (n, rng2) => (n.toDouble / (Int.MaxValue + 1), rng2)
  }

  def doubleMap: Rand[Double] = {
    map(nonNegativeInt)(_.toDouble / (Int.MaxValue + 1))
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    rng.nextInt match {
      case (i, rng2) => double(rng2) match {
        case (d, rng3) => ((i, d), rng3)
      }
    }
  }


  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val x = intDouble(rng)
    (x._1.swap, x._2)
  }

  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
    val (d1, r1) = double(rng)
    val (d2, r2) = double(r1)
    val (d3, r3) = double(r2)
    ((d1, d2, d3), r3)
  }

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
    @tailrec
    def loop(cnt: Int, lrng: RNG, items: List[Int] = Nil): (List[Int], RNG) = {
      if (cnt == 0) (items, lrng)
      else {
        lrng.nextInt match {
          case (i, rng) => loop(cnt - 1, rng, i :: items)
        }
      }
    }

    loop(count, rng)
  }

  def intsSeq(count: Int)(rng: RNG): Rand[List[Int]] = sequence(List.fill(count)(RNG.int))

  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    rng => {
      val (a, rng2) = ra(rng)
      val (b, rng3) = rb(rng2)
      (f(a, b), rng3)
    }
  }

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = {
    fs
      .foldRight(
        unit(List.empty[A])
      )(
        (ra, acc) => {
          map2(ra, acc)(_ :: _)
        }
      )
  }

  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] = rng => {
    val (a, rng2) = f(rng)
    g(a)(rng2)
  }

  def mapFlatMap[A, B](s: Rand[A])(f: A => B): Rand[B] = this.flatMap(s)(x => unit(f(x)))

  def map2FlatMap[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = flatMap(ra)(a => flatMap(rb)(b => unit(f(a, b))))
}

//  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
//    rng => {
//      val (a, rng2) = ra(rng)
//      val (b, rng3) = rb(rng2)
//      (f(a, b), rng3)
//    }
//  }
case class State[S, +A](run: S => (A, S)) {
  def map[B](f: A => B): State[S, B] =
    flatMap(a => State.unit(f(a)))

  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] =
    flatMap(a => sb.map(b => f(a,b)))

  def flatMap[B](f: A => State[S, B]): State[S, B] =  State(state => {
    val (a, state2) = run(state)
    f(a).run(state2)
   })






}



sealed trait Input

case object Coin extends Input

case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

object State {
  type Rand[A] = State[RNG, A]

  def unit[S, A](a: A): State[S, A] = State(s => (a,s))

  def sequence[S, A](fs: List[State[S, A]]): State[S, List[A]] =
    fs
      .foldRight(unit[S, List[A]](Nil))(
        (f, acc) => {
          f.map2(acc)(_ :: _)
        }
      )

//  def get[S]: State[S, S] = State(s => (s,s))
//  def set[S](s: S): State[S, Unit] = State(_ => ((), s))

  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = ???


}
