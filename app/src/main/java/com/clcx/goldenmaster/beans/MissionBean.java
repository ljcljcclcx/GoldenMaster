package com.clcx.goldenmaster.beans;

import java.io.Serializable;

/**
 * Created by ljc123 on 2016/10/30.
 */

public class MissionBean implements Serializable {
    private String intro;
    private int typeId;
    private int reward;
    private int needNumber;//需要的数量
    private int currentNumber;//需要的数量
    private boolean isFinish;//已完成


    public MissionBean(String intro, int needNumber, int typeId, int reward) {
        this.intro = intro;
        this.typeId = typeId;
        this.reward = reward;
        isFinish = false;
        this.needNumber = needNumber;
        currentNumber = 0;
    }

    public void addNumber() {
        currentNumber++;
        if (currentNumber >= needNumber) {
            currentNumber = needNumber;
            isFinish = true;
        }
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public int getNeedNumber() {
        return needNumber;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public String getIntro() {
        return intro;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getReward() {
        return reward;
    }
}
