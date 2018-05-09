package zdavep.test.rules

import zdavep.Rule
import zdavep.test.domain.Car
import scala.concurrent.Future

final class NavigationRule extends Rule[Car] {
  override def shouldProcess(car: Car): Boolean = !car.navigation
  def process(car: Car): Future[Car] = Future.successful(car.copy(navigation = true))
}

object NavigationRule {
  import akka.actor.Props
  def props(): Props = Props(new NavigationRule())
}
