package com.my.dubrovnyi.shop.db.queryCreator;

import com.my.dubrovnyi.shop.db.entities.valueContainers.FilterValues;

public class DBQueryCreator {

    private static final String LIMIT_VALUE = " limit ";
    private static final String ORDER_BY_VALUE = " order by ";
    private static final String WHERE_VALUE = " where ";
    private static final String AND_VALUE = " and ";
    private String inputQueryString;

    public DBQueryCreator(String inputQueryString) {
        this.inputQueryString = inputQueryString;
    }

    public String getFilteredPaginationQueryString(FilterValues filterValues,
                                                   int offset, int numberOfRecords) {
        StringBuilder sb = new StringBuilder();
        sb.append(getFilteredQueryString(filterValues));
        sb.append(LIMIT_VALUE).append(offset).append(COMMA_QUERY_VALUE)
                .append(numberOfRecords);
        return String.valueOf(sb);
    }

    public String getSortingQueryString(String orderValue, String direction,
                                        FilterValues filterValues) {
        StringBuilder sb = new StringBuilder();
        if (orderValue == null) {
            return getFilteredQueryString(filterValues);
        }
        if (direction == null) {
            direction = EMPTY_STRING_VALUE;
        }
        sb.append(getFilteredQueryString(filterValues)).append(ORDER_BY_VALUE)
                .append(orderValue).append(direction);
        return String.valueOf(sb);
    }

    public String getFilteredQueryString(FilterValues filterValues) {
        if (filterValues == null) {
            return inputQueryString;
        }
        StringBuilder sb = new StringBuilder();
        boolean setPreviousStatement = false;

        String name = filterValues.getName();
        String category = filterValues.getCategory();
        String firm = filterValues.getFirm();
        int firstValue = filterValues.getFirstValue();
        int secondValue = filterValues.getSecondValue();

        sb.append(inputQueryString).append(WHERE_VALUE);

        if (!EMPTY_STRING_VALUE.equals(name)) {
            sb.append(GOOD_NAME_QUERY_VALUE).append(EQUALS_SIGN)
                    .append(APOSTROPHE).append(name).append(APOSTROPHE);
            setPreviousStatement = true;
        }
        if (!EMPTY_STRING_VALUE.equals(category)) {
            if (setPreviousStatement) {
                sb.append(AND_VALUE);
            }
            sb.append(GOOD_CATEGORY_QUERY_VALUE)
                    .append(EQUALS_SIGN)
                    .append(APOSTROPHE)
                    .append(category)
                    .append(APOSTROPHE);

            setPreviousStatement = true;
        }
        if (!EMPTY_STRING_VALUE.equals(firm)) {
            if (setPreviousStatement) {
                sb.append(AND_VALUE);
            }
            sb.append(GOOD_FIRM_QUERY_VALUE)
                    .append(EQUALS_SIGN)
                    .append(APOSTROPHE)
                    .append(firm)
                    .append(APOSTROPHE);
            setPreviousStatement = true;
        }
        if (setPreviousStatement) {
            sb.append(AND_VALUE);
        }
        sb.append(GOOD_PRICE_QUERY_VALUE)
                .append(WHITE_SPACE_VALUE).append(MORE_EQUALS_SIGN)
                .append(WHITE_SPACE_VALUE)
                .append(firstValue)
                .append(AND_VALUE).append(GOOD_PRICE_QUERY_VALUE)
                .append(WHITE_SPACE_VALUE).append(LESS_EQUALS_SIGN)
                .append(WHITE_SPACE_VALUE)
                .append(secondValue);
        return String.valueOf(sb);
    }
}

