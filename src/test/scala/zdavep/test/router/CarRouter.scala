package zdavep.test.router

import akka.actor.{ ActorRef, Props }
import zdavep.test.rules.{ NavigationRule, PaintCarRule, SensorsRule }
import zdavep.{ Router, RuleKey }

final class CarRouter(lastRule: ActorRef) extends Router(lastRule) {
  import zdavep.test.rules.CarRuleKeys._

  def ruleFor(key: RuleKey): Option[ActorRef] = key match {
    case PaintBlack ⇒ Some(context.actorOf(PaintCarRule.props(color = "black")))
    case PaintGray ⇒ Some(context.actorOf(PaintCarRule.props(color = "gray")))
    case AddNavigation ⇒ Some(context.actorOf(NavigationRule.props()))
    case AddSensors ⇒ Some(context.actorOf(SensorsRule.props()))
    case _ ⇒ None
  }
}

object CarRouter {
  def props(lastRule: ActorRef): Props = Props(new CarRouter(lastRule))
}