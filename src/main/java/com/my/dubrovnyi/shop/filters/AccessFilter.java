package com.my.dubrovnyi.shop.filters;

import com.my.dubrovnyi.shop.db.entities.xml.RuleRoles;
import com.my.dubrovnyi.shop.filters.accessManager.AccessManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.USER_LOGIN_VALUE;

public class AccessFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AccessFilter.class);
    private static final int ERROR_PAGE_403 = 403;
    private static final String USER_VALUE = "user";
    private static final String UNAUTHORIZED_USER_VALUE = "unauthorized";
    private static final String RETURN_PAGE_VALUE = "returnPage";
    private static final String LOGIN_PAGE_VALUE = "/login";
    private static final String ACCESS_RULE_VALUE = "accessRules";
    private AccessManager accessManager;

    public void init(FilterConfig config) throws ServletException {
        LOG.debug("Was initialed AccessFilter");
        RuleRoles rules = (RuleRoles) config.getServletContext()
                .getAttribute(ACCESS_RULE_VALUE);
        accessManager = new AccessManager(rules);
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        String role = getUserRole(httpServletRequest);
        String httpLink = String.valueOf(httpServletRequest.getRequestURI());

        if (accessManager.isPageBlocked(httpLink, role)) {
            if (isUserEnters(role)) {
                LOG.error("Error page: 403");
                httpServletResponse.sendError(ERROR_PAGE_403);
            } else {
                httpServletRequest.getSession().setAttribute(RETURN_PAGE_VALUE, httpLink);
                httpServletResponse.sendRedirect(LOGIN_PAGE_VALUE);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void destroy() {
    }

    private String getUserRole(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_VALUE) == null) {
            return UNAUTHORIZED_USER_VALUE;
        }
        return USER_VALUE;
    }

    private boolean isUserEnters(String role) {
        return USER_VALUE.equals(role);
    }
}
