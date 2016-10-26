package com.clcx.goldenmaster.customview;

import android.graphics.Color;

/**
 * Created by ljc123 on 2016/5/30.
 */
public class CapacityBean {

    private String capacityName;
    private float capacityPoint;
    private int pointColor;

    public CapacityBean(String capacityName, float capacityPoint, int pointColor) {
        this.capacityName = capacityName;
        this.capacityPoint = capacityPoint;
        this.pointColor = pointColor;
    }

    public CapacityBean(String capacityName, int capacityPoint) {
        this.capacityName = capacityName;
        this.capacityPoint = capacityPoint;
        this.pointColor = Color.rgb(0, 0, 0);
    }

    public String getCapacityName() {
        return capacityName;
    }

    public float getCapacityPoint() {
        return capacityPoint;
    }

    public int getPointColor() {
        return pointColor;
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
    }
}
