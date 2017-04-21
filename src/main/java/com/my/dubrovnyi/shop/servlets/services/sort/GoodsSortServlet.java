package com.my.dubrovnyi.shop.servlets.services.sort;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sortGoods")
public class GoodsSortServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(GoodsSortServlet.class);
    private static final String GOODS_PAGE_VALUE = "/goodList";

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
        String orderValue = req.getParameter(SELECT_FIELD_VALUE);
        String direction = req.getParameter(SELECT_WAY_VALUE);

        HttpSession session = req.getSession();

        session.setAttribute(SELECT_FIELD_VALUE, orderValue);
        session.setAttribute(SELECT_WAY_VALUE, direction);
        session.setAttribute(O_T_VALUE, SORT_VALUE);
        req.getRequestDispatcher(GOODS_PAGE_VALUE).forward(req, resp);
    }
}