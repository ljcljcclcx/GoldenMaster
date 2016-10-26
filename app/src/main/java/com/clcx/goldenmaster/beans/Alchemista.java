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
    private int energe;
    private int money;

    public Alchemista(String name) {
        this.name = name;
        exp = 0;
        energe = 100;
        bag = new ArrayList<>();
        money = 100;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int m) {
        money += m;
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

    public int getEnerge() {
        return energe;
    }

    public void setEnerge(int energe) {
        this.energe = energe;
    }

}
