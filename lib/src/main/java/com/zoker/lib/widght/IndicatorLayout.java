package com.zoker.lib.widght;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 浮点指示器
 * Created by Administrator on 2016/10/21.
 */
public abstract class IndicatorLayout extends LinearLayout {
    public IndicatorLayout(Context context) {
        super(context);
        initView();
    }

    public IndicatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public IndicatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IndicatorLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }


    private void initView() {
        this.setOrientation(HORIZONTAL);
    }

    private int space = 20;

    public abstract View getIndicatorView();

    private List<View> indicatorList = new ArrayList<>();
    private int lastSelectIndex = NONE_SELECT;
    public static final int NONE_SELECT = -1;

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }


    public void setCount(int count) {
        if (indicatorList.size() < count) {
            int newCount = count - indicatorList.size();
            for (int index = 0; index < newCount; index++) {
                View newIndicatorView = getIndicatorView();
                indicatorList.add(newIndicatorView);
                this.addView(newIndicatorView);

                MarginLayoutParams layoutParams = (MarginLayoutParams) newIndicatorView.getLayoutParams();

                if (layoutParams == null)
                    layoutParams = new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                if (indicatorList.indexOf(newIndicatorView) != 0) {
                    if (getOrientation() == HORIZONTAL)
                        layoutParams.leftMargin = space;
                    else
                        layoutParams.topMargin = space;
                }
                newIndicatorView.setLayoutParams(layoutParams);

            }
        }
    }

    public void select(int index) {
        if (lastSelectIndex != NONE_SELECT && lastSelectIndex < indicatorList.size())
            indicatorList.get(lastSelectIndex).setSelected(false);

        if (index != NONE_SELECT && index < indicatorList.size()) {
            indicatorList.get(index).setSelected(true);
            lastSelectIndex = index;
        }
    }
}
