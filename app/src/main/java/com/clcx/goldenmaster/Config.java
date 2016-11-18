package com.clcx.goldenmaster;

import android.content.Context;
import android.content.Intent;

import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.beans.Alchemista;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.beans.MarketItemAction;
import com.clcx.goldenmaster.beans.MessageBean;
import com.clcx.goldenmaster.beans.MissionBean;
import com.clcx.goldenmaster.beans.TodayNews;
import com.clcx.goldenmaster.beans.TodayNewsAction;
import com.clcx.goldenmaster.customview.CapacityBean;
import com.clcx.goldenmaster.factories.MissionFactory;
import com.clcx.goldenmaster.ui.investment.InvestType;
import com.clcx.goldenmaster.ui.investment.InvestmentModel;
import com.clcx.goldenmaster.ui.market.MarketModel;
import com.clcx.goldenmaster.ui.message.MessageModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljc123 on 2016/8/30.
 */
public class Config {
    public static final int COLLECTION_ENERGE_REDUCE = 6;//搜索体力消耗
    public static final int ALCHAMIST_ENERGE_REDUCE = 4;//炼金体力消耗
    public static final int REST_ENERGE_RECOVERY[] = {50, 100};//休息体力恢复
    public static final int REST_MONEY_REDUCE[] = {180, 350};//休息金钱消耗

    public static final InvestType INVESTTYPE[] = new InvestType[]{
            new InvestType(1, 0.027f, "炼金师机构维护投资基金")
            , new InvestType(3, 0.032f, "冒险家协会基金")
            , new InvestType(6, 0.035f, "勇者医药协会基金")
            , new InvestType(12, 0.042f, "炼金大师天使投资基金")
    };

    public static final String APP_ID = "com.clcx.goldenmaster";
    public static final String SPF_MARKET = "market";
    public static final String SPF_MISSION = "mission";

    private static final String SPF_ALCHEMISTA = "alchemista";
    private static final String SPF_SAVEDTIME = "savetime";
    private static final String SPF_SAVEDAY = "saveday";
    private static final String SPF_NEWS = "todaynews";
    public static final String SPF_MESSAGE = "message";

    //投资记录
    public static final String SPF_INVESTMENT_ID = "investmentId";
    public static final String SPF_INVESTMENT_TIME = "investment_time";
    public static final String SPF_INVESTMENT_MONEY = "investment_money";

    // intent
    public static final String HEAD_DATA = "data";
    public static final String COUNT = "count";
    public static final String VH_CLASS = "vh";
    // RxBusEventName
    public static final String EVENT_LOGIN = "login";
    public static final String EVENT_DEL_ITEM = "delete_item";
    public static final String EVENT_UPDATE_ITEM = "update_item";
    public static final String EVENT_HEADDATA = "get_headdata";
    public static final String EVENT_COUNT = "get_count";
    //base
    private static final int MARKET_MATEIAL_COUNT = 50;//每天最多创建多少个固定材料
    private static final int MARKET_SHIJI_COUNT = 4;//每天最多创建多少个固定试剂


    /**
     * 获取一个android沙盒路径，pathName为自己新建的文件夹名字
     *
     * @param pathName
     * @return
     */
    public static String getInnerPath(String pathName) {
        return MyApplication.getContext().getFilesDir().getAbsolutePath() + File.separator + pathName;
    }

    /**
     * 创建今日任务
     *
     * @return
     */
    public static void createTodayMissions() {
        Context context = MyApplication.getContext();
        try {
            //先将序列化结果写到byte缓存中，其实就分配一个内存空间
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            //将对象序列化写入byte缓存
            os.writeObject(MissionFactory.missions);
            //将序列化的数据转为16进制保存
            String bytesToHexString = Config.bytesToHexString(bos.toByteArray());
            //保存该16进制数组
            context.getSharedPreferences(Config.APP_ID,
                    Context.MODE_PRIVATE).edit().putString(Config.SPF_MISSION, bytesToHexString).commit();
        } catch (Exception e) {
            e.printStackTrace();
            LogCLCXUtils.e("保存obj失败\n" + e.toString());
        }
    }

    /**
     * 创建今日新闻
     *
     * @return
     */
    public static void createTodayNews() {
        Context context = MyApplication.getContext();
        TodayNews news = TodayNewsAction.getInstance().createNews();
        try {
            //先将序列化结果写到byte缓存中，其实就分配一个内存空间
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            //将对象序列化写入byte缓存
            os.writeObject(news);
            //将序列化的数据转为16进制保存
            String bytesToHexString = bytesToHexString(bos.toByteArray());
            //保存该16进制数组
            context.getSharedPreferences(APP_ID,
                    Context.MODE_PRIVATE).edit().putString(SPF_NEWS, bytesToHexString).commit();
        } catch (Exception e) {
            e.printStackTrace();
            LogCLCXUtils.e("保存obj失败\n" + e.toString());
        }
    }

