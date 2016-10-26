package com.clcx.goldenmaster.factories;

import com.clcx.goldenmaster.basement.util.MathClcxUtil;
import com.clcx.goldenmaster.beans.AlItemMaterial;
import com.clcx.goldenmaster.beans.AlchemiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class MaterialFactory {
    public static final List<AlItemMaterial> itemFactories = new ArrayList<>();
    private static final int yanse = 0;
    private static final int qiwei = 1;
    private static final int jiazhi = 2;
    private static final int duxing = 3;
    private static final int waiguan = 4;
    private static final int ciji = 5;

    static {
        itemFactories.add(new AlItemMaterial("溪流", "芦苇"
                , new float[]{1.0f, 1.0f, 3.5f, 1.0f, 1.0f, 1.0f}
                , new int[]{}
                , new int[]{}));
        itemFactories.add(new AlItemMaterial("狼", "眼草"
                , new float[]{1.0f, 1.0f, 1.0f, 4.5f, 2.3f, 2.2f}
                , new int[]{qiwei}
                , new int[]{duxing, ciji}));
        itemFactories.add(new AlItemMaterial("猫", "耳"
                , new float[]{2, 3, 1.0f, 1.0f, 1.0f, 1.0f}
                , new int[]{waiguan, duxing}
                , new int[]{jiazhi, qiwei, yanse}));
        itemFactories.add(new AlItemMaterial("蓝色", "甲虫"
                , new float[]{1.0f, 1.0f, 1.0f, 1.0f, 5, 1.0f}
                , new int[]{yanse}
                , new int[]{duxing, waiguan}));
        itemFactories.add(new AlItemMaterial("死之", "花"
                , new float[]{5, 1.0f, 2, 1.0f, 3, 3}
                , new int[]{duxing, yanse}
                , new int[]{jiazhi, waiguan, duxing}));
        itemFactories.add(new AlItemMaterial("幽光", "蘑菇"
                , new float[]{1.0f, 1.0f, 2, 1.0f, 3, 1.0f}
                , new int[]{}
                , new int[]{waiguan, yanse}));
        itemFactories.add(new AlItemMaterial("玛特纳", "腐生植物"
                , new float[]{1.0f, 5, 1.0f, 1.0f, 1.0f, 2}
                , new int[]{yanse}
                , new int[]{duxing, ciji}));

    }

    public static AlItemMaterial createRandomItem() {
        return itemFactories.get(MathClcxUtil.getInstance().randomInt(itemFactories.size())).cloneItem();
    }
}
