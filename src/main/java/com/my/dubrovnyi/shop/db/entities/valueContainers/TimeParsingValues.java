package com.my.dubrovnyi.shop.db.entities.valueContainers;

public class TimeParsingValues {
    private long timeOut;
    private long previousCurrentTime;
    private long currentTime;

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public long getPreviousCurrentTime() {
        return previousCurrentTime;
    }

    public void setPreviousCurrentTime(long previousCurrentTime) {
        this.previousCurrentTime = previousCurrentTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
}
