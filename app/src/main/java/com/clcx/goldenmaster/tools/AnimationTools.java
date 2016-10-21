package com.clcx.goldenmaster.tools;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AnimationTools {
    private AnimationTools() {
    }

    private static AnimationTools instance = null;

    public static AnimationTools getInstance() {
        if (instance == null) {
            synchronized (AnimationTools.class) {
                if (instance == null) {
                    instance = new AnimationTools();
                }

            }
        }
        return instance;
    }

    public AnimationBuilder alphaAnimation(float fromAlpha, float toAlpha) {
        AlphaAnimation alphaAnim = new AlphaAnimation(fromAlpha, toAlpha);
        AnimationBuilder builder = new AnimationBuilder(alphaAnim);
        return builder;
    }
}
