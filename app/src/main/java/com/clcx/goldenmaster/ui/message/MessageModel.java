package com.clcx.goldenmaster.ui.message;

import android.content.Context;
import android.os.Message;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.beans.Alchemista;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.beans.MessageBean;
import com.clcx.goldenmaster.ui.creator.CreatorContract;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/25.
 */

public class MessageModel implements MessageContract.Model {


    public static void saveMessages(List<MessageBean> beans) {
        Context context = MyApplication.getContext();
        try {
            //先将序列化结果写到byte缓存中，其实就分配一个内存空间
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            //将对象序列化写入byte缓存
            os.writeObject(beans);
            //将序列化的数据转为16进制保存
            String bytesToHexString = Config.bytesToHexString(bos.toByteArray());
            //保存该16进制数组
            context.getSharedPreferences(Config.APP_ID,
                    Context.MODE_PRIVATE).edit().putString(Config.SPF_MESSAGE, bytesToHexString).commit();
        } catch (Exception e) {
            e.printStackTrace();
            LogCLCXUtils.e("保存obj失败\n" + e.toString());
        }
    }

    public static List<MessageBean> getMessages() {
        List<MessageBean> beans = new ArrayList<>();
        Context context = MyApplication.getContext();
        try {
            String string = context.getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).getString(Config
                            .SPF_MESSAGE,
                    "null");
            if (context.getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).contains(Config.SPF_MESSAGE) &&
                    string != null
                    && !string.equals("")) {
                //将16进制的数据转为数组，准备反序列化
                byte[] stringToBytes = Config.StringToBytes(string);
                ByteArrayInputStream bis = new ByteArrayInputStream(stringToBytes);
                ObjectInputStream is = new ObjectInputStream(bis);
                //返回反序列化得到的对象
                beans = (List<MessageBean>) is.readObject();
            } else {
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //所有异常返回null
        return beans;
    }

    @Override
    public void removeMessage(int location) {
        List<MessageBean> beans = getMessages();
        beans.remove(location);
        saveMessages(beans);
    }

    /**
     * 发送消息
     */
    public void sendMessage(MessageBean bean) {
        List<MessageBean> messages = getMessages();
        messages.add(0, bean);
        saveMessages(messages);
    }

}
