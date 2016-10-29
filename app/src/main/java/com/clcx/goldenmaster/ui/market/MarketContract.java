package com.clcx.goldenmaster.ui.market;

import com.clcx.goldenmaster.adapters.MarketAdapter;
import com.clcx.goldenmaster.basement.BaseModel;
import com.clcx.goldenmaster.basement.BasePresenter;
import com.clcx.goldenmaster.basement.BaseView;
import com.clcx.goldenmaster.beans.MarketItem;

import java.util.List;

/**
 * Created by ljc123 on 2016/10/25.
 */

public interface MarketContract {
    interface Model extends BaseModel {

    }

    interface View extends BaseView {
        MarketAdapter getAdapter();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        @Override
        public void onStart() {

        }

        //市场购买
        abstract void buyItem(int location);

        //以下是市场排序
        abstract void orderByPrice(MarketAdapter adapter);

        abstract void orderByQiwei(MarketAdapter adapter);

        abstract void orderByYanse(MarketAdapter adapter);

        abstract void orderByJiazhi(MarketAdapter adapter);

        abstract void orderByDuxing(MarketAdapter adapter);

        abstract void orderByCiji(MarketAdapter adapter);

        abstract void orderByWaiguan(MarketAdapter adapter);

    }
}
