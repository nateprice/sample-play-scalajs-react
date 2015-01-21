package mobilesites

import scala.scalajs.js
import org.scalajs.dom
import scalajs.js.Dynamic.{global => g}

object Main extends js.JSApp {
  def main(): Unit = {
    //g.alert("hello")
    LayoutC("MobileSites") render dom.document.body
  }
}