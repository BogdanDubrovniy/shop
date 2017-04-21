package com.my.dubrovnyi.shop.db.entities.dataTransferObject;

public class OrdersListDTO {
    private int idList;
    private int idGoods;

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public int getIdGoods() {
        return idGoods;
    }

    public void setIdGoods(int idGoods) {
        this.idGoods = idGoods;
    }
}
