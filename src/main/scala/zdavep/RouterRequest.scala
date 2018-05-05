package zdavep

/**
 * Router request message.
 */
final case class RouterRequest(keys: Seq[RuleKey], payload: AnyRef)
