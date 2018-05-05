package zdavep

import akka.actor.{ Actor, ActorRef }
import scala.concurrent.Future

/**
 * Process a message and send the result to the next rule.
 */
trait Rule[A <: AnyRef] extends Actor with Routing {

  // Execution context
  import context.dispatcher

  /**
   * Async payload processing.
   */
  def process(payload: A): Future[A]

  /**
   * Receive and process a message then route to the next rule.
   */
  def receive: Receive = {
    case RoutingSlip(rules: Seq[ActorRef], payload: A) ⇒
      process(payload).map { processed ⇒
        route(rules, processed)
      }
  }
}
