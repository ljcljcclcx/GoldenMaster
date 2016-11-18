package com.clcx.goldenmaster.ui.creator;

import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.BaseModel;
import com.clcx.goldenmaster.basement.BasePresenter;
import com.clcx.goldenmaster.basement.BaseView;

/**
 * Created by ljc123 on 2016/10/25.
 */

public interface CreatorContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseView {
        void forbidEditText();

        void releaseEditText();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        @Override
        public void onStart() {

        }

        abstract void deleteAlchemista();
    }
}
