package zdavep

/**
 * A marker interface for defining rule keys.
 */
trait RuleKey {

  /**
   * The priority of the rule in a routing chain.
   */
  def priority: Int
}
