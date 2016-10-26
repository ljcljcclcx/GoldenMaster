package com.clcx.goldenmaster.ui.alchemist;

import com.clcx.goldenmaster.adapters.AlchemistAdapter;
import com.clcx.goldenmaster.basement.BaseModel;
import com.clcx.goldenmaster.basement.BasePresenter;
import com.clcx.goldenmaster.basement.BaseView;
import com.clcx.goldenmaster.beans.AlchemiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/25.
 */

public interface AlchemistContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseView {

        //设置熔炼按钮可点击
        void CanAlchemist();

        void CanNotAlchemist();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        protected List<AlchemiItem> itemsInBottle;

        @Override
        public void onStart() {
            itemsInBottle = new ArrayList<>();
        }

        abstract void addItemToBottle(int location, AlchemistAdapter bag, AlchemistAdapter bottle);//炼金时，将材料加入瓶中

        abstract void removeFromBottle(int location, AlchemistAdapter bag, AlchemistAdapter bottle);//炼金时，将材料从瓶中取出

        abstract void alchemist(AlchemistAdapter bag, AlchemistAdapter bottle);//进行炼金

    }
}
