package com.zoker.lib.widght;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zoker.lib.util.DisplayUtil;

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
    private String[] datas;


    private List<View> indexTopViews = new ArrayList<>();
    private List<View> indexBottomViews = new ArrayList<>();

    private int lastIndex = 0;

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
                if (indexTopViews.size() > index) {
                    lastIndex = index;
                    scrollView.scrollTo(0, (int) indexTopViews.get(index).getY());
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
                            if (indexTopViews.get(lastIndex - 1).getY() > scrollY) {
                                lastIndex = lastIndex - 1;
                                tabs.get(lastIndex).select();
                            }
                        }
                    } else {    //上滑
                        if (indexBottomViews.get(lastIndex).getY() + indexBottomViews.get(lastIndex).getMeasuredHeight() < scrollY && !(lastIndex + 1 >= indexBottomViews.size())) {
                            lastIndex = lastIndex + 1;
                            tabs.get(lastIndex).select();
                        }
                    }
                }
            }
        });
    }

    boolean hasAddSpace = false;
    int bottomSpace = 0;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!hasAddSpace) {
            View view = new View(getContext());
            int height = scrollView.getMeasuredHeight() - indexTopViews.get(indexTopViews.size() - 1).getMeasuredHeight() - indexBottomViews.get(indexBottomViews.size() - 1).getMeasuredHeight() + 3 - bottomSpace;
            view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
            rootView.addView(view);
            hasAddSpace = true;
        }
    }

    public void setData(String[] strings) {
        datas = strings;
        rootView.removeAllViews();
        tabs.clear();
        indexTopViews.clear();
        indexBottomViews.clear();
        for (int i = 0; i < strings.length; i++) {
            if (i % 2 == 0) {
                TabLayout.Tab tab = tabLayout.newTab().setText(strings[i]);
                tabs.add(tab);
                tabLayout.addTab(tab);
                TextView titleView = (TextView) (new TitleViewHolder(rootView)).itemView;
                titleView.setText(strings[i]);
                indexTopViews.add(titleView);
                rootView.addView(titleView);
            } else {
                TextView titleView = (TextView) (new ItemViewHolder(rootView)).itemView;
                titleView.setText(strings[i]);
                indexBottomViews.add(titleView);
                rootView.addView(titleView);
            }
        }
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(ViewGroup parent) {
            this(new TextView(parent.getContext()));
        }

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
        }
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        public TitleViewHolder(ViewGroup parent) {
            this(new TextView(parent.getContext()));
        }

        public TitleViewHolder(View itemView) {
            super(itemView);
            itemView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
            itemView.setBackgroundColor(Color.RED);
        }
    }
}
