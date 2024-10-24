package vip.fairy;

import vip.fairy.rules.Rule;

public interface RuleEngine {
    void run(Rule rule, Object object);
}
