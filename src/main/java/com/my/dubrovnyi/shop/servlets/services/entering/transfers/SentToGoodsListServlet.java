package com.my.dubrovnyi.shop.servlets.services.entering.transfers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * gets the list and transfer to another servlet
 */
@WebServlet("/list")
public class SentToGoodsListServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SentToGoodsListServlet.class);
    private static final String GOODS_PAGE_VALUE = "/goodList";
    private static final String OPERATION_TYPE_VALUE = "operationType";
    private static final String NO_TYPE_VALUE = "noType";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.info("Was transferred to goods list page!");
        pageTransfer(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.info("Was transferred to goods list page!");
        pageTransfer(req, resp);
    }

    private void pageTransfer(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().setAttribute(OPERATION_TYPE_VALUE, NO_TYPE_VALUE);
        req.getRequestDispatcher(GOODS_PAGE_VALUE).forward(req, resp);
    }
}