package com.clcx.goldenmaster.ui.house;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.adapters.AlchemistAdapter;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.adapters.MarketAdapter;
import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.OrderClcxUtil;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.beans.Alchemista;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.factories.MaterialFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/22.
 */
public class PresenterAlHouse extends HouseContract.Presenter {

    @Override
    void testItems() {
        if (Config.getAlchemista().getEnerge() > Config.COLLECTION_ENERGE_REDUCE) {
            AlchemiItem item = MaterialFactory.createRandomItem();
            AlchemistaAction.builder().putItemToBag(item);
            ToastClcxUtil.getInstance().showToast("找到了" + item.getName());
            AlchemistaAction.builder().reduceEnerge(Config.COLLECTION_ENERGE_REDUCE);
            //搜索+1经验
            AlchemistaAction.builder().addExp(1);
            initUI();
        } else {
            ToastClcxUtil.getInstance().showToast("你太累了，无法探索了！");
        }

    }

    @Override
    void initUI() {
        mView.setTextviewState();
        if (Config.deltaDay() >= 1) {
            Config.newDay();
        }
    }


}
