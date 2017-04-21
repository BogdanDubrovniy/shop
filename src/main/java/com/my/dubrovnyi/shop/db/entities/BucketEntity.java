package com.my.dubrovnyi.shop.db.entities;

import com.my.dubrovnyi.shop.db.entities.dataTransferObject.GoodsDTO;

/**
 * Does the immutable objects
 */
public class BucketEntity {
    private final GoodsDTO good;
    private final int amount;

    public BucketEntity(GoodsDTO good, int amount) {
        if (good == null) {
            good = new GoodsDTO();
        }
        this.good = good;
        this.amount = amount;
    }

    public int getId() {
        return good.getId();
    }

    public String getName() {
        return good.getName();
    }

    public String getCategory() {
        return good.getCategory();
    }

    public String getFirm() {
        return good.getFirm();
    }

    public int getPrice() {
        return good.getPrice();
    }

    public int getAmount() {
        return amount;
    }
}
