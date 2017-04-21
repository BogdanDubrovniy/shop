package com.my.dubrovnyi.shop.servlets.services.bucketServices;

import com.my.dubrovnyi.shop.db.DAOs.BucketDAO;
import com.my.dubrovnyi.shop.db.DAOs.GoodsDAO;
import com.my.dubrovnyi.shop.db.entities.BucketEntity;
import com.my.dubrovnyi.shop.db.entities.Goods;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.BucketDTO;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.GoodsDTO;
import com.my.dubrovnyi.shop.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addGoodToBucket")
public class AddGoodToBucketServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(AddGoodToBucketServlet.class);
    private static final String BUCKET_PAGE_VALUE = "WEB-INF/servicePages/bucket.jsp";
    private static final String GOODS_PAGE_VALUE = "/pages/goods.jsp";
    private static final String GOOD_AMOUNT = "goodAmount";
    private static final String GOOD_NUMBER = "goodNumber";
    private static final String GOOD_LIST = "goodList";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fillBucketOperation(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        fillBucketOperation(req, resp);
    }

    private void fillBucketOperation(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            addGood(req, resp);
        } catch (DAOException e) {
            System.err.println("Error when adding good to bucket!");
            LOG.error("Error when adding good to bucket!");
            wrongAddingGoodAdding(req, resp);
        }
    }

    private void addGood(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, DAOException {
        LOG.info("Was started good adding to bucket");

        if (!inputValidationAmount(req)) {
            throw new DAOException("Wrong input data!");
        } else {
            int goodAmount;
            goodAmount = Integer.parseInt(req.getParameter(GOOD_AMOUNT));
            int goodNumber = Integer.parseInt(req.getParameter(GOOD_NUMBER));

            BucketDAO bucketDAO = (BucketDAO) req.getSession()
                    .getAttribute(BUCKET_ENTITIES_VALUE);
            if (bucketDAO == null) {
                bucketDAO = new BucketDAO();
            }
            GoodsDAO goodsDAO = GoodsDAO.getInstance();
            GoodsDTO goodToAdd = goodsDAO.getGoodInfoById(goodNumber);

            bucketDAO.addGoodToBucket(goodToAdd, goodAmount);
            successfulGoodAdding(req, resp, bucketDAO);
        }
    }

    private boolean inputValidationAmount(HttpServletRequest req) {
        return !(req.getParameter(GOOD_AMOUNT) == null
                || EMPTY_VALUE.equals(req.getParameter(GOOD_AMOUNT))
                || (Integer.parseInt(req.getParameter(GOOD_AMOUNT)) < 1));
    }

    private void wrongAddingGoodAdding(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        GoodsDAO goodsDAO = GoodsDAO.getInstance();
        List<Goods> list = null;
        try {
            int recordsPerPage = (int) req.getSession()
                    .getAttribute(RECORDS_PER_PAGE_VALUE);
            list = goodsDAO.getFilteredPaginationGoods(null,
                    0, recordsPerPage);
            int numberOfPages = (int) Math.ceil(goodsDAO.getRowsNumber()
                    * 1.0 / recordsPerPage);
            req.setAttribute(NUMBER_OF_PAGES_VALUE, numberOfPages);
        } catch (DAOException e) {
            System.err.println(e.getMessage());
        }
        req.setAttribute(GOOD_LIST, list);
        req.getRequestDispatcher(GOODS_PAGE_VALUE).forward(req, resp);
    }

    private void successfulGoodAdding(HttpServletRequest req, HttpServletResponse resp,
                                      BucketDAO bucketDAO)
            throws ServletException, IOException {

        List<BucketEntity> list = new BucketDTO(bucketDAO.getBucket()).getBucketEntity();
        req.getSession().setAttribute(BUCKET_ENTITIES_VALUE, bucketDAO);
        req.setAttribute(BUCKET_LIST_VALUE, list);

        req.getRequestDispatcher(BUCKET_PAGE_VALUE).forward(req, resp);
    }
}