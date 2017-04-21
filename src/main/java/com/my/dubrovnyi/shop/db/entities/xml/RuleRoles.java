package com.my.dubrovnyi.shop.db.entities.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for rules list
 */
public class RuleRoles {
    private Map<String, List<Rule>> ruleRolesMap;
    private static final String USER_VALUE = "user";
    private static final String UNAUTHORIZED_USER_VALUE = "unauthorized";

    public RuleRoles() {
        ruleRolesMap = new LinkedHashMap<>();
        ruleRolesMap.put(USER_VALUE, new ArrayList<>());
        ruleRolesMap.put(UNAUTHORIZED_USER_VALUE, new ArrayList<>());
    }

    public void addRule(Rule rule, String role) {
        List<Rule> ruleList = ruleRolesMap.get(role);
        ruleList.add(rule);
        ruleRolesMap.put(role, ruleList);
    }

    public List<Rule> getUserRules() {
        return ruleRolesMap.get(USER_VALUE);
    }

    public List<Rule> getUnauthorizedRules() {
        return ruleRolesMap.get(UNAUTHORIZED_USER_VALUE);
    }
}
