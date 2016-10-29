package com.clcx.goldenmaster.ui.bag;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.ui.creator.CreatorContract;

/**
 * Created by ljc123 on 2016/10/22.
 */
public class BagPresenter extends BagContract.Presenter {

    @Override
    void sellItem(int location, HouseBagAdapter adapter) {
        AlchemistaAction.builder().lostItem(location);
        adapter.removeItem(location);
        adapter.notifyDataSetChanged();
        ToastClcxUtil.getInstance().showToast("上架成功！");
    }
}
