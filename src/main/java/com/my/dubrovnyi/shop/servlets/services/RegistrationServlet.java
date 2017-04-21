package com.my.dubrovnyi.shop.servlets.services;

import com.my.dubrovnyi.shop.db.DAOs.UserDAO;
import com.my.dubrovnyi.shop.db.entities.User;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.UserDTO;
import com.my.dubrovnyi.shop.db.entities.valueContainers.TimeParsingValues;
import com.my.dubrovnyi.shop.exceptions.DAOException;
import com.my.dubrovnyi.shop.servlets.services.imagesService.UserAvatarWriter;
import com.my.dubrovnyi.shop.validators.InputTextValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Does the registration of new client
 *
 * @author Bogdan Dubrovniy
 */
@MultipartConfig
@WebServlet("/registrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final String RIGHT_PATH = "/login.jsp";
    private static final String RETURN_PATH = "WEB-INF/servicePages/registrationPage.jsp";
    private final static Logger LOG = Logger.getLogger(RegistrationServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("Was called RegistrationServlet # doPost");
        LOG.debug("Registration was started");

        long timeOut = (long) request.getSession().getAttribute(TIMEOUT_ATTRIBUTE_VALUE);
        long previousCurrentTime = (long) request.getSession()
                .getAttribute(CURRENT_TIME_ATTRIBUTE_VALUE);
        long currentTime = new Date().getTime();

        UserDTO userDTO = new UserDTO();
        userDTO.setName(request.getParameter(USER_NAME_VALUE));
        userDTO.setSurname(request.getParameter(USER_SURNAME_VALUE));
        userDTO.setLogin(request.getParameter(USER_LOGIN_VALUE));
        userDTO.setPassword(request.getParameter(USER_PASSWORD_VALUE));
        userDTO.setPasswordConfirmed(request.getParameter(USER_PASSWORD_CONFIRM_VALUE));
        userDTO.setEmail(request.getParameter(USER_EMAIL_VALUE));

        TimeParsingValues times = new TimeParsingValues();
        times.setTimeOut(timeOut);
        times.setPreviousCurrentTime(previousCurrentTime);
        times.setCurrentTime(currentTime);

        try {
            registration(request, response, userDTO, times);
        } catch (DAOException e) {
            System.err.println(e.getMessage());
            setPreviousValuesAndReturnToPage(request, response, userDTO);
        }
        LOG.debug("Registration was end");
    }

    private void registration(HttpServletRequest request, HttpServletResponse response,
                              UserDTO userDTO, TimeParsingValues times)
            throws ServletException, IOException, DAOException {
        UserDAO usersDAO = UserDAO.getInstance();
        String code = request.getParameter(USER_CAPTCHA_VALUE);
        String captcha = captchaSaving(request);

        boolean validationResult = inputDTOValidation(userDTO)
                && captchaValidation(captcha, code) && timeOutValidation(times);
        int userRestrictId = usersDAO.createAndGetUserRestrictId();

        if (validationResult && (userRestrictId != -1)) {
            User user = new User();
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setLogin(userDTO.getLogin());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setRestrictId(userRestrictId);
            
            boolean answer = usersDAO.createUser(user);

            if (answer) {
                LOG.info("Registration is successful");
                new UserAvatarWriter(request, getServletContext(),
                        user.getName()).writeUserAvatar();
                response.sendRedirect(RIGHT_PATH);
            } else {
                LOG.error("Error registration");
                request.getRequestDispatcher(RETURN_PATH)
                        .forward(request, response);
            }
        } else {
            LOG.error("Input data are wrong! ");
            setPreviousValuesAndReturnToPage(request, response, userDTO);
        }
    }

    private void setPreviousValuesAndReturnToPage(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  UserDTO tempUser)
            throws ServletException, IOException {

        request.setAttribute(USER_NAME_VALUE, tempUser.getName());
        request.setAttribute(USER_SURNAME_VALUE, tempUser.getSurname());
        request.setAttribute(USER_LOGIN_VALUE, tempUser.getLogin());
        request.setAttribute(USER_EMAIL_VALUE, tempUser.getEmail());
        request.getRequestDispatcher(RETURN_PATH)
                .forward(request, response);
    }

    private String captchaSaving(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(USER_CAPTCHA_VALUE);
    }

    private boolean inputDTOValidation(UserDTO userDTO) {
        return InputTextValidator.checkPasswordAndLogin(userDTO.getLogin()) &&
                InputTextValidator.checkPasswordAndLogin(userDTO.getPassword())
                && userDTO.getPassword().equals(userDTO.getPasswordConfirmed())
                && InputTextValidator.checkName(userDTO.getName())
                && InputTextValidator.checkName(userDTO.getSurname())
                && InputTextValidator.checkEmail(userDTO.getEmail());
    }

    private boolean timeOutValidation(TimeParsingValues timeParsingValues) {
        return (timeParsingValues.getCurrentTime() - timeParsingValues
                .getPreviousCurrentTime()) <= timeParsingValues.getTimeOut();
    }

    private boolean captchaValidation(String captcha, String code) {
        return captcha.equals(code);
    }
}