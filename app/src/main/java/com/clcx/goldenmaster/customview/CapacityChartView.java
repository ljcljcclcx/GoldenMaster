package com.clcx.goldenmaster.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/5/30.
 */
public class CapacityChartView extends View {

    private Paint mPaint;
    private OnCapacityTopPointTouchListener onCapacityTopPointTouchListener;
    //边数，不能小于3，默认3
    private int edgeNumber;
    //字体大小
    private static final int FONT_SIZE = 30;
    //文字距离顶点的距离
    private static final int FONT_MARGIN = 20;
    //控件上下间距
    private static final int VIEW_MARGIN = 30;
    //内容集合
    private List<CapacityBean> data;
    //数据最大值，必须设置最大值
    private int maxValue;
    //半径
    private int radius;
    //等高线间距，每隔多少数据，绘制一条等高线
    private int contourIntervel;
    //所有顶点的坐标
    private PointF[] topPoints;
    //顶点触碰范围，范围越大，越灵敏
    private static final int TOUCH_RANGE = 2000;
    //顶点半径
    private static final int TOP_POINT_RADIU = 10;

    public CapacityChartView(Context context) {
        super(context);
        init();
    }

    public CapacityChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CapacityChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        edgeNumber = 3;
        data = new ArrayList<>();
        //默认是100
        maxValue = 100;
    }

    //设置边数
    public void setData(List<CapacityBean> data, int maxValue) {
        this.data = data;
        if (data.size() < 3) {
            this.edgeNumber = 3;
        } else {
            this.edgeNumber = data.size();
        }
        this.topPoints = new PointF[this.edgeNumber];
        this.maxValue = maxValue;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int diameter = getMeasuredHeight() > getMeasuredWidth() ? getMeasuredWidth() : getMeasuredHeight();
        //以多边形每个顶点组成的圆的半径
        radius = diameter / 2;
        //计算实际半径
        radius -= VIEW_MARGIN;

        //绘制外部框架
        drawFrame(canvas, true);
        //绘制等高线
        contourIntervel = 1;
        for (int a = 0; a < maxValue; a += contourIntervel) {
            drawContour(canvas, a);
        }
        //绘制内部
        drawFrame(canvas, false);
    }


    /**
     * 绘制外边框
     *
     * @param canvas
     */
    private void drawFrame(Canvas canvas, boolean isOutter) {
        //中心点
        PointF pieCenter = new PointF(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        //最大半径
        int maxRadius = radius;
        Path p = new Path();
        PointF[] pointFs = new PointF[this.edgeNumber];
        for (int i = 0; i < pointFs.length; i++) {
            int radiusF = maxRadius;
            //如果不是外部框架，则半径会根据数值进行改变
            if (!isOutter) {
                radiusF = (int) ((float) maxRadius * (float) this.data.get(i).getCapacityPoint() / (float) this
                        .maxValue);
                //不能超出框架
                if (radiusF > maxRadius) {
                    radiusF = maxRadius;
                }
            }
            //当前角度，因为默认是顺时针，所以应该是270度
            float nowAngle = 270 + (i * (360 / edgeNumber));
            Log.d("CapacityChartView", "nowAngle=" + nowAngle);
            float pointY = (float) radiusF * (float) Math.sin(nowAngle * Math.PI / 180);
            float pointX = (float) radiusF * (float) Math.cos(nowAngle * Math.PI / 180);
            pointFs[i] = new PointF(pointX, pointY);
            //在顶点旁写文字
            if (isOutter) {
                //记录顶点坐标
                this.topPoints[i] = new PointF(pieCenter.x + pointX, pieCenter.y + pointY);
                //清空虚线效果
                mPaint.setStrokeWidth(0);
                mPaint.setTextSize(FONT_SIZE);
                mPaint.setColor(Color.BLACK);
                //把能力顶点绘制出来
                mPaint.setColor(this.data.get(i).getPointColor());
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(this.topPoints[i].x, this.topPoints[i].y, TOP_POINT_RADIU, mPaint);
/*                String disTxt = "";
                if (this.data.size() > i) {
                    disTxt = this.data.get(i).getCapacityName();
                } else {
                    disTxt = "Error";
                }

                //计算文字宽高
                int fontHeight = (int) Math.ceil(mPaint.getFontMetrics().descent - mPaint.getFontMetrics().ascent);
                int fontWidth = (int) mPaint.measureText(disTxt);
                if (nowAngle >= 270 && nowAngle <= 450) {
                    //270~450是右侧的点
                    canvas.drawText(disTxt, pieCenter.x + pointX + FONT_MARGIN, pieCenter.y + pointY + fontHeight /
                            2, mPaint);
                } else {
                    //左侧的点
                    canvas.drawText(disTxt, pieCenter.x + pointX - fontWidth - FONT_MARGIN, pieCenter.y + pointY +
                            fontHeight / 2, mPaint);
                }
*/
                //绘制轴线
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(2);
                mPaint.setColor(Color.rgb(55, 155, 155));
                canvas.drawLine(pieCenter.x, pieCenter.y, pieCenter.x + pointX, pieCenter.y + pointY, mPaint);
            }
        }

        //清空虚线效果
        mPaint.setPathEffect(null);

        p.moveTo(pieCenter.x + pointFs[0].x, pieCenter.y + pointFs[0].y);
        for (PointF p1 : pointFs) {
            Log.d("CapacityChartView", "x=" + p1.x + ",y=" + p1.y);
            p.lineTo(pieCenter.x + p1.x, pieCenter.y + p1.y);
        }
        p.lineTo(pieCenter.x + pointFs[0].x, pieCenter.y + pointFs[0].y);
        //外部框架和内部图形的paint不一样
        if (isOutter) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
            mPaint.setColor(Color.BLACK);
        } else {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(0);
            mPaint.setColor(Color.argb(200, 255, 200, 200));
        }
        canvas.drawPath(p, mPaint);
    }

    /**
     * 绘制等高线
     *
     * @param canvas
     * @param lineNumber 数值
     */
    private void drawContour(Canvas canvas, int lineNumber) {
        //中心点
        PointF pieCenter = new PointF(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        //最大半径
        int maxRadius = radius;
        Path p = new Path();
        PointF[] pointFs = new PointF[this.edgeNumber];
        for (int i = 0; i < pointFs.length; i++) {
            int radiusF = (int) ((float) maxRadius * lineNumber / (float) maxValue);
            //不能超出框架
            if (radiusF > maxRadius) {
                radiusF = maxRadius;
            }
            //当前角度，因为默认是顺时针，所以应该是270度
            float nowAngle = 270 + (i * (360 / edgeNumber));
            Log.d("CapacityChartView", "nowAngle=" + nowAngle);
            float pointY = (float) radiusF * (float) Math.sin(nowAngle * Math.PI / 180);
            float pointX = (float) radiusF * (float) Math.cos(nowAngle * Math.PI / 180);
            pointFs[i] = new PointF(pointX, pointY);

        }

        //清空虚线效果
        mPaint.setPathEffect(null);

        p.moveTo(pieCenter.x + pointFs[0].x, pieCenter.y + pointFs[0].y);
        for (PointF p1 : pointFs) {
            Log.d("CapacityChartView", "x=" + p1.x + ",y=" + p1.y);
            p.lineTo(pieCenter.x + p1.x, pieCenter.y + p1.y);
        }
        p.lineTo(pieCenter.x + pointFs[0].x, pieCenter.y + pointFs[0].y);

        mPaint.setColor(Color.rgb(200, 200, 200));
        canvas.drawPath(p, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //判断某点位于圆内：(圆心x-点x)平方+(圆心y-点y)平方<=半径的平方
                for (int i = 0; i < edgeNumber; i++) {
                    if ((this.topPoints[i].x - x) * (this.topPoints[i].x - x)
                            + (this.topPoints[i].y - y) * (this.topPoints[i].y - y)
                            <= TOUCH_RANGE) {
                        if (onCapacityTopPointTouchListener != null) {
                            onCapacityTopPointTouchListener.touchCapacity(this.data.get(i));
                        }
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setOnCapacityTopPointTouchListener(OnCapacityTopPointTouchListener onCapacityTopPointTouchListener) {
        this.onCapacityTopPointTouchListener = onCapacityTopPointTouchListener;
    }

    public interface OnCapacityTopPointTouchListener {
        void touchCapacity(CapacityBean bean);
    }
}
