package com.my.dubrovnyi.shop.servlets.services.entering.transfers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/personalPage")
public class SentToPersonalPageServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SendToRegistrationServlet.class);
    private static final String PERSONAL_PAGE_VALUE = "WEB-INF/servicePages/personalPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.info("Was transferred to personal page!");
        req.getRequestDispatcher(PERSONAL_PAGE_VALUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(PERSONAL_PAGE_VALUE).forward(req, resp);
    }
}
