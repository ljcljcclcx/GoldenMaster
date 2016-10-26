package com.clcx.goldenmaster.ui.alchemist;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.adapters.AlchemistAdapter;
import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.beans.Alchemista;
import com.clcx.goldenmaster.beans.AlchemistaAction;

/**
 * Created by ljc123 on 2016/10/22.
 */
public class AlchemistPresenter extends AlchemistContract.Presenter {
    @Override
    void addItemToBottle(int location, AlchemistAdapter bag, AlchemistAdapter bottle) {
        Alchemista aa = Config.getAlchemista(MyApplication.getContext());
        AlchemiItem item = aa.getBag().get(location);
        //判断瓶中材料不能一样
        for (int a = 0; a < itemsInBottle.size(); a++) {
            if (itemsInBottle.get(a).getName().equals(item.getName())) {
                ToastClcxUtil.getInstance().showToast("不能熔炼相同材料！");
                return;
            }
        }
        if (itemsInBottle.size() >= 3 + (aa.getExp() / 3000)) {
            ToastClcxUtil.getInstance().showToast("您的等级太低，无法加入更多材料");
            return;
        }

        AlchemistaAction.builder().lostItem(location);
        itemsInBottle.add(item);
        bag.setItems(Config.getAlchemista(MyApplication.getContext()).getBag());
        bag.notifyDataSetChanged();
        bottle.setItems(itemsInBottle);
        bottle.notifyDataSetChanged();
        if (itemsInBottle.size() >= 3) {
            mView.CanAlchemist();
        }
    }

    @Override
    void removeFromBottle(int location, AlchemistAdapter bag, AlchemistAdapter bottle) {

        AlchemiItem item = itemsInBottle.get(location);
        AlchemistaAction.builder().putItemToBag(item);
        itemsInBottle.remove(item);
        bag.setItems(Config.getAlchemista(MyApplication.getContext()).getBag());
        bag.notifyDataSetChanged();
        bottle.setItems(itemsInBottle);
        bottle.notifyDataSetChanged();
        if (itemsInBottle.size() < 3) {
            mView.CanNotAlchemist();
        }
    }

    @Override
    void alchemist(AlchemistAdapter bag, AlchemistAdapter bottle) {
        AlchemistaAction.builder().alchemist(itemsInBottle);
        bottle.clear();
        itemsInBottle.clear();
        bottle.notifyDataSetChanged();
        bag.setItems(Config.getAlchemista(MyApplication.getContext()).getBag());
        bag.notifyDataSetChanged();
    }
}
