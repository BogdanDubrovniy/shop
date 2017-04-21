package com.my.dubrovnyi.shop.db.DAOs;

import com.my.dubrovnyi.shop.db.entities.User;
import com.my.dubrovnyi.shop.db.entities.UserRestrict;
import com.my.dubrovnyi.shop.exceptions.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.COMMA_QUERY_VALUE;
import static com.my.dubrovnyi.shop.constants.ConstantsClass.ERROR_CODE;
import static com.my.dubrovnyi.shop.constants.ConstantsClass.PARAMETER_INDEX;

public class UserDAO {
    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    private static final String CREATE_USER_ERROR_MESSAGE = "Can not create User ";
    private static final String UPDATE_RESTRICT_ERROR_MESSAGE
            = "Can not update user restrict updater ";
    private static final String CREATE_USER_RESTRICTION_ERROR_MESSAGE
            = "Can not create new User restriction ";

    private static final String FIND_USER_NAME_BY_LOGIN_AND_PASSWORD
            = "select user_login from shop_db.users where user_login = ? "
            + "and user_password = ?";
    private static final String CREATE_USER_RESTRICT
            = "insert into user_restrict values(default, 0, 0)";
    private static final String CREATE_USER
            = "insert into shop_db.users values(default, ?, ?, ?, ?, ?, false, ?)";
    private static final String FIND_USER_ID_BY_LOGIN
            = "select user_id from shop_db.users where user_login = ?";
    private static final String GET_USER_INFO_BY_LOGIN
            = "select users.user_name, users.user_surname, users.user_email "
            + "from users where user_login=?";
    private static final String UPDATE_USER_RESTRICT
            = "update user_restrict set user_restrict.restrict_tries=?, "
            + "user_restrict.restrict_time=? where user_restrict.restict_id=?";
    private static final String GET_INFORMATION_ABOUT_RESTRICTION_BY_LOGIN
            = "select user_restrict.restict_id, user_restrict.restrict_tries, "
            + "user_restrict.restrict_time from user_restrict, users where "
            + "users.id_user_restrict=user_restrict.restict_id and "
            + "users.user_login =?";

    private static UserDAO instance;

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public boolean createUser(User user) throws DAOException {
        LOG.debug("Was called user creator");
        boolean flag;
        Connection con = null;
        try {
            con = getConnection();
            flag = createUser(con, user);
            if (flag) {
                con.commit();
            }
        } catch (SQLException e) {
            rollback(con);
            LOG.error(CREATE_USER_ERROR_MESSAGE + user, e);
            throw new DAOException(CREATE_USER_ERROR_MESSAGE + user, e);
        } finally {
            close(con);
        }
        return flag;
    }

    public boolean updateUserRestrict(UserRestrict userRestrict)
            throws DAOException {
        LOG.debug("Was called user restrict updater");
        boolean flag;
        Connection con = null;
        try {
            con = getConnection();
            flag = updateUserRestrict(con, userRestrict);
            if (flag) {
                con.commit();
            }
        } catch (SQLException e) {
            rollback(con);
            LOG.error(UPDATE_RESTRICT_ERROR_MESSAGE
                    + userRestrict, e);
            throw new DAOException(UPDATE_RESTRICT_ERROR_MESSAGE
                    + userRestrict, e);
        } finally {
            close(con);
        }
        return flag;
    }