    /**
     * 获取今日新闻
     *
     * @return
     */
    public static TodayNews getTodayNews() {
        TodayNews news = null;
        Context context = MyApplication.getContext();
        try {
            String string = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(SPF_NEWS,
                    "null");
            if (context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).contains(SPF_NEWS) && string != null
                    && !string.equals("")) {
                //将16进制的数据转为数组，准备反序列化
                byte[] stringToBytes = StringToBytes(string);
                ByteArrayInputStream bis = new ByteArrayInputStream(stringToBytes);
                ObjectInputStream is = new ObjectInputStream(bis);
                //返回反序列化得到的对象
                news = (TodayNews) is.readObject();
            } else {
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //所有异常返回null
        return news;
    }

    /**
     * 创建今日的市场
     *
     * @return
     */
    public static void createTodayMarket() {
        Context context = MyApplication.getContext();
        List<MarketItem> market = MarketModel.getMarketItems();
        //先移除所有角色上架的物品
        for (int i = 0; i < market.size(); i++) {
            if (!market.get(i).getSeller().equals(getAlchemista().getName())) {
                market.remove(i);
                i--;
            }
        }

        for (int a = 0; a < MARKET_MATEIAL_COUNT; a++) {
            market.add(MarketItemAction.getInstance().createMarketItem());
        }

        for (int a = 0; a < MARKET_SHIJI_COUNT; a++) {
            market.add(MarketItemAction.getInstance().createMarketProduct());
        }
        try {
            //先将序列化结果写到byte缓存中，其实就分配一个内存空间
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            //将对象序列化写入byte缓存
            os.writeObject(market);
            //将序列化的数据转为16进制保存
            String bytesToHexString = bytesToHexString(bos.toByteArray());
            //保存该16进制数组
            context.getSharedPreferences(APP_ID,
                    Context.MODE_PRIVATE).edit().putString(SPF_MARKET, bytesToHexString).commit();
        } catch (Exception e) {
            e.printStackTrace();
            LogCLCXUtils.e("保存obj失败\n" + e.toString());
        }
    }

    /**
     * 新的一天
     */
    public static void newDay() {
        ToastClcxUtil.getInstance().showToast("新的一天开始了！");
        //顺序很重要
        createTodayNews();//1
        createTodayMarket();//2
        createTodayMissions();//3
    }


    private static void cacheCurrentTime(Context context) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit().putString(SPF_SAVEDTIME,
                format.format(new Date())).commit();
    }

    private static String getSavedTime() {
        return MyApplication.getContext().getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(SPF_SAVEDTIME,
                "");
    }

