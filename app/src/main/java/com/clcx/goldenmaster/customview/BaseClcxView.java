package com.clcx.goldenmaster.customview;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ljc123 on 2016/10/27.
 */

public abstract class BaseClcxView extends View {

    public BaseClcxView(Context context) {
        super(context);
        init();
    }

    public BaseClcxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseClcxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected abstract void init();
}
