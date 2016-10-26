package com.clcx.goldenmaster.ui.house;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.adapters.AlchemistAdapter;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.adapters.MarketAdapter;
import com.clcx.goldenmaster.basement.BaseModel;
import com.clcx.goldenmaster.basement.BasePresenter;
import com.clcx.goldenmaster.basement.BaseView;
import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.beans.AlchemiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/25.
 */

public interface HouseContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseView {
        void setTextviewState();

        void setRecyclerViewAdapter();


        //以下6个turnTo是切换内容页

        void turnToNews();

        void turnToRest();

        void turnToCollection();
    }

    abstract class Presenter extends BasePresenter<Model, View> {


        @Override
        public void onStart() {
        }

        abstract void testItems();

        abstract void initUI();


        //以下6个turnTo是切换内容页
        abstract void turnToNews();

        abstract void turnToRest();

        abstract void turnToCollection();


    }
}
