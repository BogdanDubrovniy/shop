package com.my.dubrovnyi.shop.servlets.services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.EMPTY_VALUE;
import static com.my.dubrovnyi.shop.constants.ConstantsClass.RECORDS_PER_PAGE_VALUE;

@WebServlet("/setMaxNumberPerPage")
public class SetMaxValuesPerPageServlet extends HttpServlet {
    private static final String GOODS_PAGE_TRANSFER = "/goodList";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setMaxNoteNumberPerPage(req, resp);
    }

    private void setMaxNoteNumberPerPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int recordsPerPage;
        String recordsPerPageValue = req.getParameter(RECORDS_PER_PAGE_VALUE);

        if (recordsPerPageValue == null || EMPTY_VALUE.equals(recordsPerPageValue)) {
            recordsPerPage = (int) req.getSession().getAttribute(RECORDS_PER_PAGE_VALUE);
        } else {
            recordsPerPage = Integer.parseInt(recordsPerPageValue);
            if (recordsPerPage <= 0) {
                recordsPerPage = Integer.MAX_VALUE;
            }
        }
        req.getSession().setAttribute(RECORDS_PER_PAGE_VALUE, recordsPerPage);
        req.getRequestDispatcher(GOODS_PAGE_TRANSFER).forward(req, resp);
    }
}

