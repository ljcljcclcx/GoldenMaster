package com.clcx.goldenmaster.basement.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.clcx.goldenmaster.basement.BaseActivity;
import com.google.gson.Gson;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class SpUtil {
    static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isNight() {
        return prefs.getBoolean("isNight", false);
    }

    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean("isNight", isNight).commit();
        if (context instanceof BaseActivity)
            ((BaseActivity) context).reload();
    }

//    public static _User getUser() {
//        return new Gson().fromJson(prefs.getString("user", ""), _User.class);
//    }
//
//    public static void setUser(_User user) {
//        prefs.edit().putString("user", new Gson().toJson(user)).commit();
//    }
}
