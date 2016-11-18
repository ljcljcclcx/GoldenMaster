package com.clcx.goldenmaster.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.clcx.goldenmaster.R;

/**
 * Created by ljc123 on 2016/10/31.
 */

public class AlertPoint extends BaseClcxView {
    private Paint mPaint;
    private Paint textPaint;
    private int messageCount;
    private int shadowOffset = 1;

    public AlertPoint(Context context) {
        super(context);
    }

    public AlertPoint(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlertPoint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.rgb(255, 80, 80));
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        messageCount = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (messageCount > 0) {
            //绘制阴影
            mPaint.setColor(Color.rgb(80, 80, 80));
            int radiu = getMeasuredWidth() > getMeasuredHeight() ? (getMeasuredHeight() - getPaddingTop() -
                    getPaddingBottom()) / 2 : (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) / 2;
            radiu -= shadowOffset;
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radiu, mPaint);

            //绘制本圆
            mPaint.setColor(Color.rgb(255, 80, 80));
            canvas.drawCircle(getMeasuredWidth() / 2 - shadowOffset, getMeasuredHeight() / 2 - shadowOffset, radiu,
                    mPaint);


            String str = messageCount <= 99 ? Integer.toString(messageCount) : "99+";
            Rect bounds = new Rect();
            textPaint.getTextBounds(str, 0, str.length(), bounds);
            textPaint.setTextSize(getContext().getResources().getDimensionPixelSize(R.dimen.alert_point_text_size));
            canvas.drawText(str,
                    (getMeasuredWidth() - bounds.width()) / 2 - shadowOffset,
                    (getMeasuredHeight() + bounds.height()) / 2 - shadowOffset,
                    textPaint);

        }


    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
        invalidate();
    }
}
