package com.my.dubrovnyi.shop.db.DAOs;

import com.my.dubrovnyi.shop.db.entities.dataTransferObject.OrderDTO;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.OrdersListDTO;
import com.my.dubrovnyi.shop.exceptions.DAOException;
import com.my.dubrovnyi.shop.servlets.services.connection.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.ERROR_CODE;
import static com.my.dubrovnyi.shop.constants.ConstantsClass.PARAMETER_INDEX;
import static com.my.dubrovnyi.shop.servlets.services.connection.ConnectionManager.close;

public class OrderDAO {
    private static final Logger LOG = Logger.getLogger(OrderDAO.class);

    private static final String ORDER_CREATION_MESSAGE_ERROR
            = "Can not create new order ";
    private static final String GOOD_LIST_ADDING_MESSAGE_ERROR
            = "Can not add new good to order list ";
    private static final String ORDER_FILL_MESSAGE_ERROR
            = "Can not fill order ";
    private static final String SET_ORDER_MESSAGE_ERROR
            = "Can not set order to user ";
    private static final String GET_USER_ORDER_MESSAGE_ERROR
            = "Can not get users orders info ";

    private static final String CREATE_NEW_ORDER
            = "insert into orders values(default)";

    private static final String ADD_NEW_GOOD_TO_ORDER_LIST
            = "insert into orders_list values(?, ?)";

    private static final String FILL_ORDERS_BODY
            = "insert into orders_entity values(?, ?, ?, ?, ?)";

    private static final String SET_ORDER_TO_USER
            = "insert into user_orders values(?, ?)";

    private static final String GET_USERS_ORDER_INFO
            = "select orders_entity.order_id, orders_entity.order_entity_status,"
            + " orders_entity.order_entity_details, "
            + "orders_entity.order_entity_user_info, "
            + "orders_entity.order_entity_date "
            + "from users, user_orders, orders_entity where "
            + "users.user_login=? and user_id=id_user and "
            + "user_orders.id_orders=orders_entity.order_id";

    private static OrderDAO instance;

    public static synchronized OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    public int getNewOrderCreationIndex() throws DAOException {
        LOG.debug("Was called new order creator");
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            return getNewOrderCreationIndex(con);
        } catch (SQLException e) {
            ConnectionManager.rollback(con);
            LOG.error(ORDER_CREATION_MESSAGE_ERROR, e);
            throw new DAOException(ORDER_CREATION_MESSAGE_ERROR, e);
        } finally {
            ConnectionManager.close(con);
        }
    }

    public boolean addGoodToOrderList(OrdersListDTO ordersList) throws DAOException {
        LOG.debug("Was called add Good To Order List");
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            return addGoodToOrderList(con, ordersList);
        } catch (SQLException e) {
            ConnectionManager.rollback(con);
            LOG.error(GOOD_LIST_ADDING_MESSAGE_ERROR, e);
            throw new DAOException(GOOD_LIST_ADDING_MESSAGE_ERROR, e);
        } finally {
            ConnectionManager.close(con);
        }
    }

    public boolean fillOrderBody(OrderDTO orderDTO) throws DAOException {
        LOG.debug("Was called order fill");
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            return fillOrderBody(con, orderDTO);
        } catch (SQLException e) {
            ConnectionManager.rollback(con);
            LOG.error(ORDER_FILL_MESSAGE_ERROR, e);
            throw new DAOException(ORDER_FILL_MESSAGE_ERROR, e);
        } finally {
            ConnectionManager.close(con);
        }
    }

    public boolean setOrderToUser(int idUser, int idOrder) throws DAOException {
        LOG.debug("Was called set Order to User");
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            return setOrderToUser(con, idUser, idOrder);
        } catch (SQLException e) {
            ConnectionManager.rollback(con);
            LOG.error(SET_ORDER_MESSAGE_ERROR, e);
            throw new DAOException(SET_ORDER_MESSAGE_ERROR, e);
        } finally {
            ConnectionManager.close(con);
        }
    }

    public List<OrderDTO> getUsersOrders(String login) throws DAOException {
        LOG.debug("Was called users orders info");
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            return getUsersOrders(con, login);
        } catch (SQLException e) {
            ConnectionManager.rollback(con);
            LOG.error(GET_USER_ORDER_MESSAGE_ERROR, e);
            throw new DAOException(GET_USER_ORDER_MESSAGE_ERROR, e);
        } finally {
            ConnectionManager.close(con);
        }
    }

    private List<OrderDTO> getUsersOrders(Connection con, String login)
            throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(GET_USERS_ORDER_INFO);
            preparedStatement.setString(PARAMETER_INDEX, login);
            ResultSet rs = null;
            try {
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    OrderDTO orderDTO = new OrderDTO();
                    int columnIndex = 1;
                    orderDTO.setIdOrder(rs.getInt(columnIndex++));
                    orderDTO.setStatus(rs.getString(columnIndex++));
                    orderDTO.setDetails(rs.getString(columnIndex++));
                    orderDTO.setUserInfo(rs.getString(columnIndex++));
                    orderDTO.setDate(rs.getString(columnIndex));
                    list.add(orderDTO);
                }
                return list;
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private boolean setOrderToUser(Connection con, int idUser, int idOrder)
            throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(SET_ORDER_TO_USER);
            int columnIndex = 1;
            preparedStatement.setString(columnIndex, String.valueOf(idUser));
            columnIndex++;
            preparedStatement.setString(columnIndex, String.valueOf(idOrder));
            if (preparedStatement.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return false;
    }

    private boolean fillOrderBody(Connection con, OrderDTO orderDTO)
            throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(FILL_ORDERS_BODY);
            int columnIndex = 1;
            preparedStatement.setString(columnIndex,
                    String.valueOf(orderDTO.getIdOrder()));
            columnIndex++;
            preparedStatement.setString(columnIndex, orderDTO.getStatus());
            columnIndex++;
            preparedStatement.setString(columnIndex, orderDTO.getDetails());
            columnIndex++;
            preparedStatement.setString(columnIndex, orderDTO.getDate());
            columnIndex++;
            preparedStatement.setString(columnIndex, orderDTO.getUserInfo());

            if (preparedStatement.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return false;
    }

    private boolean addGoodToOrderList(Connection con, OrdersListDTO ordersList)
            throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(ADD_NEW_GOOD_TO_ORDER_LIST);
            int columnIndex = 1;
            preparedStatement.setString(columnIndex,
                    String.valueOf(ordersList.getIdList()));
            columnIndex++;
            preparedStatement.setString(columnIndex,
                    String.valueOf(ordersList.getIdGoods()));
            if (preparedStatement.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return false;
    }

    private int getNewOrderCreationIndex(Connection con)
            throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(CREATE_NEW_ORDER,
                    Statement.RETURN_GENERATED_KEYS);
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    con.commit();
                    return rs.getInt(PARAMETER_INDEX);
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
