package com.clcx.goldenmaster.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ljc123 on 2016/10/27.
 */

public class EnergeBar extends BaseClcxView {
    private Paint mPaint;
    private float max;
    private float progress;

    public EnergeBar(Context context) {
        super(context);
    }

    public EnergeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EnergeBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.rgb(255, 255, 150));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);

        canvas.drawRect(new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight()), mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(0, 0, (int) ((progress / max) * (float) getMeasuredWidth()),
                getMeasuredHeight()), mPaint);
    }

    public void initUI(float max, float progress) {
        this.progress = progress;
        this.max = max;
        invalidate();
    }
}
