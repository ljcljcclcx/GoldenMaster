package com.clcx.goldenmaster.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ljc123 on 2016/11/18.
 */

public class MovePanalView extends BaseClcxView {
    private Paint mPaint;
    private float[] fingerPosition;
    private IMoveControll iMoveControll;
    private boolean fingerOn;

    public MovePanalView(Context context) {
        super(context);
    }

    public MovePanalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovePanalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fingerPosition = new float[2];
        fingerPosition[0] = 0.0f;
        fingerPosition[1] = 0.0f;
        fingerOn = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.argb(100, 255, 255, 255));
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, getMeasuredWidth() / 2, mPaint);
        if (fingerOn) {
            mPaint.setColor(Color.argb(180, 255, 255, 255));
            canvas.drawCircle(fingerPosition[0], fingerPosition[1], getMeasuredWidth() / 8, mPaint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fingerPosition[0] = event.getX();
                fingerPosition[1] = event.getY();
                fingerOn = true;
                if (iMoveControll != null) {
                    float[] center = {getMeasuredWidth() / 2, getMeasuredHeight() / 2};
                    float total = Math.abs(fingerPosition[0] - center[0]) + Math.abs(fingerPosition[1] - center[1]);
                    float offsetX = (fingerPosition[0] - center[0]) / total;
                    float offsetY = (fingerPosition[1] - center[1]) / total;
                    iMoveControll.move(offsetX, offsetY);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                fingerPosition[0] = event.getX();
                fingerPosition[1] = event.getY();
                fingerOn = true;
                if (iMoveControll != null) {
                    float[] center = {getMeasuredWidth() / 2, getMeasuredHeight() / 2};
                    float total = Math.abs(fingerPosition[0] - center[0]) + Math.abs(fingerPosition[1] - center[1]);
                    float offsetX = (fingerPosition[0] - center[0]) / total;
                    float offsetY = (fingerPosition[1] - center[1]) / total;
                    iMoveControll.move(offsetX, offsetY);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                fingerOn = false;
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setiMoveControll(IMoveControll iMoveControll) {
        this.iMoveControll = iMoveControll;
    }

    public interface IMoveControll {
        void move(float offsetX, float offsetY);//x增加率，y增加率，最终计算，用一个固定的速度值乘以这两个增加率就能计算出实际位移
    }
}
