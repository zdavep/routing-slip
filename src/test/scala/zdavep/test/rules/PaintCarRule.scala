package zdavep.test.rules

import scala.concurrent.Future
import zdavep.Rule
import zdavep.test.domain.Car

final class PaintCarRule(color: String) extends Rule[Car] {
  override def shouldProcess(car: Car): Boolean = car.color != color
  def process(car: Car): Future[Car] = Future.successful(car.copy(color = color))
}

object PaintCarRule {
  import akka.actor.Props
  def props(color: String): Props = Props(new PaintCarRule(color))
}
