package com.my.dubrovnyi.shop.servlets.services.filter;

import com.my.dubrovnyi.shop.db.entities.valueContainers.FilterValues;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/filterGoods")
public class GoodsFilterServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(GoodsFilterServlet.class);

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
        setUpFilterValues(req);
        req.getSession().setAttribute(O_T_VALUE, FILTER_VALUE);
        req.getRequestDispatcher(GOODS_PAGE_VALUE).forward(req, resp);
    }

    private void setUpFilterValues(HttpServletRequest req) {
        FilterValues filterValues = new FilterValues();
        String name = req.getParameter(FILTER_NAME);
        String category = req.getParameter(FILTER_CATEGORY);
        String firm = req.getParameter(FILTER_FIRM);
        int firstValue;
        String firstPar = req.getParameter(FILTER_B_R);
        if (firstPar == null || EMPTY_STRING_VALUE.equals(firstPar)) {
            firstValue = ZERO;
        } else {
            firstValue = Integer.parseInt(firstPar);
            if (firstValue < ZERO) {
                firstValue = ZERO;
            }
        }
        int secondValue;
        String secondPar = req.getParameter(FILTER_A_R);
        if (secondPar == null || EMPTY_STRING_VALUE.equals(secondPar)) {
            secondValue = Integer.MAX_VALUE;
        } else {
            secondValue = Integer.parseInt(secondPar);
            if (secondValue < firstValue) {
                secondValue = Integer.MAX_VALUE;
            }
        }
        filterValues.setName(name);
        filterValues.setCategory(category);
        filterValues.setFirm(firm);
        filterValues.setFirstValue(firstValue);
        filterValues.setSecondValue(secondValue);
        req.getSession().setAttribute(FILTER_FIELD_VALUE, filterValues);
    }
}
