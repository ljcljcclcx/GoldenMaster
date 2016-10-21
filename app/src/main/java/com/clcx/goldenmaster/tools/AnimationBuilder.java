package com.clcx.goldenmaster.tools;

import android.view.View;
import android.view.animation.Animation;

import com.clcx.goldenmaster.basement.LogCLCXUtils;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AnimationBuilder {
    private Animation animation;

    public AnimationBuilder(Animation animation) {
        this.animation = animation;
    }

    public AnimationBuilder setDuration(int duration) {
        animation.setDuration(duration);
        return this;
    }

    private void setAnimationEnd(final IAnimationTools iat) {
        LogCLCXUtils.e("setAnimationEnd");
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                LogCLCXUtils.e("onAnimationEnd");
                iat.animateEnd();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public AnimationBuilder setFillAfter(boolean flag) {
        animation.setFillAfter(flag);
        return this;
    }

    public void start(View view, IAnimationTools iat) {
        view.startAnimation(animation);
        if (iat != null) {
            setAnimationEnd(iat);
        }
    }
}
