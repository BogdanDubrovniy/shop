package com.my.dubrovnyi.shop.db.entities.valueContainers;

public class FilterValues {
    private String name;
    private String category;
    private String firm;
    private int firstValue;
    private int secondValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(int firstValue) {
        this.firstValue = firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(int secondValue) {
        this.secondValue = secondValue;
    }

    @Override
    public String toString() {
        return "FilterValues{"
                + "name='" + name + '\''
                + ", category='" + category + '\''
                + ", firm='" + firm + '\''
                + ", firstValue=" + firstValue
                + ", secondValue=" + secondValue
                + '}';
    }
}
