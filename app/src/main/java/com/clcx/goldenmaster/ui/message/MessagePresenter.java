package com.clcx.goldenmaster.ui.message;

import android.content.Context;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.adapters.MessageAdapter;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.beans.MessageBean;
import com.clcx.goldenmaster.ui.market.MarketModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/22.
 */
public class MessagePresenter extends MessageContract.Presenter {
    //遍历市场，寻找自己出售的商品，判断时间，如果时间合适，那么就出售成功，并创造消息
    public static boolean createSoldMessage() {
        boolean isSold = false;
        List<MessageBean> messages = MessageModel.getMessages();
        if (messages == null) {
            messages = new ArrayList<>();
        }
        List<MarketItem> marketItem = MarketModel.getMarketItems();
        for (int a = 0; a < marketItem.size(); a++) {
            MarketItem mi = marketItem.get(a);
            if (mi.getSeller().equals(Config.getAlchemista().getName())) {
                //如果是自己，那么判断商品时间和价值
                int price = mi.getPrice();
                DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
                DateFormat messageFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                long currentDate = Long.parseLong(format.format(new Date()));
                String test = "currentDate - mi.getUpTime()=[" + currentDate + "-" + mi.getUpTime() + "]=" +
                        (currentDate - mi.getUpTime());
                StringBuffer msgStb = new StringBuffer();
//                msgStb.append(test);
                msgStb.append("\n您上架的" + mi.getItem().getName() + "已成功出售！获得$" + mi.getPrice());
                //价格区间
                if (priceRangeJudge(price, currentDate, mi, 0, LOW1, 0)
                        || priceRangeJudge(price, currentDate, mi, LOW1, LOW2, 2)
                        || priceRangeJudge(price, currentDate, mi, LOW2, NOM1, 2)
                        || priceRangeJudge(price, currentDate, mi, NOM1, NOM2, 10)
                        || priceRangeJudge(price, currentDate, mi, NOM2, HIGH1, 200)
                        || priceRangeJudge(price, currentDate, mi, HIGH1, HIGH2, 600)) {
                    messages.add(0, new MessageBean(msgStb.toString(), messageFormat.format(new Date()), mi.getPrice
                            ()));
                    marketItem.remove(a);
                    a--;
                    isSold = true;
                }
            }
        }
        MessageModel.saveMessages(messages);
        MarketModel.saveTodayMarket(marketItem);
        return isSold;
    }

    //遍历市场，寻找自己出售的商品，判断时间，如果时间合适，那么就出售成功，并创造消息
    public static void createNormalMessage(String content, int price) {
        List<MessageBean> messages = MessageModel.getMessages();
        if (messages == null) {
            messages = new ArrayList<>();
        }
        DateFormat messageFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        messages.add(0, new MessageBean(content, messageFormat.format(new Date()), price));
        MessageModel.saveMessages(messages);
    }

    @Override
    void clickMessage(int location) {
        MessageBean bean = (MessageBean) mView.getAdapter().getItem(location);
        AlchemistaAction.builder().getMoney(bean.getPrice());
        ToastClcxUtil.getInstance().showToast("获得$" + bean.getPrice());
        mView.getAdapter().removeItem(location);
        mView.getAdapter().notifyItemRemoved(location);
        mModel.removeMessage(location);
    }

    //出售价格区间，必须连续
    private static final float LOW1 = 0.3f;
    private static final float LOW2 = 0.6f;
    private static final float NOM1 = 0.9f;
    private static final float NOM2 = 1.2f;
    private static final float HIGH1 = 1.4f;
    private static final float HIGH2 = 1.7f;

    /**
     * 以下是出售区间，直接影响出售需要的时间
     *
     * @param price
     * @param currentDate
     * @param mi
     * @param low         最低价比率
     * @param high        最高价比率
     * @param time        时间，单位分钟
     * @return
     */
    private static boolean priceRangeJudge(int price, long currentDate, MarketItem mi, float low, float high, int
            time) {
        int priceRangeLow = (int) ((float) mi.getItem().getPrice() * low);
        int priceRangeHigh = (int) ((float) mi.getItem().getPrice() * high);
        if (price < priceRangeHigh && price >= priceRangeLow && currentDate - mi.getUpTime() > time) {
            return true;
        }
        return false;
    }
}
