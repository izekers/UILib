package com.zoker.lib.widght;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoker on 2018/1/23.
 */

public class TabScrollView extends LinearLayout {
    private TabLayout tabLayout;
    private NestedScrollView scrollView;
    private LinearLayout rootView;
    private List<TabLayout.Tab> tabs = new ArrayList<>();
    private List<View> itemViews = new ArrayList<>();
    private int lastIndex = 0;
    private boolean hasAddSpace = false;
    private int bottomSpace = 0;
    private View bottomspaceView;

    public TabScrollView(Context context) {
        super(context);
        init();
    }

    public TabScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TabScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        this.setOrientation(VERTICAL);
        tabLayout = new TabLayout(this.getContext());
        tabLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        scrollView = new NestedScrollView(this.getContext());
        scrollView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView = new LinearLayout(this.getContext());
        rootView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rootView.setOrientation(VERTICAL);
        scrollView.addView(rootView);
        this.addView(tabLayout);
        this.addView(scrollView);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tabs.indexOf(tab);
                if (itemViews.size() > index) {
                    lastIndex = index;
                    scrollView.scrollTo(0, (int) itemViews.get(index).getY());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0) {
                    lastIndex = 0;
                    tabs.get(0).select();
                } else {
                    if (oldScrollY > scrollY) { //下滑
                        if (lastIndex != 0) {
                            if (itemViews.get(lastIndex - 1).getY() > scrollY) {
                                lastIndex = lastIndex - 1;
                                tabs.get(lastIndex).select();
                            }
                        }
                    } else {    //上滑
                        if (itemViews.get(lastIndex).getY() + itemViews.get(lastIndex).getMeasuredHeight() < scrollY && !(lastIndex + 1 >= itemViews.size())) {
                            lastIndex = lastIndex + 1;
                            tabs.get(lastIndex).select();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!hasAddSpace) {
            rootView.addView(getBottomSpaceView());
            hasAddSpace = true;
            invalidate();
        }
    }


    private View getBottomSpaceView() {
        rootView.removeView(bottomspaceView);
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        if (!itemViews.isEmpty()) {
            height = scrollView.getMeasuredHeight() - itemViews.get(itemViews.size() - 1).getMeasuredHeight() + 3 - bottomSpace;
        }

        if (bottomspaceView == null) {
            bottomspaceView = new View(getContext());
        }
        bottomspaceView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        return bottomspaceView;
    }

    /**
     * 修正底部空隙
     */
    public void setBottomSpace(int bottomSpace) {
        this.bottomSpace = bottomSpace;
    }

    /**
     * 移除所有子控件
     */
    public void removeAllItems() {
        rootView.removeAllViews();
        tabLayout.removeAllTabs();
        tabs.clear();
        itemViews.clear();
    }

    /**
     * 添加联动的item
     *
     * @param tabString tab的名字
     * @param view      联动的item
     */
    public void addItem(String tabString, View view) {
        if (view != null) {
            TabLayout.Tab tab = tabLayout.newTab().setText(tabString);
            tabs.add(tab);
            tabLayout.addTab(tab);
            itemViews.add(view);
            rootView.addView(view);
            rootView.addView(getBottomSpaceView());
        }
    }
}
