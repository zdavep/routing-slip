package zdavep

import akka.actor.ActorRef

/**
 * Rule routing behavior.
 */
trait Routing {

  /**
   * Route a message to the next rule in the chain.
   */
  def route(rules: Seq[ActorRef], payload: AnyRef): Unit = rules match {
    case rule :: rest ⇒
      val msg = if (rest.isEmpty) payload else RoutingSlip(rest, payload)
      rule ! msg
  }
}
