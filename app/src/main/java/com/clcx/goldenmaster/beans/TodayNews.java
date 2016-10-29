package com.clcx.goldenmaster.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/27.
 */

public class TodayNews implements Serializable{
    private String content;
    private List<Integer> priceUpLarge;
    private List<Integer> priceUpSmall;

    public TodayNews(String content, int[] priceUpLarge, int[] priceUpSmall) {
        this.priceUpLarge = new ArrayList<>();
        this.priceUpSmall = new ArrayList<>();
        if (priceUpLarge.length <= 0 && priceUpSmall.length <= 0) {
            this.content = "今天并没有什么新闻。";
            return;
        }
        StringBuffer stb = new StringBuffer();
        if (priceUpLarge.length + priceUpSmall.length < 2) {
            stb.append("根据炼金新闻报道，今日");
        } else {
            stb.append("特大新闻！今日");
        }

        if (priceUpLarge.length > 0) {
            for (Integer i : priceUpLarge) {
                stb.append("[" + AlchemiItem.ITEM_PROPERTIES[i] + "]");
                this.priceUpLarge.add(i);
            }
            stb.append("属性的材料或成品有大幅度的涨价趋势");
        } else {

        }
        if (priceUpSmall.length > 0) {
            stb.append("，");
            for (Integer i : priceUpSmall) {
                stb.append("[" + AlchemiItem.ITEM_PROPERTIES[i] + "]");
                this.priceUpSmall.add(i);
            }
            stb.append("属性的材料或成品的价格有所增涨。");
        } else {
            stb.append("。");
        }
        this.content = stb.toString();
    }

    public String getContent() {
        return content;
    }

    public List<Integer> getPriceUpLarge() {
        return priceUpLarge;
    }

    public List<Integer> getPriceUpSmall() {
        return priceUpSmall;
    }
}
