package com.my.dubrovnyi.shop.db.entities;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.PRIME_NUMBER_THIRTY_ONE;

public class Goods {
    private int id;
    private String name;
    private String category;
    private String firm;
    private int price;
    private String photoPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Goods goods = (Goods) o;
        if (id != goods.id) {
            return false;
        }
        if (price != goods.price) {
            return false;
        }
        if (name != null ? !name.equals(goods.name) : goods.name != null) {
            return false;
        }
        if (category != null ? !category.equals(goods.category)
                : goods.category != null) {
            return false;
        }
        if (firm != null ? !firm.equals(goods.firm) : goods.firm != null) {
            return false;
        }
        return photoPath != null ? photoPath.equals(goods.photoPath)
                : goods.photoPath == null;
    }

    /**
     * hashCode will be as vendor code
     */
    @Override
    public int hashCode() {
        int result = id;
        result = PRIME_NUMBER_THIRTY_ONE * result
                + (name != null ? name.hashCode() : 0);
        result = PRIME_NUMBER_THIRTY_ONE * result
                + (category != null ? category.hashCode() : 0);
        result = PRIME_NUMBER_THIRTY_ONE * result
                + (firm != null ? firm.hashCode() : 0);
        result = PRIME_NUMBER_THIRTY_ONE * result
                + price;
        result = PRIME_NUMBER_THIRTY_ONE * result
                + (photoPath != null ? photoPath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Goods{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", category='" + category + '\''
                + ", firm='" + firm + '\''
                + ", price=" + price
                + ", photoPath='" + photoPath + '\''
                + '}';
    }
}
