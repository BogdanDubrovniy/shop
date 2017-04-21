package com.my.dubrovnyi.shop.servlets.services.entering;

import com.my.dubrovnyi.shop.db.DAOs.UserDAO;
import com.my.dubrovnyi.shop.db.entities.UserRestrict;
import com.my.dubrovnyi.shop.exceptions.DAOException;
import com.my.dubrovnyi.shop.userService.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.USER_LOGIN_VALUE;

@WebServlet("/loginServlet")
public class LogInServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(LogInServlet.class);
    private static final long BLOCK_TIME = 300000L; // 5 min
    private static final int ERROR_PAGE_CODE = 401;
    private static final int MIN_USER_TRY_NUMBER = 1;
    private static final int MAX_USER_TRIES = 3;
    private static final int INCREMENT = 1;
    private static final String INPUT_LOGIN_VALUE = "inputLogin";
    private static final String INPUT_PASSWORD_VALUE = "inputPassword";
    private static final String LOGIN_SERVLET = "/login";
    private static final String RETURN_PAGE_VALUE = "returnPage";
    private static final String PERSONAL_PAGE_SERVLET = "/personalPage";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.debug("Was started logging");
        try {
            login(req, resp);
            LOG.debug("Logging was ended");
        } catch (DAOException e) {
            LOG.error("Can not logged");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("Was started logging");
        try {
            login(request, response);
            LOG.debug("Logging was ended");
        } catch (DAOException e) {
            LOG.error("Can not logged");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DAOException {
        UserService userService = new UserService(UserDAO.getInstance());

        String login = request.getParameter(INPUT_LOGIN_VALUE);
        String password = request.getParameter(INPUT_PASSWORD_VALUE);

        String userLogin = userService.getUserLogin(login, password);
        if (isUserBlocked(login, userService)) {
            response.sendError(ERROR_PAGE_CODE);
        } else {
            if (userService.isUserExists(userLogin)) {
                userService.cleanUserRestrict(login);
                transfer(request, response, userLogin);
            } else {
                LOG.trace("Wrong login or password, next try");
                UserRestrict newRestrict = new UserRestrict();
                UserRestrict oldRestrict = userService.getUserRestrict(login);

                newRestrict.setId(userService.getUserId(login));
                newRestrict.setTriesCount(oldRestrict.getTriesCount() + INCREMENT);

                if (newRestrict.getTriesCount() >= MAX_USER_TRIES) {
                    newRestrict.setActualBlockTime(new Date().getTime() + BLOCK_TIME);
                    newRestrict.setTriesCount(MIN_USER_TRY_NUMBER);
                }
                userService.updateUserRestrict(login, newRestrict);
                request.getRequestDispatcher(LOGIN_SERVLET)
                        .forward(request, response);
            }
        }
    }

    private void transfer(HttpServletRequest request, HttpServletResponse response,
                          String userLogin) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String returnPage = (String) session.getAttribute(RETURN_PAGE_VALUE);
        request.getSession().setAttribute(USER_LOGIN_VALUE, userLogin);
        if (returnPage != null) {
            session.setAttribute(RETURN_PAGE_VALUE, null);
            request.getRequestDispatcher(returnPage).forward(request, response);
        } else {
            LOG.info("Was detected user name");
            request.getRequestDispatcher(PERSONAL_PAGE_SERVLET)
                    .forward(request, response);
        }
    }

    private boolean isUserBlocked(String login, UserService userService) {
        Date currentDate = new Date();
        return userService.getUserRestrict(login)
                .getActualBlockTime() > currentDate.getTime();
    }
}