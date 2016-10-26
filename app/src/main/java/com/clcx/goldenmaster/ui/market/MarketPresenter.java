package com.clcx.goldenmaster.ui.market;

import com.clcx.goldenmaster.adapters.MarketAdapter;
import com.clcx.goldenmaster.basement.util.OrderClcxUtil;
import com.clcx.goldenmaster.beans.MarketItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/22.
 */
public class MarketPresenter extends MarketContract.Presenter {
    @Override
    void orderByPrice(MarketAdapter adapter) {
        List<MarketItem> marketItems = new ArrayList<>();
        for (int a = 0; a < adapter.getItemCount(); a++) {
            marketItems.add((MarketItem) adapter.getItem(a));
        }
        OrderClcxUtil.getInstance().mergeSort(0, adapter.getItemCount() - 1, marketItems);
        adapter.setItems(marketItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    void orderByQiwei(MarketAdapter adapter) {

    }

    @Override
    void orderByYanse(MarketAdapter adapter) {

    }

    @Override
    void orderByJiazhi(MarketAdapter adapter) {

    }

    @Override
    void orderByDuxing(MarketAdapter adapter) {

    }

    @Override
    void orderByCiji(MarketAdapter adapter) {

    }

    @Override
    void orderByWaiguan(MarketAdapter adapter) {

    }
}
