import scala.collection.immutable._

object Playground {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(100); 
  println("Welcome to the Scala worksheet");$skip(52); 
  
  val l = ListMap("a" -> 1 , "b" -> 2, "c" -> 3);System.out.println("""l  : scala.collection.immutable.ListMap[String,Int] = """ + $show(l ));$skip(14); val res$0 = 
  l.map(_._2);System.out.println("""res0: scala.collection.immutable.Iterable[Int] = """ + $show(res$0));$skip(11); val res$1 = 

l.head._1;System.out.println("""res1: String = """ + $show(res$1))}
}
