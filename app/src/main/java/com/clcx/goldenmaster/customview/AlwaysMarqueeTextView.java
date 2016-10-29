package com.clcx.goldenmaster.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.clcx.goldenmaster.basement.MyApplication;
import com.clcx.goldenmaster.basement.util.MathClcxUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ljc123 on 2016/10/10.
 */
public class AlwaysMarqueeTextView extends View {

    private Paint textPaint;
    private String text;
    private int currentX;
    private int maxLength;
    private int viewWidth;
    private RefreshMaqee refreshMaqee;
    private String[] texts;
    private boolean isManyTexts;//是否是多个text切换

    public AlwaysMarqueeTextView(Context context) {
        super(context);
        init();
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.rgb(50, 0, 0));
        text = "-";
        DisplayMetrics outMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(outMetrics);
        currentX = outMetrics.widthPixels;
        maxLength = 99999;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (currentX <= -1 * maxLength) {
                    currentX = viewWidth;
                    if (isManyTexts) {
                        text = texts[MathClcxUtil.getInstance().randomInt(texts.length)];
                    }
                }
                currentX -= 2;
                if (refreshMaqee != null) {
                    refreshMaqee.invalidateMe();
                }
            }
        };
        timer.schedule(task, 1000, 6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        viewWidth = getMeasuredWidth();
        //牌面文字
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.LEFT);
        Rect bounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        maxLength = bounds.width();
        canvas.drawText(text, currentX, getMeasuredHeight() / 2 + bounds
                .height() / 2, textPaint);
    }

    public void setText(String text, RefreshMaqee refreshMaqee) {
        this.refreshMaqee = refreshMaqee;
        this.text = text;
        this.isManyTexts = false;
        invalidate();
    }

    public void setTexts(String[] texts, RefreshMaqee refreshMaqee) {
        this.refreshMaqee = refreshMaqee;
        this.texts = texts;
        text = texts[MathClcxUtil.getInstance().randomInt(texts.length)];
        this.isManyTexts = true;
        invalidate();
    }

    public interface RefreshMaqee {
        void invalidateMe();
    }
}
