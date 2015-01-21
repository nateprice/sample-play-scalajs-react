package example

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._, ScalazReact._

/**
 * @author msciab
 */
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
