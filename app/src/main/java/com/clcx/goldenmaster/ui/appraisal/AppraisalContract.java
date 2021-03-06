package com.clcx.goldenmaster.ui.appraisal;

import com.clcx.goldenmaster.basement.BaseModel;
import com.clcx.goldenmaster.basement.BasePresenter;
import com.clcx.goldenmaster.basement.BaseView;

/**
 * Created by ljc123 on 2016/10/25.
 */

public interface AppraisalContract {
    interface Model extends BaseModel {

    }

    interface View extends BaseView {
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        @Override
        public void onStart() {

        }

    }
}
