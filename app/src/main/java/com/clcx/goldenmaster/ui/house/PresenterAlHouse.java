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
    void turnToNews() {

    }

    @Override
    void turnToRest() {

    }

    @Override
    void turnToCollection() {

    }



    @Override
    void testItems() {
        AlchemistaAction.builder().putItemToBag(MaterialFactory.createRandomItem());
    }

    @Override
    void initUI() {
        mView.setTextviewState();
        mView.setRecyclerViewAdapter();
        if (Config.deltaDay() > 1) {
            ToastClcxUtil.getInstance().showToast("新的一天开始了！");
            Config.createTodayMarket();
        }
    }


}
