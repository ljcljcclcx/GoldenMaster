package com.clcx.goldenmaster.customview;

/**
 * Created by ljc123 on 2016/5/30.
 */
public class CapacityBean {

    private String capacityName;
    private int capacityPoint;

    public CapacityBean(String capacityName, int capacityPoint) {
        this.capacityName = capacityName;
        this.capacityPoint = capacityPoint;
    }

    public String getCapacityName() {
        return capacityName;
    }

    public int getCapacityPoint() {
        return capacityPoint;
    }

    public void setCapacityName(String capacityName) {
        this.capacityName = capacityName;
    }

    public void setCapacityPoint(int capacityPoint) {
        this.capacityPoint = capacityPoint;
    }
}
