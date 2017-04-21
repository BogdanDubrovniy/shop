package com.my.dubrovnyi.shop.db.entities.xml;

public class Rule {
    private String role;
    private String urlPath;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUrlPatter() {
        return urlPath;
    }

    public void setUrlPatter(String urlPath) {
        this.urlPath = urlPath;
    }

    @Override
    public String toString() {
        return "Rule{"
                + "role=" + role + ", urlPatter=" + urlPath + '}';
    }
}
