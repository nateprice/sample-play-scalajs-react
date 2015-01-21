package mobilesites

import org.scalajs.dom
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._, ScalazReact._
import japgolly.scalajs.react.ReactComponentB

import Commons._

object ToolbarC {

  val component = ReactComponentB[(List[String], StringAction)]("ToolBar").
    render(P => {
      val (ls, action) = P
      nav(`class` := "navbar navbar-inverse navbar-fixed-top",
        div(`class` := "container-fluid",
          div(`class` := "navbar-header",
            button(`class` := "navbar-toggle collapsed",
              `type` := "button",
              //`data-toggle`:="collapse", 
              //`data-target`:="#navbar", 
              //`aria-expanded`:="false", 
              //`aria-controls`:="navbar",
              span(`class` := "sr-only",
                "Toggle navigation"),
              span(`class` := "icon-bar"),
              span(`class` := "icon-bar"),
              span(`class` := "icon-bar")),
            a(`class` := "navbar-brand",
              href := "#", "MobileSites")),
          div(`class` := "navbar-collapse collapse",
            ul(`class` := "nav navbar-nav navbar-right",
              ls.map(s => li(onClick --> action(s), a(href := "#", s)))),
            form(`class` := "navbar-form navbar-right",
              input(`class` := "form-control", `type` := "text",
                `placeholder` := "Search...")))))
    }).build

  def apply(titles: Iterable[String], action: StringAction) = component((titles.toList, action))
}