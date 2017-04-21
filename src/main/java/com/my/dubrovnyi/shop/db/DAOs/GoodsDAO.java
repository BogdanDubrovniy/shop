package com.my.dubrovnyi.shop.db.DAOs;

import com.my.dubrovnyi.shop.db.entities.Goods;
import com.my.dubrovnyi.shop.db.entities.dataTransferObject.GoodsDTO;
import com.my.dubrovnyi.shop.db.entities.valueContainers.FilterValues;
import com.my.dubrovnyi.shop.db.queryCreator.DBQueryCreator;
import com.my.dubrovnyi.shop.exceptions.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.COMMA_QUERY_VALUE;
import static com.my.dubrovnyi.shop.constants.ConstantsClass.PARAMETER_INDEX;
import static com.my.dubrovnyi.shop.servlets.services.connection.ConnectionManager.close;
import static com.my.dubrovnyi.shop.servlets.services.connection.ConnectionManager.getConnection;
import static com.my.dubrovnyi.shop.servlets.services.connection.ConnectionManager.rollback;

public class GoodsDAO {
    private static final Logger LOG = Logger.getLogger(GoodsDAO.class);

    private static final String ERROR_MESSAGE_GETTING_GOODS = "Can not get all goods ";
    private static final String LIMIT_QUERY_VALUE = " limit ";

    private static final String FIND_ALL_GOODS
            = "select good_id, good_name, good_category, "
            + "good_firm, good_price, good_photo from "
            + "shop_db.goods";

    private static final String GET_GOOD_ROWS_NUMBER_COUNT
            = "select count(good_id) from shop_db.goods";

    private static final String GET_GOOD_BY_ID
            = "select good_id, good_name, good_category, good_firm, "
            + "good_price from shop_db.goods where good_id = ?";

    private static GoodsDAO instance;

    public static synchronized GoodsDAO getInstance() {
        if (instance == null) {
            instance = new GoodsDAO();
        }
        return instance;
    }

    public GoodsDTO getGoodInfoById(int id) {
        LOG.debug("Was called good collector");
        GoodsDTO goodsDTO;
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(GET_GOOD_BY_ID);
                st.setString(PARAMETER_INDEX, String.valueOf(id));
                ResultSet rs = null;
                try {
                    rs = st.executeQuery();
                    if (rs.next()) {
                        goodsDTO = new GoodsDTO();

                        int columnIndex = 1;
                        goodsDTO.setId(rs.getInt(columnIndex++));
                        goodsDTO.setName(rs.getString(columnIndex++));
                        goodsDTO.setCategory(rs.getString(columnIndex++));
                        goodsDTO.setFirm(rs.getString(columnIndex++));
                        goodsDTO.setPrice(rs.getInt(columnIndex));
                        return goodsDTO;
                    }
                    con.commit();
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
            LOG.error("Error when calling good by id collector " + e.getMessage());
        } finally {
            close(con);
        }
        return null;
    }


    public List<Goods> getSortedGoods(String orderValue, String direction,
                                      FilterValues filterValues, int offset,
                                      int numberOfRecords)
            throws DAOException {
        String query = new DBQueryCreator(FIND_ALL_GOODS)
                .getSortingQueryString(orderValue, direction, filterValues)
                + LIMIT_QUERY_VALUE + offset + COMMA_QUERY_VALUE + numberOfRecords;
        return getAllGoods(query);
    }

    public List<Goods> getFilteredGoods(FilterValues filterValues)
            throws DAOException {
        String query = new DBQueryCreator(FIND_ALL_GOODS)
                .getFilteredQueryString(filterValues);
        System.out.println(query);
        return getAllGoods(query);
    }

    public List<Goods> getFilteredPaginationGoods(FilterValues filterValues,
                                                  int offset, int numberOfRecords)
            throws DAOException {
        String query = new DBQueryCreator(FIND_ALL_GOODS)
                .getFilteredPaginationQueryString(filterValues, offset, numberOfRecords);
        return getAllGoods(query);
    }

    public int getRowsNumber() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(GET_GOOD_ROWS_NUMBER_COUNT);
            if (rs.next()) {
                return rs.getInt(PARAMETER_INDEX);
            }
        } catch (SQLException e) {
            System.err.println("Error when getting rows count");
        } finally {
            close(rs);
            close(stmt);
            close(connection);
        }
        return 0;
    }

    private List<Goods> getAllGoods(String query) throws DAOException {
        LOG.debug("Was called user getting by login");
        List<Goods> goodsList;
        Connection con = null;
        try {
            con = getConnection();
            goodsList = getAllGoods(con, query);
            if (goodsList != null) {
                con.commit();
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(ERROR_MESSAGE_GETTING_GOODS, ex);
            throw new DAOException(ERROR_MESSAGE_GETTING_GOODS, ex);
        } finally {
            close(con);
        }
        return goodsList;
    }

    private List<Goods> getAllGoods(Connection con, String query) throws SQLException {
        List<Goods> goodsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(query);
            ResultSet rs = null;
            try {
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Goods good = new Goods();

                    int columnIndex = 1;
                    good.setId(rs.getInt(columnIndex++));
                    good.setName(rs.getString(columnIndex++));
                    good.setCategory(rs.getString(columnIndex++));
                    good.setFirm(rs.getString(columnIndex++));
                    good.setPrice(rs.getInt(columnIndex++));
                    good.setPhotoPath(rs.getString(columnIndex));

                    goodsList.add(good);
                }
                return goodsList;
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
}
