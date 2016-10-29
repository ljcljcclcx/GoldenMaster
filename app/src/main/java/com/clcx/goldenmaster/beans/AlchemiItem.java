package com.clcx.goldenmaster.beans;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.basement.util.MathClcxUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AlchemiItem implements Serializable {
    public static final int MAX_PROPERTY_COUNT = 6;
    protected String prename;
    protected String lastName;
    protected String[] properties;
    protected float[] propertiesPoint;
    protected float price;
    protected String recipe;
    protected String type;
    protected int[] gainStateId;
    protected int[] restrainStateId;

    private boolean isAppraisal;//是否已鉴定

    public boolean isAppraisal() {
        return isAppraisal;
    }

    public void setAppraisal(boolean appraisal) {
        isAppraisal = appraisal;
    }

    public static final int[] CAPACITY_COLORS = {
            Color.rgb(255, 0, 0),
            Color.rgb(255, 180, 0),
            Color.rgb(255, 0, 255),
            Color.rgb(0, 255, 0),
            Color.rgb(155, 155, 155),
            Color.rgb(255, 255, 0)
    };

    public static final String ITEM_PROPERTIES[] = {"颜色", "气味", "刺激度", "毒性", "外观", "价值"};

    public AlchemiItem(String prename, String lastName, float[] propertiesPoint, int[] gainStateId, int[]
            restrainStateId) {
        this.prename = prename;
        this.lastName = lastName;
        this.propertiesPoint = propertiesPoint;
        this.gainStateId = gainStateId;
        this.restrainStateId = restrainStateId;
        this.properties = new String[MAX_PROPERTY_COUNT];
        for (int a = 0; a < MAX_PROPERTY_COUNT; a++) {
            this.properties[a] = ITEM_PROPERTIES[a];
        }
        isAppraisal = false;
    }

    public String getName() {
        return this.prename + this.lastName;
    }

    public String getIntro() {
        StringBuffer stb = new StringBuffer();
        stb.append("【" + this.prename + this.lastName + "】\n");
        stb.append("增益:");
        if (gainStateId.length <= 0) {
            stb.append("无");
        } else {
            for (int a = 0; a < gainStateId.length; a++) {
                stb.append(properties[gainStateId[a]] + ",");
            }
            stb.deleteCharAt(stb.length() - 1);//删除最后一个逗号
        }
        stb.append("\n");
        stb.append("减益:");
        if (restrainStateId.length <= 0) {
            stb.append("无");
        } else {
            for (int a = 0; a < restrainStateId.length; a++) {
                stb.append(properties[restrainStateId[a]] + ",");
            }
            stb.deleteCharAt(stb.length() - 1);//删除最后一个逗号
        }
        stb.append("\n市场估价:$" + MathClcxUtil.getInstance().moneyFormat(getPrice()));
        return stb.toString();
    }

    /**
     * 计算受新闻影响的最终价格
     *
     * @param propertyPoint 影响到的属性值，参见上面
     */
    protected float countFinalPrice(float propertyPoint, int i) {
        float gainResRatio = 1.0f;
        for (Integer targetI : Config.getTodayNews().getPriceUpLarge()) {
            if (targetI == i) {
                gainResRatio += 2.5f;
            }
        }
        for (Integer targetI : Config.getTodayNews().getPriceUpSmall()) {
            if (targetI == i) {
                gainResRatio += 1.3f;
            }
        }
        //最后计算物品增益减溢带来的价格浮动
        gainResRatio += (float) getGainStateId().length * 0.2f;
        gainResRatio -= (float) getGainStateId().length * 0.2f;
        propertyPoint *= gainResRatio;
        return propertyPoint;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }

    public float[] getPropertiesPoint() {
        return propertiesPoint;
    }

    public void setPropertiesPoint(float[] propertiesPoint) {
        this.propertiesPoint = propertiesPoint;
    }

    public int getPrice() {
        return (int) price;
    }

    public float getPriceF() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int[] getRestrainStateId() {
        return restrainStateId;
    }

    public void setRestrainStateId(int[] restrainStateId) {
        this.restrainStateId = restrainStateId;
    }

    public int[] getGainStateId() {
        return gainStateId;
    }

    public void setGainStateId(int[] gainStateId) {
        this.gainStateId = gainStateId;
    }
}
