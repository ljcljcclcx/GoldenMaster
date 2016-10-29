package com.clcx.goldenmaster.ui.market;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.adapters.MarketAdapter;
import com.clcx.goldenmaster.basement.util.OrderClcxUtil;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.beans.MarketItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/22.
 */
public class MarketPresenter extends MarketContract.Presenter {
    @Override
    void buyItem(int location) {

        if (mView.getAdapter() != null) {
            MarketItem item = (MarketItem) mView.getAdapter().getItem(location);
            if (Config.getAlchemista().getMoney() >= item.getPrice()) {
                mView.getAdapter().removeItem(location);
                mView.getAdapter().notifyItemRemoved(location);
                MarketModel.removeItemFromMarket(location);
                AlchemistaAction.builder().putItemToBag(item.getItem());
                AlchemistaAction.builder().getMoney(-1 * (item.getPrice()));
            } else {
                ToastClcxUtil.getInstance().showToast("您的钱不够！");
            }
        }
    }

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
        List<MarketItem> marketItems = new ArrayList<>();
        for (int a = 0; a < adapter.getItemCount(); a++) {
            marketItems.add((MarketItem) adapter.getItem(a));
        }
        OrderClcxUtil.getInstance().insertOrder(marketItems, 1);
        adapter.setItems(marketItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    void orderByYanse(MarketAdapter adapter) {
        List<MarketItem> marketItems = new ArrayList<>();
        for (int a = 0; a < adapter.getItemCount(); a++) {
            marketItems.add((MarketItem) adapter.getItem(a));
        }
        OrderClcxUtil.getInstance().insertOrder(marketItems, 0);
        adapter.setItems(marketItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    void orderByJiazhi(MarketAdapter adapter) {
        List<MarketItem> marketItems = new ArrayList<>();
        for (int a = 0; a < adapter.getItemCount(); a++) {
            marketItems.add((MarketItem) adapter.getItem(a));
        }
        OrderClcxUtil.getInstance().insertOrder(marketItems, 5);
        adapter.setItems(marketItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    void orderByDuxing(MarketAdapter adapter) {
        List<MarketItem> marketItems = new ArrayList<>();
        for (int a = 0; a < adapter.getItemCount(); a++) {
            marketItems.add((MarketItem) adapter.getItem(a));
        }
        OrderClcxUtil.getInstance().insertOrder(marketItems, 3);
        adapter.setItems(marketItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    void orderByCiji(MarketAdapter adapter) {
        List<MarketItem> marketItems = new ArrayList<>();
        for (int a = 0; a < adapter.getItemCount(); a++) {
            marketItems.add((MarketItem) adapter.getItem(a));
        }
        OrderClcxUtil.getInstance().insertOrder(marketItems, 2);
        adapter.setItems(marketItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    void orderByWaiguan(MarketAdapter adapter) {
        List<MarketItem> marketItems = new ArrayList<>();
        for (int a = 0; a < adapter.getItemCount(); a++) {
            marketItems.add((MarketItem) adapter.getItem(a));
        }
        OrderClcxUtil.getInstance().insertOrder(marketItems, 4);
        adapter.setItems(marketItems);
        adapter.notifyDataSetChanged();
    }
}
