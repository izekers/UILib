package com.zoker.lib.widght;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zoker.lib.util.ScrollerHelper;

/**
 * Created by zoker on 2018/1/24.
 */

public class WecatRecyclerView extends RecyclerView {
    private ScrollerHelper scrollerHelper;
    private DotView dotView;
    private TextView textView;

    public WecatRecyclerView(Context context) {
        super(context);
        initView();
    }

    public WecatRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WecatRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        scrollerHelper = ScrollerHelper.create(this, new ScrollerHelper.Callback() {
            @Override
            public void onRelease(float scrollX, float scrollY) {
                float dotTopbound = -dotView.getMeasuredHeight();
                float topbound = -textView.getMeasuredHeight();
                if (scrollY < (dotTopbound + topbound / 2)) {
                    scrollerHelper.autoMove(0, -(dotView.getMeasuredHeight() + textView.getMeasuredHeight()));
                } else
                    scrollerHelper.autoMove(0, 0);
            }

            @Override
            public int clampViewPositionVertical(float scrollY, float dy) {
                return (int) (-dy);
            }

            @Override
            public void onMove(float scrollX, float scrollY) {
                super.onMove(scrollX, scrollY);
                float percent = Math.abs(scrollY) * 1.0f / dotView.getMeasuredHeight();
                dotView.setPercent(percent);
                if (percent > 1.0f) {
                    float scroll = Math.abs(scrollY) - dotView.getMeasuredHeight();
                    float topPercent = scroll / textView.getMeasuredHeight();
                    setTopViewPercent(topPercent);
                }

            }

            @Override
            public boolean isVerticalMove(float scrollY, float diff) {
                return true;
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dotView = new DotView(getContext());
        MarginLayoutParams layoutParams = new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        layoutParams.topMargin = -100;
        dotView.setLayoutParams(layoutParams);
        textView = new TextView(getContext());
        MarginLayoutParams layoutParams2 = new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        layoutParams.topMargin = -200;
        textView.setLayoutParams(layoutParams2);
        textView.setText("this is a top view");
        textView.setBackgroundColor(Color.RED);
        addView(dotView, 0);
        addView(textView, 0);
    }

    private void setTopViewPercent(float percent) {
        if (percent > 1.0f)
            percent = 1.0f;
        float alpha = 255 * percent;
        float x = -200 - textView.getMeasuredHeight() * (1 - percent);
        textView.getBackground().setAlpha((int) alpha);
        textView.setX(x);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //        super.onLayout(changed, l, t, r, b);
        dotView.layout(0, -100, dotView.getMeasuredWidth(), 0);
        textView.layout(0, -200, dotView.getMeasuredWidth(), -100);

        for (int i = 2; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }

        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return scrollerHelper.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return scrollerHelper.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        scrollerHelper.computeScroll();
    }
}
