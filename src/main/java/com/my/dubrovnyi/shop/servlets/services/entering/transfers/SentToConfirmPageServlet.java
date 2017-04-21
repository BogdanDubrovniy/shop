package com.my.dubrovnyi.shop.servlets.services.entering.transfers;

import com.my.dubrovnyi.shop.db.DAOs.BucketDAO;
import com.my.dubrovnyi.shop.constants.ConstantsClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/confirm")
public class SentToConfirmPageServlet extends HttpServlet {
    private static final String CONFIRM_PAGE = "WEB-INF/servicePages/confirm.jsp";
    private static final String BUCKET_PAGE = "/bucket";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        confirm(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        confirm(req, resp);
    }

    private void confirm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BucketDAO bucketDAO = (BucketDAO) req.getSession().getAttribute(ConstantsClass.BUCKET_ENTITIES);
        if (bucketDAO.getBucket().isEmpty()) {
            req.getRequestDispatcher(BUCKET_PAGE).forward(req, resp);
        } else {
            String orderStatus = req.getParameter(ConstantsClass.ORDER_STATUS);
            String orderDescription = req.getParameter(ConstantsClass.ORDER_DESCRIPTION);

            req.getSession().setAttribute(ConstantsClass.ORDER_STATUS, orderStatus);
            req.getSession().setAttribute(ConstantsClass.ORDER_DESCRIPTION, orderDescription);

            req.setAttribute(ConstantsClass.BUCKET_GOOD_AMOUNT, bucketDAO.getTotalAmount());
            req.setAttribute(ConstantsClass.BUCKET_TOTAL_PRICE, bucketDAO.getTotalCost());

            req.getRequestDispatcher(CONFIRM_PAGE).forward(req, resp);
        }
    }
}
