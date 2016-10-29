package com.clcx.goldenmaster.beans;

import java.io.Serializable;

/**
 * Created by ljc123 on 2016/10/27.
 */

public class MessageBean implements Serializable {
    private String content;
    private String date;
    private int price;//如果是商品被出售，那么应该获得钱

    public MessageBean(String content, String date, int price) {
        this.content = content;
        this.date = date;
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }
}
