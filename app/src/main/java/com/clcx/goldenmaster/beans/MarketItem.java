package com.clcx.goldenmaster.beans;

import java.io.Serializable;

/**
 * Created by ljc123 on 2016/10/26.
 */

public class MarketItem implements Serializable{
    //上架物品
    private AlchemiItem item;
    private int price;

    public MarketItem(AlchemiItem item, int price) {
        this.item = item;
        this.price = price;
    }

    public AlchemiItem getItem() {
        return item;
    }

    public void setItem(AlchemiItem item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
