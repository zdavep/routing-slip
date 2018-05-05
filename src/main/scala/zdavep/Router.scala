package zdavep

import akka.actor.{ Actor, ActorRef }

/**
 * Router base class.
 */
abstract class Router(lastRule: ActorRef) extends Actor with Routing {

  /**
   * Initialize the rule for a given key. Returns `None` if the key is not supported.
   */
  def ruleFor(key: RuleKey): Option[ActorRef]

  /**
   * Construct a rule route and send a message down the chain.
   */
  def receive: Receive = {
    case RouterRequest(keys, payload) ⇒
      val rules = keys.sortBy(_.priority).flatMap(ruleFor) :+ lastRule
      route(rules, payload)
  }
}
