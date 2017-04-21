package com.my.dubrovnyi.shop.servlets.services.entering.transfers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/setUpFilter")
public class SentToFilterPageServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SentToFilterPageServlet.class);
    private static final String SET_UP_FILTER_PAGE_VALUE =
            "/WEB-INF/servicePages/filter/filterSetUpPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.info("Was transferred to sorted goods page!");
        req.getRequestDispatcher(SET_UP_FILTER_PAGE_VALUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(SET_UP_FILTER_PAGE_VALUE).forward(req, resp);
    }
}
