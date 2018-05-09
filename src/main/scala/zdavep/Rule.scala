package zdavep

import akka.actor.{ Actor, ActorLogging, ActorRef }
import scala.concurrent.Future
import scala.util.{ Failure, Success }

/**
 * Process a message and send the result to the next rule.
 */
trait Rule[A <: AnyRef] extends Actor with ActorLogging with Routing {

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
    case RoutingSlip(rules: Seq[ActorRef], payload: A) ⇒ process(payload).onComplete {
      case Success(processed) ⇒ route(rules, processed)
      case Failure(t) ⇒
        log.error("Rule processing failed: {}", t)
        route(rules, payload)
    }
  }
}
