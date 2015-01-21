package example

import scala.scalajs.js
import js.Dynamic.{ global => g }

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._, ScalazReact._

import org.scalajs.dom

object ScalaJSExample /*extends js.JSApp*/ {

  //React.render(TodoApp(), mountNode)

  def main(): Unit = {

    CalcExample.App("0") render dom.document.getElementById("content")

    //React.render(helloMessage, dom.document.body)
    //helloMessage("mike") render node

    //ToDoExample.TodoApp() render node

  }

  /**
   * Computes the square of an integer.
   *  This demonstrates unit testing.
   */
  def square(x: Int): Int = x * x
}
