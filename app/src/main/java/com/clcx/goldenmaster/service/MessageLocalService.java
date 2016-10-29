package com.clcx.goldenmaster.service;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.RemoteService;
import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.basement.util.NotifClcxUtil;
import com.clcx.goldenmaster.beans.MessageBean;
import com.clcx.goldenmaster.ui.investment.InvestType;
import com.clcx.goldenmaster.ui.investment.InvestmentModel;
import com.clcx.goldenmaster.ui.message.AtyMessage;
import com.clcx.goldenmaster.ui.message.MessagePresenter;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 消息服务 这个服务是在登陆之后才开启的 在LoginActivity里注册的
 *
 * @author Nosensmile_L
 */
public class MessageLocalService extends Service {

    // 推送间隔时间
    private static final long INTERVEL = 10000;
    private boolean isRunning;
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    @Override
    public void onCreate() {
        binder = new MyBinder();
        if (conn == null) {
            conn = new Myconn();
        }
        Log.d("Info", "onCreate服务");
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MessageLocalService.this.bindService(new Intent(
                        MessageLocalService.this, MessageProService.class), conn,
                Context.BIND_IMPORTANT);

        isRunning = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isRunning) {
//                    LogCLCXUtils.e("Running");
                    MessagePresenter.createSoldMessage();
                    InvestType investmentMessage = InvestmentModel.investmentGetFeedback();
                    if (investmentMessage != null) {
                        MessagePresenter.createNormalMessage(
                                "您投资的【" + investmentMessage.getIntro() + "】已经获得回馈，获得资金$" + investmentMessage
                                        .getFinalMoney() + "投资日起（以后删除）：" + investmentMessage.getTestTIME()
                                , investmentMessage.getFinalMoney());
                    }
//                    if (MessagePresenter.createSoldMessage()) {
//                        NotifClcxUtil.getInstance().createNormalNotification(0, MessageLocalService.this,
//                                "您有新的物品被人购买了！", 100,
//                                AtyMessage.class);
//                    }
                }
            }
        }, 1000, INTERVEL);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        timer.cancel();
        if (cast != null) {
            unregisterReceiver(cast);
        }

        // System.out.println("服务已摧毁");

        super.onDestroy();
    }


    private MyBinder binder;
    private Myconn conn;
    private StopReceiver cast;

    private class MyBinder extends RemoteService.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString)
                throws RemoteException {

        }

        @Override
        public String getServiceName() throws RemoteException {
            return "LocalService";
        }

    }

    private class Myconn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            cast = new StopReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction("closeService");
            registerReceiver(cast, filter);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            MessageLocalService.this.startService(new Intent(
                    MessageLocalService.this, MessageProService.class));

            MessageLocalService.this.bindService(new Intent(
                            MessageLocalService.this, MessageProService.class), conn,
                    Context.BIND_IMPORTANT);

        }

    }

    private class StopReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("closeService")) {
                // MessageLocalService.this.stopSelf();
                isRunning = false;
            }
        }
    }
}
