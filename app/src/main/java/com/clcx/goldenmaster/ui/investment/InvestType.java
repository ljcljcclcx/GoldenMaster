package com.clcx.goldenmaster.ui.investment;

/**
 * Created by ljc123 on 2016/10/28.
 */

public class InvestType {
    private float feedback;
    private String intro;
    private int hour;
    private int finalMoney;
    private long testTIME;

    public long getTestTIME() {
        return testTIME;
    }

    public void setTestTIME(long testTIME) {
        this.testTIME = testTIME;
    }

    public int getFinalMoney() {
        return finalMoney;
    }

    public void setFinalMoney(int finalMoney) {
        this.finalMoney = finalMoney;
    }

    public InvestType(int hour, float feedback, String intro) {
        this.hour = hour;
        this.feedback = feedback;
        this.intro = intro;
    }

    public int getHour() {
        return hour;
    }

    public float getFeedback() {
        return feedback;
    }

    public String getIntro() {
        return intro;
    }
}
