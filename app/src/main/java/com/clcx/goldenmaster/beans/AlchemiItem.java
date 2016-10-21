package com.clcx.goldenmaster.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AlchemiItem implements Serializable {
    protected String name;
    protected String[] properties;
    protected int[] propertiesPoint;
    protected int price;
    protected String recipe;
    protected String type;

    public AlchemiItem(String name,  int[] propertiesPoint) {
        this.name = name;
        this.properties = new String[6];
        this.properties[0]="颜色";
        this.properties[1]="气味";
        this.properties[2]="价值";
        this.properties[3]="毒性";
        this.properties[4]="外观";
        this.properties[5]="刺激度";
        this.propertiesPoint = propertiesPoint;


    }
}
