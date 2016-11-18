package com.clcx.goldenmaster.ui.mission;

import android.content.Context;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.beans.MissionBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/25.
 */

public class MissionModel implements MissionContract.Model {

    public static void saveMission(List<MissionBean> mission) {
        Context context = MyApplication.getContext();
        try {
            //先将序列化结果写到byte缓存中，其实就分配一个内存空间
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            //将对象序列化写入byte缓存
            os.writeObject(mission);
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

    public static List<MissionBean> getMissions() {
        Context context = MyApplication.getContext();
        List<MissionBean> mission = new ArrayList<>();
        try {
            String string = context.getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).getString(Config
                            .SPF_MISSION,
                    "null");
            if (context.getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).contains(Config.SPF_MISSION) &&
                    string != null
                    && !string.equals("")) {
                //将16进制的数据转为数组，准备反序列化
                byte[] stringToBytes = Config.StringToBytes(string);
                ByteArrayInputStream bis = new ByteArrayInputStream(stringToBytes);
                ObjectInputStream is = new ObjectInputStream(bis);
                //返回反序列化得到的对象
                mission = (List<MissionBean>) is.readObject();
            } else {
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //所有异常返回null
        return mission;
    }

    /**
     * 推进任务进度
     */
    public static void pushMission(int position) {
        List<MissionBean> missions = getMissions();
        if (!missions.get(position).isFinish()) {
            missions.get(position).addNumber();
            saveMission(missions);
        }

    }

    /**
     * 关闭任务
     */
    public static void closeMIssion(int position) {
        List<MissionBean> missions = getMissions();
        missions.remove(position);
        saveMission(missions);
    }

    public static String getThisIdPosition(int missionId) {
        StringBuffer stb = new StringBuffer();
        for (int a = 0; a < getMissions().size(); a++) {
            if (missionId == getMissions().get(a).getTypeId() && !getMissions().get(a).isFinish()) {
                stb.append(a + ",");
            }
        }
        if (stb.length() > 0) {
            stb.deleteCharAt(stb.length() - 1);
        }
        return stb.toString();
    }
}
