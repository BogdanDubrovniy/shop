package com.my.dubrovnyi.shop.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Does the validation of input data before managing
 *
 * @author Bogdan Dubrovniy
 */
public class InputTextValidator {
    private static final String PASSWORD_REG_EXP = "^[[а-яА-Яa-zA-Z0-9]]{4,16}+$";
    private static final String NAME_REG_EXP = "^[а-яА-Яa-zA-Z]{5,19}+$";
    private static final String EMAIL_REG_EXP = "^.+@.+[.].{2,}$";

    /**
     * Check input Login or Password before does the operation
     *
     * @param inputValue input login or password
     * @return result of checking (success or no)
     */
    public static boolean checkPasswordAndLogin(String inputValue) {
        if (inputValue != null) {
            Pattern pa = Pattern.compile(PASSWORD_REG_EXP);
            Matcher m = pa.matcher(inputValue);
            return m.find();
        }
        return false;
    }

    /**
     * Check input Name Or Surname before does the operation
     *
     * @param inputValue input name
     * @return result of checking (success or no)
     */
    public static boolean checkName(String inputValue) {
        if (inputValue != null) {
            Pattern pa = Pattern.compile(NAME_REG_EXP);
            Matcher m = pa.matcher(inputValue);
            return m.find();
        }
        return false;
    }

    /**
     * Check input Email before does the operation
     *
     * @param inputValue input name
     * @return result of checking (success or no)
     */
    public static boolean checkEmail(String inputValue) {
        if (inputValue != null) {
            Pattern pattern = Pattern.compile(EMAIL_REG_EXP);
            Matcher m = pattern.matcher(inputValue);
            return m.find();
        }
        return false;
    }
}