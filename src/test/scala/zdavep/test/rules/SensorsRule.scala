package zdavep.test.rules

import scala.concurrent.Future
import zdavep.Rule
import zdavep.test.domain.Car

final class SensorsRule extends Rule[Car] {
  override def shouldProcess(car: Car): Boolean = !car.sensors
  def process(car: Car): Future[Car] = Future.successful(car.copy(sensors = true))
}

object SensorsRule {
  import akka.actor.Props
  def props(): Props = Props(new SensorsRule())
}
