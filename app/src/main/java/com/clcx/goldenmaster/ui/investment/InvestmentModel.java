package com.clcx.goldenmaster.ui.investment;

import android.content.Context;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.MarketItem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/25.
 */

public class InvestmentModel implements InvestmentContract.Model {
    public static InvestType investmentGetFeedback() {
        InvestType returntype = null;
        boolean isEnd = false;
        long investTime = -1;
        try {
            investTime = Long.parseLong(MyApplication.getContext().getSharedPreferences(Config.APP_ID, Context
                    .MODE_PRIVATE).getString(Config.SPF_INVESTMENT_TIME,
                    ""));
        } catch (Exception e) {
        }
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        long current = Long.parseLong(format.format(new Date()));
        int id = MyApplication.getContext().getSharedPreferences(Config.APP_ID, Context
                .MODE_PRIVATE).getInt(Config.SPF_INVESTMENT_ID,
                -1);
        int deltaTime = -1;
        if (id != -1) {
            returntype = Config.INVESTTYPE[id];
            deltaTime = returntype.getHour();
        }
        int money = MyApplication.getContext().getSharedPreferences(Config.APP_ID, Context
                .MODE_PRIVATE).getInt(Config.SPF_INVESTMENT_MONEY,
                -1);
        if (returntype != null && money != -1 && deltaTime != -1 && investTime != -1 && (current - investTime) / 100 >=
                deltaTime) {
            //最终获利
            money = (int) ((float) money * (1.0f + ((float) deltaTime * returntype.getFeedback())));
            returntype.setFinalMoney(money);
            returntype.setTestTIME(investTime);
            //清空所有数据
            MyApplication.getContext().getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).edit().putString
                    (Config.SPF_INVESTMENT_TIME,
                            "").commit();
            MyApplication.getContext().getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).edit().putInt
                    (Config.SPF_INVESTMENT_ID,
                            -1).commit();
            MyApplication.getContext().getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).edit().putInt
                    (Config.SPF_INVESTMENT_MONEY,
                            -1).commit();
            isEnd = true;
        }
        return isEnd ? returntype : null;
    }

    public static void cacheInvestment(int id, int investMoney) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        MyApplication.getContext().getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).edit().putString
                (Config.SPF_INVESTMENT_TIME,
                        format.format(new Date())).commit();
        MyApplication.getContext().getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).edit().putInt
                (Config.SPF_INVESTMENT_ID,
                        id).commit();
        MyApplication.getContext().getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).edit().putInt
                (Config.SPF_INVESTMENT_MONEY,
                        investMoney).commit();
        ToastClcxUtil.getInstance().showToast("您向【" + Config.INVESTTYPE[id].getIntro() + "】投资了$" + investMoney);
    }


    public static boolean judgeIfInvest() {
        return MyApplication.getContext().getSharedPreferences(Config.APP_ID, Context
                .MODE_PRIVATE).getString(Config.SPF_INVESTMENT_TIME,
                "").equals("");
    }
}
