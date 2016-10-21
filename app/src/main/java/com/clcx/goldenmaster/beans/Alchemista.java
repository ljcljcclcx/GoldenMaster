package com.clcx.goldenmaster.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class Alchemista implements Serializable {

    private String name;
    private List<AlchemiItem> bag;
    private int exp;
    private int sleepy;
    private int hungry;
    private int thirsty;

    public Alchemista(String name) {
        this.name = name;
        exp = 0;
        sleepy = 100;
        hungry = 100;
        thirsty = 100;
        bag = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AlchemiItem> getBag() {
        return bag;
    }

    public void setBag(List<AlchemiItem> bag) {
        this.bag = bag;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getSleepy() {
        return sleepy;
    }

    public void setSleepy(int sleepy) {
        this.sleepy = sleepy;
    }

    public int getHungry() {
        return hungry;
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    public int getThirsty() {
        return thirsty;
    }

    public void setThirsty(int thirsty) {
        this.thirsty = thirsty;
    }
}
