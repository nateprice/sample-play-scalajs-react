package example

import japgolly.scalajs.react.ReactComponentB
import japgolly.scalajs.react.vdom.all._

object LabelExample {
  val labelExample = ReactComponentB[String]("labelExample")
    .render(P => div(P))
    .build

  def apply(s: String) = labelExample(s)
}