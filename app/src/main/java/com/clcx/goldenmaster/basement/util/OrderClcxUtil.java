package com.clcx.goldenmaster.basement.util;

import com.clcx.goldenmaster.beans.MarketItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OrderClcxUtil {
    private Random random;

    private OrderClcxUtil() {
    }

    private static OrderClcxUtil instance = null;

    public static OrderClcxUtil getInstance() {
        if (instance == null) {
            synchronized (OrderClcxUtil.class) {
                if (instance == null) {
                    instance = new OrderClcxUtil();
                }

            }
        }
        return instance;
    }

    private static int COOUNT = 0;

    /**
     * 归并排序
     *
     * @param start
     * @param end
     * @param lists
     */
    public void mergeSort(int start, int end, List<MarketItem> lists) {
        int mid = (start + end) / 2;
        if (start < end) {
            mergeSort(start, mid, lists);
            mergeSort(mid + 1, end, lists);
            merge(start, end, mid, lists);
        }
    }

    /**
     * 归并排序
     *
     * @param start
     * @param end
     * @param lists
     */
    private void merge(int start, int end, int mid, List<MarketItem> lists) {
        List<MarketItem> tmpList = new ArrayList<>();
        for (int a = start; a <= end; a++) {
            tmpList.add(lists.get(a));
        }
        int i, j, k;
        for (i = start, j = mid + 1, k = 0; i <= mid && j <= end; ) {
            if (lists.get(i).getPrice() < lists.get(j).getPrice()) {
                tmpList.set(k++, lists.get(i));
                i++;
            } else {
                tmpList.set(k++, lists.get(j));
                j++;
            }
        }
        while (i <= mid) {
            tmpList.set(k++, lists.get(i));
            i++;
        }
        while (j <= end) {
            tmpList.set(k++, lists.get(j));
            j++;
        }
        for (int a = 0; a < tmpList.size(); a++) {
            lists.set(start + a, tmpList.get(a));
        }
//        LogCLCXUtils.e(lists + "a" + COOUNT);
//        COOUNT++;
    }

    /**
     * 插入排序
     * 首先把第一个作为标杆，
     * 当到了第二个的时候，和第一个比，比他大，就和和第一个交换位置，然后第二个=第一个
     * 当到了第三个的时候，tmp=第三个，和第二个比，如果第二个比他大，那么第三个=第二个，tmp和第一个比，如果
     *
     * @param lists
     */
    public void insertOrder(List<Integer> lists) {
        for (int a = 1; a < lists.size(); a++) {
            int ta = a;
            for (int b = a - 1; b >= 0; b--) {
                int tmp = lists.get(ta);
                if (tmp > lists.get(b)) {
                    Collections.swap(lists, ta, b);
                    ta = b;
                }
            }
        }
//        LogCLCXUtils.e(lists + "a" + COOUNT);
//        COOUNT++;
    }

    /**
     * @param lists
     * @param type  this.properties[0] = "颜色";
     *              this.properties[1] = "气味";
     *              this.properties[2] = "刺激度";
     *              this.properties[3] = "毒性";
     *              this.properties[4] = "外观";
     *              this.properties[5] = "价值";
     */
    public void insertOrder(List<MarketItem> lists, int type) {
        for (int a = 1; a < lists.size(); a++) {
            int ta = a;
            for (int b = a - 1; b >= 0; b--) {
                MarketItem tmp = lists.get(ta);
                if (tmp.getItem().getPropertiesPoint()[type] > lists.get(b).getItem().getPropertiesPoint()[type]) {
                    Collections.swap(lists, ta, b);
                    ta = b;
                }
            }
        }
    }
}
