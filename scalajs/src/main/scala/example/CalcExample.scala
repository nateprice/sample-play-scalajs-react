package example

import scala.scalajs.js
import org.scalajs.dom
import js.Dynamic.{ global => g }

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._, ScalazReact._

object CalcExample {

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
    render((P, S, B) => {
      form(
        h1(P),
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

  def apply(s: String) = App(s)

}