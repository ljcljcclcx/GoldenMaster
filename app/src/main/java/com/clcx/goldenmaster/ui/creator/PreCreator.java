package com.clcx.goldenmaster.ui.creator;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.basement.MyApplication;

/**
 * Created by ljc123 on 2016/10/22.
 */
public class PreCreator extends CreatorContract.Presenter {
    @Override
    void deleteAlchemista() {
        Config.deleteAlchemista(MyApplication.getContext());
        mView.releaseEditText();
    }
}
