package com.zoker.lib.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zoker on 2018/1/19.
 */

public class PointView extends View {
    private @ColorInt
    int pointColor = Color.GRAY;
    protected float percent = 1.0f;
    private Paint mPaint;

    public PointView(Context context) {
        super(context);
        initView();
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode != MeasureSpec.AT_MOST)
            width = height;
        else if (heightMode == MeasureSpec.AT_MOST && widthMode != MeasureSpec.AT_MOST)
            height = width;

        setMeasuredDimension(width, height);
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
        postInvalidate();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(pointColor);
    }

    public void setPointColor(@ColorInt int pointColor) {
        this.pointColor = pointColor;
        mPaint.setColor(pointColor);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        float radius = (cx < cy ? cx : cy) * percent;
        canvas.drawCircle(cx, cy, radius, mPaint);
    }
}
