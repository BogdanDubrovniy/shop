package com.my.dubrovnyi.shop.db.entities.dataTransferObject;

import static com.my.dubrovnyi.shop.constants.ConstantsClass.PRIME_NUMBER_THIRTY_ONE;

public class GoodsDTO {
    private int id;
    private String name;
    private String category;
    private String firm;
    private int price;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GoodsDTO goodsDTO = (GoodsDTO) o;
        if (id != goodsDTO.id) {
            return false;
        }
        if (price != goodsDTO.price) {
            return false;
        }
        if (name != null ? !name.equals(goodsDTO.name) : goodsDTO.name != null) {
            return false;
        }
        if (category != null ? !category.equals(goodsDTO.category) :
                goodsDTO.category != null) {
            return false;
        }
        return firm != null ? firm.equals(goodsDTO.firm) : goodsDTO.firm == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = PRIME_NUMBER_THIRTY_ONE * result
                + (name != null ? name.hashCode() : 0);
        result = PRIME_NUMBER_THIRTY_ONE * result
                + (category != null ? category.hashCode() : 0);
        result = PRIME_NUMBER_THIRTY_ONE * result
                + (firm != null ? firm.hashCode() : 0);
        result = PRIME_NUMBER_THIRTY_ONE * result + price;
        return Math.abs(result);
    }
}