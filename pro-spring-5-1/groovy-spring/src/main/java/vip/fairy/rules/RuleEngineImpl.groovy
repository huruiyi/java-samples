package vip.fairy.rules

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import vip.fairy.RuleEngine

@Component("ruleEngine")
class RuleEngineImpl implements RuleEngine {
    Logger logger = LoggerFactory.getLogger(RuleEngineImpl.class);

    void run(Rule rule, Object object) {
        logger.info "Executing rule"

        def exit = false

        rule.parameters.each { ArrayList params ->
            def paramIndex = 0
            def success = true

            if (!exit) {
                rule.conditions.each {
                    logger.info "Condition Param index: " + paramIndex
                    success = success && it(object, params[paramIndex])
                    logger.info "Condition success: " + success
                    paramIndex++
                }

                if (success && !exit) {
                    rule.actions.each {
                        logger.info "Action Param index: " + paramIndex
                        it(object, params[paramIndex])
                        paramIndex++
                    }
                    if (rule.singlehit) {
                        exit = true
                    }
                }
            }
        }
    }
}
