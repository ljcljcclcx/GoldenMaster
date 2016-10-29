package com.clcx.goldenmaster.ui.Rest;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.Alchemista;
import com.clcx.goldenmaster.beans.AlchemistaAction;

/**
 * Created by ljc123 on 2016/10/22.
 */
public class RestPresenter extends RestContract.Presenter {

    @Override
    void rest(int energeRecovery, int price) {
        if (Config.getAlchemista().getMoney() >= price) {
            AlchemistaAction.builder().getMoney(-1 * (price));
            AlchemistaAction.builder().reduceEnerge(-1 * (energeRecovery));
            ToastClcxUtil.getInstance().showToast("好好休息了一下，体力大有提升！");
        } else {
            ToastClcxUtil.getInstance().showToast("你没那么多钱！");
        }
    }
}
