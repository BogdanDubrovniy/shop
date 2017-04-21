package com.my.dubrovnyi.shop.servlets.services;

import com.my.dubrovnyi.shop.db.DAOs.GoodsDAO;
import com.my.dubrovnyi.shop.db.entities.Goods;
import com.my.dubrovnyi.shop.db.entities.valueContainers.FilterValues;
import com.my.dubrovnyi.shop.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/goodList")
public class GoodsListServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(GoodsListServlet.class);

    private static final String GOODS_PAGE_VALUE =
            "/pages/goods.jsp";
    private static final String OPERATION_TYPE_VALUE = "operationType";
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_RECORDS_NUMBER = 2;

    private static final String GOODS_TRANSFERING_ERROR_MESSAGE
            = "Error when do transfers to goods page ";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doOperation(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doOperation(req, resp);
    }

    private void doOperation(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.info("Was transferred to goods list page!");
        try {
            setPageContextValues(req, resp);
        } catch (DAOException e) {
            System.err.println(GOODS_TRANSFERING_ERROR_MESSAGE + e.getMessage());
            LOG.error(GOODS_TRANSFERING_ERROR_MESSAGE + e.getMessage());
        }
    }

    private void setPageContextValues(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, DAOException {
        String operationType = (String) req.getSession()
                .getAttribute(OPERATION_TYPE_VALUE);

        GoodsDAO goodsDAO = GoodsDAO.getInstance();
        HttpSession session = req.getSession();

        int page = DEFAULT_PAGE_NUMBER;
        int recordsPerPage = DEFAULT_PAGE_RECORDS_NUMBER;

        if (req.getParameter(PAGE_VALUE) != null) {
            page = Integer.parseInt(req.getParameter(PAGE_VALUE));
        }
        if (req.getSession().getAttribute(RECORDS_PER_PAGE_VALUE) != null
                && !EMPTY_VALUE.equals(req.getSession()
                .getAttribute(RECORDS_PER_PAGE_VALUE))) {
            recordsPerPage = (int) req.getSession().getAttribute(RECORDS_PER_PAGE_VALUE);
        }

        List<Goods> list;
        int numberOfRecords;
        int numberOfPages;

        FilterValues filterValues = (FilterValues) req.getSession()
                .getAttribute(FILTER_FIELD_VALUE);

        switch (operationType) {
            case FILTER_VALUE:
                list = goodsDAO.getFilteredPaginationGoods(filterValues,
                        (page - DEFAULT_PAGE_NUMBER) * recordsPerPage,
                        recordsPerPage);
                numberOfRecords = goodsDAO.getFilteredGoods(filterValues).size();
                break;
            case SORT_VALUE:
                String orderValue = (String) session.getAttribute(SELECT_FIELD_VALUE);
                String direction = (String) session.getAttribute(SELECT_WAY_VALUE);
                numberOfRecords = goodsDAO.getFilteredGoods(filterValues).size();
                list = goodsDAO.getSortedGoods(orderValue, direction, filterValues,
                        (page - DEFAULT_PAGE_NUMBER) * recordsPerPage,
                        recordsPerPage);
                break;
            default:
                list = goodsDAO.getFilteredPaginationGoods(null,
                        (page - DEFAULT_PAGE_NUMBER)
                                * recordsPerPage, recordsPerPage);
                numberOfRecords = goodsDAO.getRowsNumber();
                break;
        }
        numberOfPages = (int) Math.ceil(numberOfRecords
                * DEFAULT_PAGE_NUMBER / recordsPerPage);

        session.setAttribute(RECORDS_PER_PAGE_VALUE, recordsPerPage);
        req.setAttribute(GOOD_LIST_VALUE, list);
        req.setAttribute(NUMBER_OF_PAGES_VALUE, numberOfPages);
        req.getRequestDispatcher(GOODS_PAGE_VALUE).forward(req, resp);
    }
}