package com.my.dubrovnyi.shop.constants;

public enum XML {

    SECURITY("security"), CONSTRAINT("constraint"),

    URL_PATH("url-path"), ROLE("role"),

    //path for entries:
    TNS_URI("http://entities");

    private String value;

    XML(String value) {
        this.value = value;
    }

    /**
     * @param name string to compare with value
     * @return value.equals(name)
     */
    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    /**
     * @return value of enum entities
     */
    public String value() {
        return value;
    }
}