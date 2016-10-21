package com.clcx.goldenmaster.beans;

/**
 * Created by ljc123 on 2016/10/21.
 * 成品
 */
public class AlItemProduct extends AlchemiItem {
    public AlItemProduct(String name, int[] propertiesPoint) {
        super(name, propertiesPoint);

        for (int i = 0; i < propertiesPoint.length; i++) {
            this.price += propertiesPoint[i];
        }
        if (this.price <= 0) {
            this.price = 1;
        }
        this.price *= 2;
        this.type = "成品";
    }
}
