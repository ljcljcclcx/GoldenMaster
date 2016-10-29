package com.clcx.goldenmaster.factories;

import com.clcx.goldenmaster.basement.util.MathClcxUtil;
import com.clcx.goldenmaster.beans.AlItemMaterial;
import com.clcx.goldenmaster.beans.AlItemProduct;
import com.clcx.goldenmaster.ui.appraisal.AppraisalModel;

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
        itemFactories.add(new AlItemMaterial("凡派拉", "灰烬",
                new float[]{3.2f, 1.0f, 2.0f, 1.0f, 4.5f, 1.0f},
                new int[]{yanse, waiguan},
                new int[]{ciji, qiwei}));
        itemFactories.add(new AlItemMaterial("黑豹", "牙"
                , new float[]{1.1f, 4.1f, 3.2f, 1.0f, 1.0f, 1.0f}
                , new int[]{5}
                , new int[]{0}));
        itemFactories.add(new AlItemMaterial("潘吉拉", "红山花"
                , new float[]{1.0f, 1.0f, 1.2f, 1.0f, 3.1f, 4.9f}
                , new int[]{5}
                , new int[]{2}));

        itemFactories.add(new AlItemMaterial("珍珠", "鱼目"
                , new float[]{1.5f, 3.1f, 4.2f, 2.5f, 2.1f, 1.0f}
                , new int[]{4}
                , new int[]{1}));
        itemFactories.add(new AlItemMaterial("蜻蜓", "薄翼"
                , new float[]{2.5f, 1.1f, 1.2f, 1.5f, 3.1f, 2.9f}
                , new int[]{4}
                , new int[]{5}));

        itemFactories.add(new AlItemMaterial("伞形", "比目蛙"
                , new float[]{1.1f, 1.1f, 2.2f, 3.5f, 4.2f, 3.5f}
                , new int[]{0}
                , new int[]{1}));
        itemFactories.add(new AlItemMaterial("蓝色", "甲虫"
                , new float[]{1.3f, 1.7f, 1.3f, 1.8f, 5, 1.1f}
                , new int[]{0}
                , new int[]{3}));

        itemFactories.add(new AlItemMaterial("死亡", "樱花"
                , new float[]{4.1f, 1.3f, 2, 1.1f, 1.1f, 1.0f}
                , new int[]{1}
                , new int[]{2}));
        itemFactories.add(new AlItemMaterial("狼", "眼草"
                , new float[]{1.3f, 1.5f, 1.1f, 4.5f, 2.3f, 2.2f}
                , new int[]{1}
                , new int[]{4}));

        itemFactories.add(new AlItemMaterial("猫", "耳"
                , new float[]{2, 3, 1.5f, 1.2f, 1.1f, 1.7f}
                , new int[]{2}
                , new int[]{3}));
        itemFactories.add(new AlItemMaterial("玛特纳", "腐生植物"
                , new float[]{1.0f, 4.1f, 1.2f, 1.5f, 1.8f, 2}
                , new int[]{2}
                , new int[]{5}));

        itemFactories.add(new AlItemMaterial("溪流", "芦苇"
                , new float[]{1.0f, 1.0f, 2.5f, 1.0f, 1.0f, 1.0f}
                , new int[]{3}
                , new int[]{0}));
        itemFactories.add(new AlItemMaterial("幽光", "蘑菇"
                , new float[]{1.0f, 1.5f, 2, 2.5f, 3, 1.0f}
                , new int[]{3}
                , new int[]{4}));


    }

    public static AlItemMaterial createRandomItem() {
        return itemFactories.get(MathClcxUtil.getInstance().randomInt(itemFactories.size())).cloneItem();
    }

    public static AlItemProduct createRandomProduct() {
        AlItemProduct product = new AlItemProduct("大师", "试剂", new float[]{
                MathClcxUtil.getInstance().randomFloat(5.0f, 15.0f),
                MathClcxUtil.getInstance().randomFloat(5.0f, 15.0f),
                MathClcxUtil.getInstance().randomFloat(5.0f, 15.0f),
                MathClcxUtil.getInstance().randomFloat(5.0f, 15.0f),
                MathClcxUtil.getInstance().randomFloat(5.0f, 15.0f),
                MathClcxUtil.getInstance().randomFloat(5.0f, 15.0f)
        }
                , new int[]{qiwei, yanse, waiguan, jiazhi, duxing, ciji}
                , new int[]{}
                , 20000);
        product.reName(AppraisalModel.appraisalProduct(product, 1));
        return product;
    }
}
