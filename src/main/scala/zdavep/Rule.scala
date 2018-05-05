package zdavep

import akka.actor.{ Actor, ActorRef }

/**
 * Process a message and send the result to the next rule.
 */
trait Rule[A <: AnyRef] extends Actor with Routing {

  /**
   * Process a message and return a potentially modified copy.
   */
  def process(a: A): A

  /**
   * Receive and process a message then route to the next rule.
   */
  def receive: Receive = {
    case RoutingSlip(rules: Seq[ActorRef], payload: A) ⇒ route(rules, process(payload))
  }
}
