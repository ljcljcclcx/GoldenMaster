package com.clcx.goldenmaster.ui.Rest;

import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.basement.BaseModel;
import com.clcx.goldenmaster.basement.BasePresenter;
import com.clcx.goldenmaster.basement.BaseView;

/**
 * Created by ljc123 on 2016/10/25.
 */

public interface RestContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseView {
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        @Override
        public void onStart() {

        }

        abstract void rest(int energeRecovery,int price);
    }
}
