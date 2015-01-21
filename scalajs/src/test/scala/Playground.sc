import scala.collection.immutable._

object Playground {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val l = ListMap("a" -> 1 , "b" -> 2, "c" -> 3)  //> l  : scala.collection.immutable.ListMap[String,Int] = Map(a -> 1, b -> 2, c 
                                                  //| -> 3)
  l.map(_._2)                                     //> res0: scala.collection.immutable.Iterable[Int] = List(1, 2, 3)

l.head._1                                         //> res1: String = a
}