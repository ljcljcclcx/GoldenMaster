package com.clcx.goldenmaster.ui.message;

import android.os.Message;

import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.adapters.MessageAdapter;
import com.clcx.goldenmaster.basement.BaseModel;
import com.clcx.goldenmaster.basement.BasePresenter;
import com.clcx.goldenmaster.basement.BaseView;
import com.clcx.goldenmaster.beans.MessageBean;

import java.util.List;

/**
 * Created by ljc123 on 2016/10/25.
 */

public interface MessageContract {
    interface Model extends BaseModel {


        void removeMessage(int location);
    }

    interface View extends BaseView {
        MessageAdapter getAdapter();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        @Override
        public void onStart() {

        }

        abstract int clickMessage(int location, boolean ifToast);//点击消息
    }
}
