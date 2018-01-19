package com.zoker.lib.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zoker.lib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局
 */
// TODO: 2018/1/8 筛选行数的代码在onMeasure和onLayout有所重复，影响性能，找时间修改一下 
public class FlowLayout extends ViewGroup {

    private static final String TAG = "FlowLayout";

    private int mLines = 0;

    // 最大行数
    private int mMaxLines = -1;

    /**
     * 存储所有的View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();

    /**
     * 记录每一行的最大高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    /**
     * 记录每一行的总宽度
     */
    private List<Integer> mLineWidth = new ArrayList<>();


    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private int gravity = GRAVITY_LEFT;
    public static final int GRAVITY_CENTER = 0;
    public static final int GRAVITY_LEFT = 1;
    public static final int GRAVITY_RIGHT = 2;
    public static final int GRAVITY_BOTTOM = 3;


    public void initView(AttributeSet attributeSet) {
        TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.FlowLayout);
        gravity = a.getInt(R.styleable.FlowLayout_flow_gravity, GRAVITY_LEFT);
        a.recycle();
    }

    @Override
    protected LayoutParams generateLayoutParams(
            LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
        postInvalidate();
    }

    /**
     * 负责设置子控件的测量模式和大小 根据所有子控件设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        Log.i(TAG, "#onMeasure sizeWidth = " + sizeWidth + " and sizeHeight = " + sizeHeight + " and modeWidth = " + modeWidth + " and modeHeight = " + modeHeight);
        int maxWidth = sizeWidth - getPaddingLeft() - getPaddingRight();// 行的最大宽度
        int lineWidth = 0;// 当前的宽度
        int lineHeight = 0;// 当前的高度
        int line = 1;// 行数

        int maxLineWidth = 0;
        int viewHeight = 0;

        // 遍历每个子元素
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            if (child.getVisibility() == View.GONE) {
                continue;
            }

            // 测量每一个child的宽和高
//            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 得到child的lp
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            // 当前子空间实际占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            // 当前子空间实际占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if ((lineWidth + childWidth) > maxWidth) {
                if ((mMaxLines > 0 && line < mMaxLines) || (mMaxLines < 0)) {
                    //换行
                    line++;

                    lineWidth = childWidth;
                    lineHeight = childHeight;
                    viewHeight = viewHeight + lineHeight;
                    maxLineWidth = Math.max(lineWidth, maxLineWidth);
                } else {
                    //不换行
                    break;
                }
            } else {
                lineWidth = lineWidth + childWidth;
                maxLineWidth = Math.max(lineWidth, maxLineWidth);
                if (lineHeight < childHeight) {
                    viewHeight = viewHeight - lineHeight + childHeight;
                    lineHeight = childHeight;
                }
            }
        }

        int mdw = (modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : maxLineWidth;
        int mdh = (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : viewHeight;

        setMeasuredDimension(mdw, mdh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        measureLine();

        if (getChildCount() == 0 || mAllViews.isEmpty())
            return;

        switch (gravity) {
            case GRAVITY_LEFT:
                layoutLeft(changed, l, t, r, b);
                break;
            case GRAVITY_CENTER:
                layoutCenter(changed, l, t, r, b);
                break;
            case GRAVITY_RIGHT:
                layoutRight(changed, l, t, r, b);
                break;
            case GRAVITY_BOTTOM:
                layoutBottom(changed,l,t,r,b);
                break;
            default:
                layoutLeft(changed, l, t, r, b);

        }
    }

    /**
     * 设置最多显示行数
     * @param maxLines
     */
    public void setMaxLines(int maxLines) {
        mMaxLines = maxLines;
        postInvalidate();
    }

    public void measureLine() {
        mAllViews.clear();
        mLineHeight.clear();
        mLineWidth.clear();

        int width = getWidth() - getPaddingLeft() - getPaddingRight();

        int lineWidth = 0;
        int lineHeight = 0;

        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();
        int cCount = getChildCount();
        Log.i(TAG, "#onLayout childCount = " + cCount);
        int line = 1;

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                if (i == cCount - 1) {
                    // 记录最后一行
                    mLineHeight.add(lineHeight);
                    mAllViews.add(lineViews);
                    mLineWidth.add(lineWidth);
                }
                continue;
            }
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

