package com.my.dubrovnyi.shop.userService;

import com.my.dubrovnyi.shop.db.DAOs.UserDAO;
import com.my.dubrovnyi.shop.db.entities.UserRestrict;
import com.my.dubrovnyi.shop.exceptions.DAOException;

/**
 * Class Wrapper under UserDAO
 */
public class UserService {
    private UserDAO userDAO;
    private static final int MIN_USER_TRY_NUMBER = 1;
    private static final int DEFAULT_BLOCKER_TIME = 0;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String getUserLogin(String login, String password) {
        return userDAO.getUserLogin(login, password);
    }

    public boolean isUserExists(String login) {
        return login != null;
    }

    public void cleanUserRestrict(String login) throws DAOException {
        int userId = userDAO.getUserIdByLogin(login);
        UserRestrict userRestrict = new UserRestrict();
        userRestrict.setId(userId);
        userRestrict.setTriesCount(MIN_USER_TRY_NUMBER);
        userRestrict.setActualBlockTime(DEFAULT_BLOCKER_TIME);
        updateUserRestrict(login, userRestrict);
    }

    public void updateUserRestrict(String login, UserRestrict userRestrict)
            throws DAOException {
        int userId = userDAO.getUserIdByLogin(login);
        if (userId != -1) {
            userDAO.updateUserRestrict(userRestrict);
        }
    }

    public UserRestrict getUserRestrict(String login) {
        return userDAO.getUserRestrict(login);
    }

    public int getUserId(String login) {
        return userDAO.getUserIdByLogin(login);
    }
}