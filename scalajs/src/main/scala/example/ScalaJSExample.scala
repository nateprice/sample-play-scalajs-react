package example

import scala.scalajs.js
import js.Dynamic.{ global => g }

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._, ScalazReact._

import org.scalajs.dom

object Calc {

  case class State(display: String, a: Int, b: Int) {
    def calculate(c: Char) = c match {
      case '+' => this.copy(display = "" + (a + b))
      case '-' => this.copy(display = "" + (a - b))
      case '*' => this.copy(display = "" + (a * b))
      case '/' => this.copy(display = if (b == 0) "error" else "" + (a / b))
    }
  }

  class Backend(t: BackendScope[String, State]) {
    def onChange(first: Boolean)(e: ReactEventI) =
      t.modState(s =>
        try {
          if (first) s.copy(a = Integer.parseInt(e.target.value))
          else s.copy(b = Integer.parseInt(e.target.value))
        } catch {
          case e: Throwable => s.copy(display = "error")
        })

    def operate(op: Char)(e: ReactEventI) = {
      e.preventDefault()
      t.modState(_.calculate(op))
    }

  }

  val App = ReactComponentB[String]("Calc").
    initialState(State("0", 0, 0)).
    backend(x => new Backend(x)).
    render((_, S, B) => {
      form(
        span(S.display), br,
        input(`type` := "text", name := "a",
          onChange ==> B.onChange(true)), br,
        input(`type` := "text", name := "b",
          onChange ==> B.onChange(false)), br,
        button("+", onClick ==> B.operate('+')),
        button("-", onClick ==> B.operate('-')),
        button("*", onClick ==> B.operate('*')),
        button("/", onClick ==> B.operate('/')))
    }).
    build

}

object ToDoExample {

  val TodoList = ReactComponentB[List[String]]("TodoList")
    .render(P => {
      def createItem(itemText: String) = li(itemText)
      ul(P map createItem)
    })
    .build

  case class State(items: List[String], text: String)
  val ST = ReactS.Fix[State]
  def acceptChange(e: ReactEventI) =
    ST.mod(_.copy(text = e.target.value))

  def handleSubmit(e: ReactEventI) = (
    ST.retM(e.preventDefaultIO)
    >>
    ST.mod(s => State(s.items :+ s.text, "")).liftIO)

  val TodoApp = ReactComponentB[Unit]("TodoApp")
    .initialState(State(Nil, ""))
    .renderS((T, _, S) => // Using renderS instead of render to get T (`this` in JS).
      div(
        h3("hello"),
        TodoList(S.items),
        form(onSubmit ~~> T._runState(handleSubmit))(
          input(
            onChange ~~> T._runState(acceptChange),
            value := S.text),
          button("Add #", S.items.length + 1)))).buildU

}

object Hello {
  val helloMessage = ReactComponentB[String]("HelloMessage")
    .render(name => div("hello", name))
    .build

}

object ScalaJSExample extends js.JSApp {

  //React.render(TodoApp(), mountNode)

  def main(): Unit = {

    //dom.alert("hello")

    val h = g.document.getElementById("scalajsShoutOut")
    h.textContent = "hello"

    val node = dom.document.getElementById("scalajsShoutOut")

    //React.render(helloMessage, dom.document.body)
    //helloMessage("mike") render node

    //ToDoExample.TodoApp() render node
    Calc.App("0") render node
  }

  /**
   * Computes the square of an integer.
   *  This demonstrates unit testing.
   */
  def square(x: Int): Int = x * x
}