//            Log.i(TAG, "#onLayout i = " + i + " and width = " + childWidth + " and height = " + childHeight);

            if ((childWidth + lineWidth) > width) {
                //判断需不需要换行前，记录当前行
                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);
                mLineWidth.add(lineWidth);

                if ((mMaxLines > 0 && line < mMaxLines) || (mMaxLines < 0)) {
                    //换行
                    lineWidth = 0;// 重置行宽
                    lineViews = new ArrayList<View>();
                    line++;
                } else {
                    //不换行,没有下一行数据
                    break;
                }
            }

            //添加view到追尾处
            lineHeight = Math.max(lineHeight, childHeight);
            lineWidth = lineWidth + childWidth;
            lineViews.add(child);

            if (i == cCount - 1) {
                // 记录最后一行
                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);
                mLineWidth.add(lineWidth);
            }
        }
        mLines = line;
    }

    public void layoutCenter(boolean changed, int l, int t, int r, int b) {
        List<View> lineViews = null;
        int lineHeight = 0;
        int space = 0;
        int left = getPaddingLeft();
        int top = 0;

        // 得到总行数
        for (int i = 0; i < mLines; i++) {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            int width = getWidth() - getPaddingLeft() - getPaddingRight();
            //当行左边的起始点
            space = (width > mLineWidth.get(i)) ? (width - mLineWidth.get(i)) / (lineViews.size() + 1) : 0;
            left = getPaddingLeft() + space;
            for (int index = 0; index < lineViews.size(); index++) {
                View childView = lineViews.get(index);
                if (childView.getVisibility() == View.GONE) {
                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

                //计算childView的left,top,right,bottom
                int childLeft = left + lp.leftMargin;
                int childTop = top + lp.topMargin;
                int childRight = childLeft + childView.getMeasuredWidth();
                int childBottom = childTop + childView.getMeasuredHeight();
                childView.layout(childLeft, childTop, childRight, childBottom);

                left = childRight + lp.rightMargin + space;
            }
            //下一行的起始高度
            lineHeight = mLineHeight.get(i);
            top = top + lineHeight;
        }
    }

    public void layoutLeft(boolean changed, int l, int t, int r, int b) {
        List<View> lineViews = null;
        int lineHeight = 0;
        int left = getPaddingLeft();
        int top = 0;

        // 得到总行数
        for (int i = 0; i < mLines; i++) {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            //当前行的最大高度
            lineHeight = mLineHeight.get(i);

            for (int index = 0; index < lineViews.size(); index++) {
                View childView = lineViews.get(index);
                if (childView.getVisibility() == View.GONE) {
                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

                //计算childView的left,top,right,bottom
                int childLeft = left + lp.leftMargin;
                int childTop = top + lp.topMargin;
                int childRight = childLeft + childView.getMeasuredWidth();
                int childBottom = childTop + childView.getMeasuredHeight();
                childView.layout(childLeft, childTop, childRight, childBottom);

                left = childRight + lp.rightMargin;
            }
            left = getPaddingLeft();
            top = top + lineHeight;
        }
    }

    public void layoutRight(boolean changed, int l, int t, int r, int b) {
        List<View> lineViews = null;
        int lineHeight = 0;
        int right = getWidth() - getPaddingRight();
        int top = 0;

        // 得到总行数
        for (int i = 0; i < mLines; i++) {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            //当前行的最大高度
            lineHeight = mLineHeight.get(i);

            for (int index = 0; index < lineViews.size(); index++) {
                View childView = lineViews.get(index);
                if (childView.getVisibility() == View.GONE) {
                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

                int childRight = right - lp.rightMargin;
                int childLeft = childRight - childView.getMeasuredWidth();

                //计算childView的left,top,right,bottom
                int childTop = top + lp.topMargin;
                int childBottom = childTop + childView.getMeasuredHeight();
                childView.layout(childLeft, childTop, childRight, childBottom);

                right = childLeft - lp.leftMargin;
            }
            right = getWidth() - getPaddingRight();
            top = top + lineHeight;
        }
    }

    public void layoutBottom(boolean changed, int l, int t, int r, int b) {
        List<View> lineViews = null;
        int lineHeight = 0;
        int left = getPaddingLeft();
        int bottom = getHeight();

        // 得到总行数
        for (int i = 0; i < mLines; i++) {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            //当前行的最大高度
            lineHeight = mLineHeight.get(i);

            for (int index = 0; index < lineViews.size(); index++) {
                View childView = lineViews.get(index);
                if (childView.getVisibility() == View.GONE) {
                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

                //计算childView的left,top,right,bottom
                int childLeft = left + lp.leftMargin;
                int childRight = childLeft + childView.getMeasuredWidth();
                int childBottom = bottom - lp.bottomMargin;;
                int childTop = childBottom - childView.getMeasuredHeight();
                childView.layout(childLeft, childTop, childRight, childBottom);

                left = childRight + lp.rightMargin;
            }
            left = getPaddingLeft();
            bottom = bottom - lineHeight;
        }
    }
}
