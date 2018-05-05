package zdavep.test.rules

import zdavep.RuleKey

object CarRuleKeys {
  sealed trait CarRuleKey extends RuleKey
  case object AddSensors extends CarRuleKey { val priority = 1 }
  case object AddNavigation extends CarRuleKey { val priority = 2 }
  case object PaintGray extends CarRuleKey { val priority = 3 }
  case object PaintBlack extends CarRuleKey { val priority = 4 }
  case object IgnoreMe extends CarRuleKey { val priority = Int.MaxValue }
}
