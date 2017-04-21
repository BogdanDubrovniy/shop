package com.my.dubrovnyi.shop.db.entities;

import java.util.Date;

public class UserRestrict {
    private int id;
    private int triesCount;
    private long actualBlockTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTriesCount() {
        return triesCount;
    }

    public void setTriesCount(int triesCount) {
        this.triesCount = triesCount;
    }

    public long getActualBlockTime() {
        return actualBlockTime;
    }

    public void setActualBlockTime(long actualBlockTime) {
        this.actualBlockTime = actualBlockTime;
    }

    @Override
    public String toString() {
        return "Restrict{"
                + "id=" + id
                + ", triesCount=" + triesCount
                + ", actualBlockTime=" + new Date(actualBlockTime)
                + '}';
    }
}
