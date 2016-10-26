package com.clcx.goldenmaster.beans;

import com.clcx.goldenmaster.basement.util.MathClcxUtil;
import com.clcx.goldenmaster.factories.MaterialFactory;

/**
 * Created by ljc123 on 2016/10/26.
 */

public class MarketItemAction {
    private static MarketItemAction instance = null;

    public static MarketItemAction getInstance() {
        if (instance == null) {
            synchronized (MarketItemAction.class) {
                if (instance == null) {
                    instance = new MarketItemAction();
                }
            }
        }
        return instance;
    }

    public MarketItem createMarketItem() {
        AlchemiItem alitem = MaterialFactory.createRandomItem();
        int price = (int) (alitem.getPriceF() * MathClcxUtil.getInstance().randomFloat(1.2f, 2.0f));
        MarketItem item = new MarketItem(alitem
                , price);
        return item;
    }
}
