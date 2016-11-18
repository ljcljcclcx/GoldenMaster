package com.clcx.goldenmaster.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by ljc123 on 2016/10/31.
 */

public class WhiteBorderView extends BaseClcxView {
    private Paint mPaint;

    public WhiteBorderView(Context context) {
        super(context);
    }

    public WhiteBorderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WhiteBorderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.rgb(255, 255, 255));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radiu = getMeasuredWidth() > getMeasuredHeight() ? (getMeasuredHeight() - getPaddingTop() -
                getPaddingBottom()) / 2 : (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) / 2;
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radiu, mPaint);
    }
}
