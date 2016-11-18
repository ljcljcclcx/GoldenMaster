package com.clcx.goldenmaster.ui.alchemist;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.adapters.AlchemistAdapter;
import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.beans.Alchemista;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.factories.MissionFactory;
import com.clcx.goldenmaster.ui.mission.MissionModel;

/**
 * Created by ljc123 on 2016/10/22.
 */
public class AlchemistPresenter extends AlchemistContract.Presenter {
    @Override
    void addItemToBottle(int location, AlchemistAdapter bag, AlchemistAdapter bottle) {
        Alchemista aa = Config.getAlchemista();
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
        if (item.isLocked()) {
            ToastClcxUtil.getInstance().showToast("该物品已经被锁定！");
            return;
        }

        AlchemistaAction.builder().lostItem(location);
        itemsInBottle.add(item);
        bag.removeItem(location);
        bag.notifyItemRemoved(location);
        bottle.addItem(0, item);
        bottle.notifyItemInserted(0);
        if (itemsInBottle.size() >= 2) {
            mView.CanAlchemist();
        }
    }

    @Override
    void removeFromBottle(int location, AlchemistAdapter bag, AlchemistAdapter bottle) {

        AlchemiItem item = itemsInBottle.get(location);
        AlchemistaAction.builder().putItemToBag(item);
        itemsInBottle.remove(item);
        bag.setItems(Config.getAlchemista().getBag());
        bag.notifyDataSetChanged();
        bottle.setItems(itemsInBottle);
        bottle.notifyDataSetChanged();
        if (itemsInBottle.size() <= 2) {
            mView.CanNotAlchemist();
        }
    }

    @Override
    void alchemist(AlchemistAdapter bag, AlchemistAdapter bottle) {
        if (Config.getAlchemista().getEnerge() > Config.ALCHAMIST_ENERGE_REDUCE) {
            AlchemistaAction.builder().alchemist(itemsInBottle);
            bottle.clear();
            itemsInBottle.clear();
            bottle.notifyDataSetChanged();
            bag.setItems(Config.getAlchemista().getBag());
            bag.notifyDataSetChanged();
            //炼金+8经验
            AlchemistaAction.builder().addExp(8);
            AlchemistaAction.builder().reduceEnerge(Config.ALCHAMIST_ENERGE_REDUCE);
            //计算任务完成
            String str = MissionModel.getThisIdPosition(MissionFactory.MISSION_ID_MAKE_PRODUCT);
            if (!str.equals("")) {
                String[] positions = str.split(",");
                for (int a = 0; a < positions.length; a++) {
                    int i = Integer.parseInt(positions[a]);
                    MissionModel.pushMission(i);
                }
            }

        } else {
            ToastClcxUtil.getInstance().showToast("你太累了，无法炼金了！");
        }
    }

    @Override
    void removeAllFromBottle() {
        for (AlchemiItem item : itemsInBottle) {
            AlchemistaAction.builder().putItemToBag(item);
        }
    }
}
