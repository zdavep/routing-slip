package zdavep

import akka.actor.ActorRef

/**
 * Rule routing message.
 */
final case class RoutingSlip(rules: Seq[ActorRef], payload: AnyRef)
