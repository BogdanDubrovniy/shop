package com.my.dubrovnyi.shop.servlets.services.entering.transfers;

import com.my.dubrovnyi.shop.db.DAOs.BucketDAO;
import com.my.dubrovnyi.shop.db.entities.BucketEntity;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.BucketDTO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.BUCKET_LIST_VALUE;

@WebServlet("/bucket")
public class SentToBucketPageServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SentToBucketPageServlet.class);
    private static final String BUCKET_PAGE_VALUE = "WEB-INF/servicePages/bucket.jsp";
    private static final String BUCKET_ENTITIES = "bucketEntities";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setValues(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setValues(req, resp);
    }

    private void setValues(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.info("Was transferred to bucket page!");


        BucketDAO bucketDAO = (BucketDAO) req.getSession().getAttribute(BUCKET_ENTITIES);
        if (bucketDAO == null) {
            bucketDAO = new BucketDAO();
        }
        List<BucketEntity> list = new BucketDTO(bucketDAO.getBucket()).getBucketEntity();

        req.setAttribute(BUCKET_LIST_VALUE, list);
        req.getRequestDispatcher(BUCKET_PAGE_VALUE).forward(req, resp);
    }
}