package com.clcx.goldenmaster.basement.tools;

import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;

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

    /**
     * Constructor to use when building a ScaleAnimation from code
     *
     * @param fromX       Horizontal scaling factor to apply at the start of the
     *                    animation
     * @param toX         Horizontal scaling factor to apply at the end of the animation
     * @param fromY       Vertical scaling factor to apply at the start of the
     *                    animation
     * @param toY         Vertical scaling factor to apply at the end of the animation
     * @param pivotXType  Specifies how pivotXValue should be interpreted. One of
     *                    Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or
     *                    Animation.RELATIVE_TO_PARENT.
     * @param pivotXValue The X coordinate of the point about which the object
     *                    is being scaled, specified as an absolute number where 0 is the
     *                    left edge. (This point remains fixed while the object changes
     *                    size.) This value can either be an absolute number if pivotXType
     *                    is ABSOLUTE, or a percentage (where 1.0 is 100%) otherwise.
     * @param pivotYType  Specifies how pivotYValue should be interpreted. One of
     *                    Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or
     *                    Animation.RELATIVE_TO_PARENT.
     * @param pivotYValue The Y coordinate of the point about which the object
     *                    is being scaled, specified as an absolute number where 0 is the
     *                    top edge. (This point remains fixed while the object changes
     *                    size.) This value can either be an absolute number if pivotYType
     *                    is ABSOLUTE, or a percentage (where 1.0 is 100%) otherwise.
     */
    public AnimationBuilder scaleAnimation(float fromX, float toX, float fromY, float toY,
                                           int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        ScaleAnimation anim = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType,
                pivotYValue);
        AnimationBuilder builder = new AnimationBuilder(anim);
        return builder;
    }

    public interface IAnimationTools {
        void animateEnd();
    }
}
