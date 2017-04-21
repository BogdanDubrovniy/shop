package com.my.dubrovnyi.shop.servlets.services.entering.transfers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class SendToLogInServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SendToLogInServlet.class);
    private static final String ENTERING_PATH = "WEB-INF/servicePages/entering.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.info("Was transferred to log in page!");
        req.getRequestDispatcher(ENTERING_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(ENTERING_PATH).forward(req, resp);
    }
}
