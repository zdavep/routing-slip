package zdavep.test.rules

import zdavep.Rule
import zdavep.test.domain.Car

final class PaintCarRule(color: String) extends Rule[Car] {
  def process(car: Car): Car =
    if (car.color == color) car else car.copy(color = color)
}

object PaintCarRule {
  import akka.actor.Props
  def props(color: String): Props = Props(new PaintCarRule(color))
}
