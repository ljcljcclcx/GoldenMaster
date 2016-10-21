package com.clcx.goldenmaster.factories;

import com.clcx.goldenmaster.beans.AlItemMaterial;
import com.clcx.goldenmaster.beans.AlchemiItem;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class MaterialFactory {
//    this.properties[0]="颜色";
//    this.properties[1]="气味";
//    this.properties[2]="价值";
//    this.properties[3]="毒性";
//    this.properties[4]="外观";
//    this.properties[5]="刺激度";
    public static final AlchemiItem langyancao=new AlItemMaterial("狼眼草"
           ,new int[]{3,1,2,6,5,4});
    public static final AlchemiItem lansejiachong=new AlItemMaterial("蓝色甲虫"
           ,new int[]{5,2,1,1,4,2});
    public static final AlchemiItem maoer=new AlItemMaterial("猫耳"
           ,new int[]{1,1,3,2,4,1});
}