    private static void cacheCurrentDay(Context context) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit().putString(SPF_SAVEDAY,
                format.format(new Date())).commit();
    }

    private static String getSavedDay() {
        return MyApplication.getContext().getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(SPF_SAVEDAY,
                "");
    }

    /**
     * 计算保存的日期和当前日期之差
     *
     * @return
     */
    public static long deltaDay() {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        long savedDay = 0;
        try {
            savedDay = Long.parseLong(getSavedDay());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long now = Long.parseLong(format.format(new Date()));
        return (now - savedDay);
    }

    /**
     * 计算小时数差距，每小时-1体力
     *
     * @return
     */
    private static int deltaHour() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        long savedTime = 0;
        long delta = 0;
        try {
            savedTime = Long.parseLong(getSavedTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long now = Long.parseLong(format.format(new Date()));
        //如果超过了一天，那么返回值计算方式不同
        String content = "";
        if ((now - savedTime) / 10000 >= 1) {
            delta = 24 + (((now - savedTime) % 10000) / 100);
            content = "delta=" + delta + ",超过一天,上次记录" + savedTime + ",当前时间" + now;
        } else {
            delta = (now - savedTime) / 100;
            content = "delta=" + delta + ",上次记录" + savedTime + ",当前时间" + now;
        }
        if (delta > 0) {
            new MessageModel().sendMessage(new MessageBean(content, "测试消息", 1));
        }
        return (int) delta;
    }

    public static void cacheAlchemista(Context context, Alchemista alchemista) {
        alchemista.reduceEnerge(deltaHour());
        try {
            //先将序列化结果写到byte缓存中，其实就分配一个内存空间
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            //将对象序列化写入byte缓存
            os.writeObject(alchemista);
            //将序列化的数据转为16进制保存
            String bytesToHexString = bytesToHexString(bos.toByteArray());
            //保存该16进制数组
            context.getSharedPreferences(APP_ID,
                    Context.MODE_PRIVATE).edit().putString(SPF_ALCHEMISTA, bytesToHexString).commit();
        } catch (Exception e) {
            e.printStackTrace();
            LogCLCXUtils.e("保存obj失败\n" + e.toString());
        }
        cacheCurrentTime(context);
        cacheCurrentDay(context);
    }

    public static Alchemista getAlchemista() {
        Alchemista alchemista = null;
        Context context = MyApplication.getContext();
        try {
            String string = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(SPF_ALCHEMISTA,
                    "null");
            if (context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).contains(SPF_ALCHEMISTA) && string != null
                    && !string.equals("")) {
                //将16进制的数据转为数组，准备反序列化
                byte[] stringToBytes = StringToBytes(string);
                ByteArrayInputStream bis = new ByteArrayInputStream(stringToBytes);
                ObjectInputStream is = new ObjectInputStream(bis);
                //返回反序列化得到的对象
                alchemista = (Alchemista) is.readObject();
            } else {
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //所有异常返回null
        return alchemista;
    }

    public static void deleteAlchemista(Context context) {
        context.getSharedPreferences(APP_ID,
                Context.MODE_PRIVATE).edit().putString(SPF_ALCHEMISTA, "").commit();
        context.getSharedPreferences(APP_ID,
                Context.MODE_PRIVATE).edit().putString(SPF_SAVEDAY, "").commit();
        context.getSharedPreferences(APP_ID,
                Context.MODE_PRIVATE).edit().putString(SPF_SAVEDTIME, "").commit();
        context.getSharedPreferences(APP_ID,
                Context.MODE_PRIVATE).edit().putString(SPF_MESSAGE, "").commit();
        context.getSharedPreferences(APP_ID,
                Context.MODE_PRIVATE).edit().putString(SPF_MARKET, "").commit();
        context.getSharedPreferences(APP_ID,
                Context.MODE_PRIVATE).edit().putString(SPF_MISSION, "").commit();
        //删除投资数据
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
        //关闭服务
        context.sendBroadcast(new Intent("closeService"));
        ToastClcxUtil.getInstance().showToast("删除成功！");
    }

    /**
     * desc:将数组转为16进制
     *
     * @param bArray
     * @return modified:
     */
    public static String bytesToHexString(byte[] bArray) {
        if (bArray == null) {
            return null;
        }
        if (bArray.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * desc:将16进制的数据转为数组
     * <p>创建人：聂旭阳 , 2014-5-25 上午11:08:33</p>
     *
     * @param data
     * @return modified:
     */
    public static byte[] StringToBytes(String data) {
        String hexString = data.toUpperCase().trim();
        if (hexString.length() % 2 != 0) {
            return null;
        }
        byte[] retData = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i++) {
            int int_ch;  // 两位16进制数转化后的10进制数
            char hex_char1 = hexString.charAt(i); ////两位16进制数中的第一位(高位*16)
            int int_ch1;
            if (hex_char1 >= '0' && hex_char1 <= '9')
                int_ch1 = (hex_char1 - 48) * 16;   //// 0 的Ascll - 48
            else if (hex_char1 >= 'A' && hex_char1 <= 'F')
                int_ch1 = (hex_char1 - 55) * 16; //// A 的Ascll - 65
            else
                return null;
            i++;
            char hex_char2 = hexString.charAt(i); ///两位16进制数中的第二位(低位)
            int int_ch2;
            if (hex_char2 >= '0' && hex_char2 <= '9')
                int_ch2 = (hex_char2 - 48); //// 0 的Ascll - 48
            else if (hex_char2 >= 'A' && hex_char2 <= 'F')
                int_ch2 = hex_char2 - 55; //// A 的Ascll - 65
            else
                return null;
            int_ch = int_ch1 + int_ch2;
            retData[i / 2] = (byte) int_ch;//将转化后的数放入Byte里
        }
        return retData;
    }

    /**
     * 设置能力图bean
     *
     * @param item
     * @return
     */
    public static List<CapacityBean> setCapacities(AlchemiItem item) {
        List<CapacityBean> setCapacities = new ArrayList<>();
        for (int a = 0; a < AlchemiItem.MAX_PROPERTY_COUNT; a++) {
            setCapacities.add(new CapacityBean(item.getProperties()[a], item.getPropertiesPoint()[a], AlchemiItem
                    .CAPACITY_COLORS[a]));
        }
        return setCapacities;
    }
}
