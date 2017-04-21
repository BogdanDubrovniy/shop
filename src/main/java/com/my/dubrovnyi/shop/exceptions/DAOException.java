package com.my.dubrovnyi.shop.exceptions;

/**
 * DB Exception of using in project
 *
 * @author Bogdan Dubrovniy
 */

public class DAOException extends Exception {

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message) {
        super(message);
    }
}
