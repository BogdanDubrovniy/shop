package com.my.dubrovnyi.shop.filters.accessManager;

import com.my.dubrovnyi.shop.db.entities.xml.Rule;
import com.my.dubrovnyi.shop.db.entities.xml.RuleRoles;
import org.apache.log4j.Logger;

import java.util.List;

public class AccessManager {
    private static final Logger LOG = Logger.getLogger(AccessManager.class);
    private static final String USER_VALUE = "user";
    private static final String JSP_VALUE = ".jsp";

    private RuleRoles rules;

    public AccessManager(RuleRoles rules) {
        this.rules = rules;
    }

    public boolean isPageBlocked(String httpLink, String role) {
        List<Rule> specificRules;
        if (USER_VALUE.equals(role)) {
            specificRules = rules.getUserRules();
        } else {
            specificRules = rules.getUnauthorizedRules();
        }

        for (Rule rule : specificRules) {
            if (httpLink.startsWith(rule.getUrlPatter())
                    && !httpLink.endsWith(JSP_VALUE)) {
                LOG.info("Current page is not accessible " + httpLink);
                return true;
            }
        }
        return false;
    }
}
