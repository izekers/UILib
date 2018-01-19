package com.zoker.lib.widght;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zoker on 2017/12/22.
 */

public class FoldingLayout extends ViewGroup {
    public FoldingLayout(Context context) {
        super(context);
        initView();
    }

    public FoldingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FoldingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FoldingLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    View topView, bottomView;
    ViewDragHelper viewDragHelper;

    private void initView() {
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return topView == child;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return child.getLeft();
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                int topBound = getPaddingTop();
                int bottomBound = getHeight() - child.getHeight() - getPaddingBottom();
                int tops = Math.min(Math.max(top, topBound), bottomBound);
                return tops;
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                int topBound = getPaddingTop();
                int bottomBound = getHeight() - releasedChild.getHeight() - getPaddingBottom();

                if (releasedChild.getTop() > (topBound + bottomBound) / 2) {
                    viewDragHelper.settleCapturedViewAt(0, bottomBound);
                    invalidate();
                } else {
                    viewDragHelper.settleCapturedViewAt(0, topBound);
                    invalidate();
                }
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                float percent = (float) top / (getHeight() - changedView.getHeight());
                //处理topView动画
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    changedView.setElevation(percent * 10);
                }
                //处理bottomView动画
                bottomView.setScaleX(1 - percent * 0.05f);
        }
        });
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        /**
         * 坑，一开始的时候以为是dis方法，后来发现是onInterceptTouchEvent方法，应该是确保肯定执行onTouchEvent吧
         */
        return viewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //此处处理将后加入的View置于前面
        if (getChildCount() != 2) {
            throw new RuntimeException("必须是2个子View");
        }
        bottomView = getChildAt(0);
        topView = getChildAt(1);
        bringChildToFront(topView);
    }


    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWith = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int childWidthWithMarin = childView.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int childHeightWithMarin = childView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;

            //高度为两个高度之和
            height = childHeightWithMarin + height;
            width = Math.max(childHeightWithMarin, width);
        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? sizeWith : width, heightMode == MeasureSpec.EXACTLY ? sizeHeight : height);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        /**
         * 考虑  需不需要根据滚动情况来设置布局的？？
         *
         * 下面观察的情况来看，似乎并不需要
         */
        /**
         * 遍历所有childView，根据其宽和高，以及margin进行布局
         */
        int cl = 0, ct = 0, cr = 0, cb = getHeight();

        for (int index = 0; index < getChildCount(); index++) {
            View childView = getChildAt(index);
            int cWidth = childView.getMeasuredWidth();
            int cHeight = childView.getMeasuredHeight();
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
            cl = marginLayoutParams.leftMargin;
            cr = cWidth + cl;
            cb = cb - marginLayoutParams.bottomMargin;
            ct = cb - cHeight;
            childView.layout(cl, ct, cr, cb);
            cb = ct - marginLayoutParams.topMargin;
        }
    }
}
