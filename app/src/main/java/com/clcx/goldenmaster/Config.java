package com.clcx.goldenmaster;

import android.content.Context;

import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.Alchemista;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.beans.MarketItemAction;

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
 * Created by ljc123 on 2016/8/30.
 */
public class Config {

    private static final String APP_ID = "com.clcx.goldenmaster";
    private static final String SPF_ALCHEMISTA = "alchemista";
    private static final String SPF_MARKET = "market";
    private static final String SPF_SAVEDTIME = "savetime";
    private static final String SPF_SAVEDAY = "saveday";

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
    public static final int PAGE_COUNT = 10;//每页显示条数

    private static final int MARKET_COUNT=1000;//每天最多创建多少个商品

    /**
     * 创建今日的市场
     *
     * @return
     */
    public static void createTodayMarket() {
        Context context = MyApplication.getContext();
        List<MarketItem> market = new ArrayList<>();
        for (int a = 0; a < MARKET_COUNT; a++) {
            market.add(MarketItemAction.getInstance().createMarketItem());
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

    public static List<MarketItem> getMarketItems() {
        Context context = MyApplication.getContext();
        List<MarketItem> market = new ArrayList<>();
        try {
            String string = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(SPF_MARKET,
                    "null");
            if (context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).contains(SPF_ALCHEMISTA) && string != null
                    && !string.equals("")) {
                //将16进制的数据转为数组，准备反序列化
                byte[] stringToBytes = StringToBytes(string);
                ByteArrayInputStream bis = new ByteArrayInputStream(stringToBytes);
                ObjectInputStream is = new ObjectInputStream(bis);
                //返回反序列化得到的对象
                market = (List<MarketItem>) is.readObject();
            } else {
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //所有异常返回null
        LogCLCXUtils.e("lll::" + market);
        return market;
    }

    private static void cacheCurrentTime(Context context) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit().putString(SPF_SAVEDTIME,
                format.format(new Date()));
    }

    private static String getSavedTime() {
        return MyApplication.getContext().getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(SPF_SAVEDTIME,
                "");
    }

    private static void cacheCurrentDay(Context context) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit().putString(SPF_SAVEDAY,
                format.format(new Date()));
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
        }

        long now = Long.parseLong(format.format(new Date()));
        return (now - savedDay);
    }

    public static void cacheAlchemista(Context context, Alchemista alchemista) {
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

    public static Alchemista getAlchemista(Context context) {
        Alchemista alchemista = null;
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
}