    public String getGetUserInfoByLogin(String login) {
        LOG.debug("Was called user info collector");
        StringBuilder stringBuilder = new StringBuilder();
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(GET_USER_INFO_BY_LOGIN);
                st.setString(PARAMETER_INDEX, login);
                ResultSet rs = null;
                try {
                    rs = st.executeQuery();
                    while (rs.next()) {
                        int columnNumber = 1;
                        stringBuilder.append(rs.getString(columnNumber++))
                                .append(COMMA_QUERY_VALUE)
                                .append(rs.getString(columnNumber++))
                                .append(COMMA_QUERY_VALUE)
                                .append(rs.getString(columnNumber));
                    }
                    con.commit();
                    return String.valueOf(stringBuilder);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                }
            } finally {
                if (st != null) {
                    st.close();
                }
            }
        } catch (SQLException e) {
            rollback(con);
            System.err.println(e.getMessage());
            LOG.error("Error when calling user info collector " + e.getMessage());
        } finally {
            close(con);
        }
        return null;
    }

    public String getUserLogin(String login, String password) {
        LOG.debug("Was called user name collector");
        String result = null;

        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(FIND_USER_NAME_BY_LOGIN_AND_PASSWORD);
                int parameterIndex = 1;
                st.setString(parameterIndex++, login);
                st.setString(parameterIndex, password);
                ResultSet rs = null;
                try {
                    rs = st.executeQuery();
                    while (rs.next()) {
                        result = rs.getString(1);
                    }
                    con.commit();
                    return result;
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                }
            } finally {
                if (st != null) {
                    st.close();
                }
            }
        } catch (SQLException e) {
            rollback(con);
            System.err.println(e.getMessage());
            LOG.error("Error when calling user name collector " + e.getMessage());
        } finally {
            close(con);
        }
        return null;
    }

    public int getUserIdByLogin(String login) {
        LOG.debug("Was called user id collector");
        int result = -1;
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(FIND_USER_ID_BY_LOGIN);
                st.setString(PARAMETER_INDEX, login);
                ResultSet rs = null;
                try {
                    rs = st.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt(1);
                    }
                    con.commit();
                    return result;
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                }
            } finally {
                if (st != null) {
                    st.close();
                }
            }
        } catch (SQLException e) {
            rollback(con);
            System.err.println(e.getMessage());
            LOG.error("Error when calling user id collector " + e.getMessage());
        } finally {
            close(con);
        }
        return result;
    }

    public int createAndGetUserRestrictId() throws DAOException {
        LOG.debug("Was called new User restriction");
        Connection con = null;
        try {
            con = getConnection();
            return createAndGetUserRestrictId(con);
        } catch (SQLException e) {
            rollback(con);
            LOG.error(CREATE_USER_RESTRICTION_ERROR_MESSAGE, e);
            throw new DAOException(CREATE_USER_RESTRICTION_ERROR_MESSAGE, e);
        } finally {
            close(con);
        }
    }

    public UserRestrict getUserRestrict(String login) {
        LOG.debug("Was called user restriction collector");
        UserRestrict userRestrict = null;
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(GET_INFORMATION_ABOUT_RESTRICTION_BY_LOGIN);
                st.setString(PARAMETER_INDEX, login);
                ResultSet rs = null;
                try {
                    rs = st.executeQuery();
                    while (rs.next()) {
                        userRestrict = new UserRestrict();
                        int columnIndex = 1;
                        userRestrict.setId(rs.getInt(columnIndex++));
                        userRestrict.setTriesCount(rs.getInt(columnIndex++));
                        userRestrict.setActualBlockTime(rs.getLong(columnIndex));
                    }
                    con.commit();
                    return userRestrict;
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                }
            } finally {
                if (st != null) {
                    st.close();
                }
            }
        } catch (SQLException e) {
            rollback(con);
            System.err.println(e.getMessage());
            LOG.error("Error when calling user restriction collector "
                    + e.getMessage());
        } finally {
            close(con);
        }
        return null;
    }

    private boolean createUser(Connection con, User user) throws SQLException {
        boolean res = false;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = con.prepareStatement(
                    CREATE_USER, Statement.RETURN_GENERATED_KEYS);

            int parameterIndex = 1;
            preparedStatement.setString(parameterIndex++, user.getName());
            preparedStatement.setString(parameterIndex++, user.getSurname());
            preparedStatement.setString(parameterIndex++, user.getLogin());
            preparedStatement.setString(parameterIndex++, user.getPassword());
            preparedStatement.setString(parameterIndex++, user.getEmail());
            preparedStatement.setString(parameterIndex,
                    String.valueOf(user.getRestrictId()));

            if (preparedStatement.executeUpdate() > 0) {
                rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    user.setId(id);
                    res = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return res;
    }

    private boolean updateUserRestrict(Connection con, UserRestrict userRestrict)
            throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(UPDATE_USER_RESTRICT);
            int parameterIndex = 1;
            preparedStatement.setString(parameterIndex++,
                    String.valueOf(userRestrict.getTriesCount()));
            preparedStatement.setString(parameterIndex++,
                    String.valueOf(userRestrict.getActualBlockTime()));
            preparedStatement.setString(parameterIndex,
                    String.valueOf(userRestrict.getId()));
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return false;
    }

    private int createAndGetUserRestrictId(Connection con) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(CREATE_USER_RESTRICT,
                    Statement.RETURN_GENERATED_KEYS);
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    LOG.info("Restrict is created");
                    con.commit();
                    return rs.getInt(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return ERROR_CODE;
    }
}
