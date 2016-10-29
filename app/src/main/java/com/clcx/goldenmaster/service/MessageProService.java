package com.clcx.goldenmaster.service;


import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.clcx.goldenmaster.RemoteService;

/**
 * 保护进程
 *
 * @author Nosensmile_L
 */
public class MessageProService extends Service {
    private MyBinder binder;
    private Myconn conn;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return binder;
    }

    @Override
    public void onCreate() {
        binder = new MyBinder();
        if (conn == null) {
            conn = new Myconn();
        }
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.bindService(new Intent(MessageProService.this,
                MessageLocalService.class), conn, Context.BIND_IMPORTANT);
        // TODO Auto-generated method stub
        return super.onStartCommand(intent, flags, startId);
    }

    private class MyBinder extends RemoteService.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString)
                throws RemoteException {

        }

        @Override
        public String getServiceName() throws RemoteException {
            return "protect";
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class Myconn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Info", "链接本地服务成功！");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            MessageProService.this.startService(new Intent(MessageProService.this,
                    MessageLocalService.class));

            MessageProService.this.bindService(new Intent(MessageProService.this,
                    MessageLocalService.class), conn, Context.BIND_IMPORTANT);
        }

    }

}
