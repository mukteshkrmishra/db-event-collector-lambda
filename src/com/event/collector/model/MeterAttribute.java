package com.event.collector.model;

public class MeterAttribute {

    private String M15_RATE;
    private String COUNT;
    private String M5_RATE;
    private String MEAN_RATE;
    private String M1_RATE;

    public String getM15_RATE() {
        return M15_RATE;
    }

    public void setM15_RATE(String m15_RATE) {
        M15_RATE = m15_RATE;
    }

    public String getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(String COUNT) {
        this.COUNT = COUNT;
    }

    public String getM5_RATE() {
        return M5_RATE;
    }

    public void setM5_RATE(String m5_RATE) {
        M5_RATE = m5_RATE;
    }

    public String getMEAN_RATE() {
        return MEAN_RATE;
    }

    public void setMEAN_RATE(String MEAN_RATE) {
        this.MEAN_RATE = MEAN_RATE;
    }

    public String getM1_RATE() {
        return M1_RATE;
    }

    public void setM1_RATE(String m1_RATE) {
        M1_RATE = m1_RATE;
    }
}
