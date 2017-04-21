package com.my.dubrovnyi.shop.servlets.services.connection;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ConnectionManager {
    private static final Logger LOG = Logger.getLogger(ConnectionManager.class);
    /**
     * Uses JNDI and Datasource (preferred style).
     */
    private static final String DATASOURCE_CONTEXT = "java:comp/env/jdbc/shop_db";

    public static Connection getConnection() {
        Connection result = null;
        try {
            Context initialContext = new InitialContext();
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                result = datasource.getConnection();
                result.setAutoCommit(false);
            } else {
                System.err.println("Failed to lookup datasource.");
                LOG.error("Failed to lookup datasource.");
            }
        } catch (NamingException | SQLException ex) {
            System.err.println("Cannot get connection: " + ex);
            LOG.error("Cannot get connection: " + ex);
        }
        return result;
    }

    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                LOG.error("Can not to close DB", e);
            }
            LOG.info("Close connection is successful");
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.error("Can not to close result set", e);
            }
            LOG.info("Close result set is successful");
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                LOG.error("Can not to close statement", e);
            }
            LOG.info("Close statement is successful");
        }
    }

    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }
}
