package com.my.dubrovnyi.shop.servlets.services.entering;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.BUCKET_ENTITIES_VALUE;
import static com.my.dubrovnyi.shop.constants.ConstantsClass.USER_LOGIN_VALUE;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(LogOutServlet.class);
    private static final String LOGIN_JSP_PAGE = "/login.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("Was called logout");
        logOut(request, response);
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute(USER_LOGIN_VALUE, null);
        session.setAttribute(BUCKET_ENTITIES_VALUE, null);

        request.getRequestDispatcher(LOGIN_JSP_PAGE).forward(request, response);
        LOG.info("Logout is successful");
    }
}

