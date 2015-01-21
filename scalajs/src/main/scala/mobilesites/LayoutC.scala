package mobilesites

import org.scalajs.dom
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import scala.collection.immutable.ListMap

import example._
import Commons._
import scalajs.js.Dynamic.{global => g}

/**
 * @author msciab
 */
object LayoutC {

  val Menu = ListMap(
    "Settings" -> ListMap(
      "Todo" -> ToDoExample,
      "Calc" -> CalcExample("Dashboard Calc"),
      "Hello" -> HelloExample("Settings")),
    "Dashboard" -> ListMap(
      "Calc" -> CalcExample("Dashboard Calc"),
      "Hello" -> HelloExample("Dashboard"),
      "Todo" -> ToDoExample),
    "Help" -> ListMap(
      "Info" -> CalcExample("Dashboard Calc"),
      "About" -> HelloExample,
      "Contact" -> ToDoExample))
      

  case class State(menu: String, submenu: String)

  class Backend(t: BackendScope[String, State]) {

    def menu(item: String)(e: ReactEvent) =
      t.modState(s => State(item, Menu(item).head._1))

    def submenu(subitem: String)(e: ReactEvent) =
      t.modState(s => State(s.menu, subitem))

  }
  
  def test(s: String)  { g.alert(s) }

  val component = ReactComponentB[String]("Layout")
    .render(title =>
      body(ToolbarC(Menu.map(_._1), test),
        div(`class` := "container-fluid",
          div(`class` := "row",
            div(`class` := "col-sm-3 col-md-2 sidebar",
              MenuC(title)),
            div(`class` := "col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main",
              CalcExample(title)))))).build

  def apply(s: String) = component(s)
}