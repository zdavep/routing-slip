package zdavep.test

import akka.actor.ActorSystem
import akka.testkit.{ ImplicitSender, TestKit }
import org.scalatest.WordSpecLike
import zdavep.RouterRequest
import zdavep.test.domain.Car
import zdavep.test.router.CarRouter

final class RouterTest extends TestKit(ActorSystem("test"))
  with WordSpecLike
  with ImplicitSender
  with SystemTermination {

  // Put keys in scope
  import zdavep.test.rules.CarRuleKeys._

  // The router we're testing
  val router = system.actorOf(CarRouter.props(testActor))

  "A car router" must {
    "Construct a gray car" in {
      router ! RouterRequest(Seq(PaintGray, IgnoreMe, PaintGray), Car())
      expectMsg(Car(color = "gray"))
    }
    "Construct a car with navigation and leave other fields unchanged" in {
      router ! RouterRequest(Seq(AddNavigation), Car())
      expectMsg(Car(navigation = true))
    }
    "Construct a black car with sensors and navigation" in {
      router ! RouterRequest(Seq(PaintBlack, AddSensors, IgnoreMe, AddNavigation), Car())
      expectMsg(Car(color = "black", navigation = true, sensors = true))
    }
  }
}
