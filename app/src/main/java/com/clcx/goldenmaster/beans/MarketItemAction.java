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

    private static final String[] SELLER_NAMES = {"蓝莓", "飞翔的炼金师", "遗忘", "坂田大佐", "玛特纳公爵", "殇丶", "455892732", "恶魔doom",
            "古堡幽灵"};

    public MarketItem createMarketItem() {
        AlchemiItem alitem = MaterialFactory.createRandomItem();
        int price = (int) (alitem.getPriceF() * MathClcxUtil.getInstance().randomFloat(1.3f, 2.1f));
        MarketItem item = new MarketItem(alitem
                , price, SELLER_NAMES[MathClcxUtil.getInstance().randomInt(SELLER_NAMES.length)]);
        return item;
    }

    public MarketItem createMarketProduct() {
        AlchemiItem alitem = MaterialFactory.createRandomProduct();
        int price = (int) (alitem.getPriceF() * MathClcxUtil.getInstance().randomFloat(1.5f, 3.0f));
        MarketItem item = new MarketItem(alitem
                , price, SELLER_NAMES[MathClcxUtil.getInstance().randomInt(SELLER_NAMES.length)]);
        return item;
    }
}
