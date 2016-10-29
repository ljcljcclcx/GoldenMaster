package com.clcx.goldenmaster.beans;

import com.clcx.goldenmaster.ui.appraisal.AppraisalType;

/**
 * Created by ljc123 on 2016/10/21.
 * 成品
 */
public class AlItemProduct extends AlchemiItem {
    public AlItemProduct(String prename, String lastName, float[] propertiesPoint, int[] gainStateId, int[]
            restrainStateId, int alchemistExp) {
        super(prename, lastName, propertiesPoint, gainStateId, restrainStateId);
        for (int i = 0; i < propertiesPoint.length; i++) {
            this.price += countFinalPrice(propertiesPoint[i], i);
        }
        if (this.price <= 0) {
            this.price = 1;
        }

        this.price *= 1.5f + ((float) alchemistExp / 800.0f);
        this.type = "成品";
    }

    /**
     * 鉴定后重命名
     */
    public void reName(AppraisalType type) {
        this.prename = type.getName();
        this.lastName = "药剂";
        this.price *= type.getScore();
        this.setAppraisal(true);
    }
}
