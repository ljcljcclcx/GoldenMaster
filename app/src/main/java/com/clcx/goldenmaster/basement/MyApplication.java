package com.clcx.goldenmaster.basement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * 整个application
 *
 * @author Nosensmile_L
 */
public class MyApplication extends Application {

    private static Context context;
    /**
     * 对外提供整个应用生命周期的Context
     **/
    private static MyApplication instance;
    /***
     * 寄存整个应用Activity
     **/
    private final Stack<WeakReference<Activity>> activitys = new Stack<WeakReference<Activity>>();
    /**
     * 版本号和版本名称
     */
    private int appVersion;
    private String appVersionName;

    public int getAppVersion() {
        return appVersion;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (instance == null) {
            instance = this;
        }
        try {
            appVersion = getPackageManager()
                    .getPackageInfo(getPackageName(), 0).versionCode;
            appVersionName = getPackageManager().getPackageInfo(
                    getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

//        CrashHandler.getInstance().init(getApplicationContext());
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /******************************************* Application中存放的Activity操作（压栈/出栈）API（开始）
     * *****************************************/

    /**
     * 将Activity压入Application栈
     *
     * @param task 将要压入栈的Activity对象
     */
    public void pushTask(WeakReference<Activity> task) {
        activitys.push(task);
    }

    /**
     * 将传入的Activity对象从栈中移除
     *
     * @param task
     */
    public void removeTask(WeakReference<Activity> task) {
        activitys.remove(task);
    }

    /**
     * 根据指定位置从栈中移除Activity
     *
     * @param taskIndex Activity栈索引
     */
    public void removeTask(int taskIndex) {
        if (activitys.size() > taskIndex)
            activitys.remove(taskIndex);
    }

    /**
     * 将栈中Activity移除至栈顶
     */
    public void removeToTop() {
        int end = activitys.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            if (!activitys.get(i).get().isFinishing()) {
                activitys.get(i).get().finish();
            }
        }
    }

    /**
     * 移除全部（用于整个应用退出）
     */
    public void removeAll() {
        // finish所有的Activity
        for (WeakReference<Activity> task : activitys) {
            if (!task.get().isFinishing()) {
                task.get().finish();
            }
        }
    }

}
