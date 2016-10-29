package com.clcx.goldenmaster.ui.appraisal;

import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.beans.AlItemProduct;

import java.util.Collections;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/25.
 */

public class AppraisalModel implements AppraisalContract.Model {

    public static final String PRES[] = {"初级", "炼金师", "创造者", "大师"};
    public static final String MID1[] = {"多色性", "浓郁", "激发", "毒性", "观赏性", "财富"};
    public static final String MID2[] = {"缤纷", "栀子花", "毁灭", "剧毒", "旅行", "神秘宝藏"};

    /**
     * @param product
     * @param judgeLevel 判定等级，0炼金师鉴定，1大师鉴定
     * @return
     */
    public static AppraisalType appraisalProduct(AlItemProduct product, int judgeLevel) {

        int[] orders = insertOrder(product.getPropertiesPoint());
//        LogCLCXUtils.e("product:" + product.getName());
//        for (int a = 0; a < orders.length; a++) {
//            LogCLCXUtils.e(orders[a] + ":orders:" + product.getPropertiesPoint()[orders[a]]);
//        }

        StringBuffer stb = new StringBuffer();
        float score = 0.0f;
        for (int a = 0; a < product.getPropertiesPoint().length; a++) {
            score += product.getPropertiesPoint()[a];
        }
//        LogCLCXUtils.e("score=" + score);
        float scoreRatio = 1.0f;
        switch (judgeLevel) {
            case 0:
                if (score < 20.0f) {
                    stb.append(PRES[0]);
                    scoreRatio = 1.5f;
                } else {
                    stb.append(PRES[1]);
                    scoreRatio = 2.5f;
                }
                break;
            case 1:
                if (score < 20.0f) {
                    stb.append(PRES[0]);
                    scoreRatio = 1.5f;
                } else if (score >= 20.0f && score < 40.0f) {
                    stb.append(PRES[1]);
                    scoreRatio = 2.5f;
                } else if (score >= 40.0f && score < 65.0f) {
                    stb.append(PRES[2]);
                    scoreRatio = 4.0f;
                } else {
                    stb.append(PRES[3]);
                    scoreRatio = 6.0f;
                }
                break;
            default:
                break;
        }

        if (score >= 40) {
            stb.append(MID2[orders[0]]);
        } else {
            stb.append(MID1[orders[0]]);
        }
        AppraisalType type = new AppraisalType(stb.toString(), scoreRatio);
        return type;
    }

    private static int[] insertOrder(float[] lists) {
        int[] orders = {0, 1, 2, 3, 4, 5};
        float[] counts = new float[lists.length];
        for (int b = 0; b < lists.length; b++) {
            counts[b] = lists[b];
        }
        for (int a = 1; a < counts.length; a++) {
            int ta = a;
            for (int b = a - 1; b >= 0; b--) {
                float tmp = counts[ta];
                int order = orders[ta];
                if (tmp > counts[b]) {
                    counts[ta] = counts[b];
                    counts[b] = tmp;
                    orders[ta] = orders[b];
                    orders[b] = order;
                    ta = b;
                }
            }
        }
        return orders;
    }
}
