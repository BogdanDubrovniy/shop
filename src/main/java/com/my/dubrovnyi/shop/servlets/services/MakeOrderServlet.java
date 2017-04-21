package com.my.dubrovnyi.shop.servlets.services;

import com.my.dubrovnyi.shop.db.DAOs.BucketDAO;
import com.my.dubrovnyi.shop.db.DAOs.OrderDAO;
import com.my.dubrovnyi.shop.db.DAOs.UserDAO;
import com.my.dubrovnyi.shop.db.entities.BucketEntity;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.BucketDTO;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.GoodsDTO;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.OrderDTO;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.OrdersListDTO;
import com.my.dubrovnyi.shop.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/makeOrder")
public class MakeOrderServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(MakeOrderServlet.class);
    private static final String SUCCESSFUL_OPERATION_WAY =
            "/successfulOrder";
    private static final String ERROR_OPERATION_PAGE =
            "/WEB-INF/servicePages/order/orderInfoError.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            doOrder(request);
            LOG.info("Making order is successful!");
            request.getRequestDispatcher(SUCCESSFUL_OPERATION_WAY).forward(request, response);
        } catch (DAOException e) {
            LOG.error("Was error when making order!");
            request.getRequestDispatcher(ERROR_OPERATION_PAGE).forward(request, response);
        }
    }

    private void doOrder(HttpServletRequest request)
            throws DAOException {
        LOG.debug("Was creation new Order !");

        String login = (String) request.getSession().getAttribute(USER_LOGIN_VALUE);
        UserDAO userDAO = UserDAO.getInstance();
        OrderDAO orderDAO = OrderDAO.getInstance();
        BucketDAO bucketDAO = (BucketDAO) request.getSession()
                .getAttribute(BUCKET_ENTITIES_VALUE);

        int creationIndex = orderDAO.getNewOrderCreationIndex();
        if (creationIndex == -1) {
            throw new DAOException("Error when creation order!");
        }
        boolean operationResult;

        addGoodsToOrderList(request, orderDAO, bucketDAO, creationIndex);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setIdOrder(creationIndex);
        orderDTO.setStatus((String) request.getSession()
                .getAttribute(ORDER_STATUS));
        orderDTO.setDetails((String) request.getSession()
                .getAttribute(ORDER_DESCRIPTION));
        orderDTO.setDate(new Date().toString());
        orderDTO.setUserInfo(getUserInfo(userDAO, login));

        operationResult = orderDAO.fillOrderBody(orderDTO);
        if (!operationResult) {
            throw new DAOException("Error when fill the order values!");
        }
        operationResult = orderDAO.setOrderToUser(userDAO.getUserIdByLogin(login),
                creationIndex);
        if (!operationResult) {
            throw new DAOException("Error set order to user!");
        }
        request.getSession().setAttribute(BUCKET_GOOD_AMOUNT, null);
        request.getSession().setAttribute(BUCKET_TOTAL_PRICE, null);

        String deliveryMethod = request.getParameter(DELIVERY_METHOD);
        String address = request.getParameter(ADDRESS_VALUE);
        String cardId = request.getParameter(CARD_ID_VALUE);

        request.setAttribute(DELIVERY_METHOD, deliveryMethod);
        request.setAttribute(ADDRESS_VALUE, address);
        request.setAttribute(CARD_ID_VALUE, cardId);

        request.getSession().setAttribute(ORDER_STATUS, null);
        request.getSession().setAttribute(ORDER_DESCRIPTION, null);
    }

    private void addGoodsToOrderList(HttpServletRequest request, OrderDAO orderDAO,
                                     BucketDAO bucketDAO, int creationIndex)
            throws DAOException {
        boolean operationResult;
        Map<GoodsDTO, Integer> bucket = bucketDAO.getBucket();
        for (GoodsDTO entry : bucket.keySet()) {
            OrdersListDTO ordersListDTO = new OrdersListDTO();
            ordersListDTO.setIdList(creationIndex);
            ordersListDTO.setIdGoods(entry.getId());
            operationResult = orderDAO.addGoodToOrderList(ordersListDTO);
            if (!operationResult) {
                throw new DAOException("Error when adding good to order!");
            }
        }
        setUpOrderGoodsList(request, bucketDAO);
        request.getSession().setAttribute(BUCKET_ENTITIES_VALUE, null);
    }

    private String getUserInfo(UserDAO userDAO, String login) {
        return userDAO.getGetUserInfoByLogin(login);
    }

    private void setUpOrderGoodsList(HttpServletRequest request, BucketDAO bucketDAO) {
        List<BucketEntity> list = new BucketDTO(bucketDAO.getBucket())
                .getBucketEntity();
        request.setAttribute(BUCKET_LIST_VALUE, list);
    }
}

