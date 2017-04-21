package com.my.dubrovnyi.shop.servlets.services.bucketServices;

import com.my.dubrovnyi.shop.db.DAOs.BucketDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.BUCKET_ENTITIES_VALUE;

@WebServlet("/deleteById")
public class DeleteGoodByIdServlet extends HttpServlet {
    private static final String BUCKET_PAGE_VALUE = "WEB-INF/servicePages/bucket.jsp";
    private static final String ID_VALUE = "id";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        deleteGood(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        deleteGood(request, response);
    }

    private void deleteGood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ID_VALUE));

        BucketDAO bucketDAO = (BucketDAO) request.getSession()
                .getAttribute(BUCKET_ENTITIES_VALUE);
        if (bucketDAO.deleteGoodById(id)) {
            request.getSession().setAttribute(BUCKET_ENTITIES_VALUE, bucketDAO);
        }
        request.getRequestDispatcher(BUCKET_PAGE_VALUE).forward(request, response);
    }
}