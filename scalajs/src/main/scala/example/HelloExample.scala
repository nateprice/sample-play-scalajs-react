package example

/**
 * @author msciab
 */
import japgolly.scalajs.react.ReactComponentB
import japgolly.scalajs.react.vdom.all._

object HelloExample {

  case class State(msg: String)

  val helloExample = ReactComponentB[String]("HelloExample")
    .initialState(s => State(s))
    .render((P, S, _) => h1(S))
    .build

  def apply(s: String) = helloExample(s)
}

