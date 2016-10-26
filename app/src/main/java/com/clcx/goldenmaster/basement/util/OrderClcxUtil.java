package com.clcx.goldenmaster.basement.util;

import com.clcx.goldenmaster.beans.MarketItem;

import java.util.ArrayList;
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

    public void mergeSort(int start, int end, List<MarketItem> lists) {
        int mid = (start + end) / 2;
        if (start < end) {
            mergeSort(start, mid, lists);
            mergeSort(mid + 1, end, lists);
            merge(start, end, mid, lists);
        }
    }

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


}
