package com.clcx.goldenmaster.beans;

import android.content.Context;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.basement.MyApplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AlchemistaAction {
    public static AlchemistaActionBuilder builder() {
        return new AlchemistaActionBuilder(MyApplication.getContext(), Config.getAlchemista(MyApplication.getContext
                ()));
    }
}
