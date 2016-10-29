package com.clcx.goldenmaster.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ljc123 on 2016/10/26.
 */

public class MarketItem implements Serializable {
    //上架物品
    private AlchemiItem item;
    private int price;
    private String seller;
    /**
     *上架日期：
     * 价值<估价：1分钟内被出售
     * 价值=估价：30分钟后被出售
     * 价值>估价but<2*估价:以小时计算，每增加10%，出售时间增加1小时
     */
    private long upTime;

    public long getUpTime() {
        return upTime;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }


    public MarketItem(AlchemiItem item, int price, String seller) {
        this.item = item;
        this.price = price;
        this.seller = seller;
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        upTime = Long.parseLong(format.format(new Date()));
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
