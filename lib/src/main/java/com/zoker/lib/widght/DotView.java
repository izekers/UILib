package com.zoker.lib.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zoker on 2018/1/11.
 */

public class DotView extends View {
    private float percent;
    private float maxRadius = 15;
    private float maxDist = 60;
    private Paint mPaint;
    private @ColorInt
    int mDotColor = Color.BLACK;
    private int dotCount;
    private int select = -1;

    public DotView(Context context) {
        super(context);
        initView();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(mDotColor);
    }

    public void setPercent(float percent) {
        this.percent = percent;
        postInvalidate();
    }

    public void setDotColor(@ColorInt int color) {
        mDotColor = color;
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw(canvas, percent);
    }

    private void draw(Canvas canvas, float percent) {
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        if (percent <= 0.5f) {
            float finalPercent = percent * 2;
            float radius = 10 * finalPercent;
            canvas.drawCircle(cx, cy, radius, mPaint);
        } else {
            if (percent > 1.0f)
                percent = 1.0f;
            float finalPercent = (1 - (1 - percent) * 2);
            float radius = 10;
            canvas.drawCircle(cx, cy, radius, mPaint);
            canvas.drawCircle(cx - finalPercent * 50, cy, radius, mPaint);
            canvas.drawCircle(cx + finalPercent * 50, cy, radius, mPaint);
        }
    }

}
