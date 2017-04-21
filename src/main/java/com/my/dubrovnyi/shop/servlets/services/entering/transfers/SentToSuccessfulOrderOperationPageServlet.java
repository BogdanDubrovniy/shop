package com.my.dubrovnyi.shop.servlets.services.entering.transfers;

import com.my.dubrovnyi.shop.db.DAOs.OrderDAO;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.OrderDTO;
import com.my.dubrovnyi.shop.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.USER_LOGIN_VALUE;

@WebServlet("/successfulOrder")
public class SentToSuccessfulOrderOperationPageServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(
            SentToSuccessfulOrderOperationPageServlet.class);
    private static final String SUCCESSFUL_OPERATION_PAGE =
            "/WEB-INF/servicePages/order/orderInfoSuccessful.jsp";
    private static final String LOGIN_JSP_PAGE = "/login.jsp";
    private static final String USER_ORDER_LIST_VALUE = "userOrderList";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.info("Was transferred order operation result page!");
        try {
            setUpOrdersInfo(req, resp);
        } catch (DAOException e) {
            LOG.error("Wrong page transfer");
            resp.sendRedirect(LOGIN_JSP_PAGE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            setUpOrdersInfo(req, resp);
        } catch (DAOException e) {
            LOG.error("Wrong page transfer");
            resp.sendRedirect(LOGIN_JSP_PAGE);
        }
    }

    private void setUpOrdersInfo(HttpServletRequest request,
                                 HttpServletResponse response)
            throws DAOException, IOException, ServletException {
        OrderDAO orderDAO = OrderDAO.getInstance();

        String login = (String) request.getSession().getAttribute(USER_LOGIN_VALUE);
        List<OrderDTO> orderDTOList = orderDAO.getUsersOrders(login);

        request.setAttribute(USER_ORDER_LIST_VALUE, orderDTOList);
        request.getRequestDispatcher(SUCCESSFUL_OPERATION_PAGE)
                .forward(request, response);
    }
}
