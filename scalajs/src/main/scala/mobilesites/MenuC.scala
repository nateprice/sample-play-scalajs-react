package mobilesites

import org.scalajs.dom
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._, ScalazReact._
import japgolly.scalajs.react.ReactComponentB

/**
 * @author msciab
 */
object MenuC {

  val component = ReactComponentB[String]("ToolBar")
    .render(title =>
      div(`class` := "col-sm-3 col-md-2 sidebar",
        ul(`class` := "nav nav-sidebar",
          li(`class` := "active",
            a(href := "#", "Item11")),
          li(a(href := "#", "Item12"))),
        ul(`class` := "nav nav-sidebar",
          li(a(href := "#", "Item21")),
          li(a(href := "#", "Item22")))))
    .build

  def apply(title: String) = component(title)
}