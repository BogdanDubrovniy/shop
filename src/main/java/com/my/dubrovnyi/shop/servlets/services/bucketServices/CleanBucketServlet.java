package com.my.dubrovnyi.shop.servlets.services.bucketServices;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.BUCKET_ENTITIES_VALUE;

@WebServlet("/cleanBucket")
public class CleanBucketServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(CleanBucketServlet.class);
    private static final String BUCKET_PAGE_VALUE = "WEB-INF/servicePages/bucket.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        cleanBucket(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        cleanBucket(req, resp);
    }

    private void cleanBucket(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(BUCKET_ENTITIES_VALUE, null);
        try {
            request.getRequestDispatcher(BUCKET_PAGE_VALUE).forward(request, response);
        } catch (ServletException | IOException e) {
            LOG.error("Can not clean bucket!");
        }
    }
}
