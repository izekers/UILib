package com.zoker.lib.widght.bottomNavigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zoker.lib.R;
import com.zoker.lib.widght.SimpleFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */

public class BottomNavigationBar extends TabLayout {
    private ArrayList<BottomNavigationItem> items;
    private int tab_layout = -1;
    private static final int DEFAULT_LAYOUT = R.layout.bottom_navigation_tab;
    private BottomNavigationFragmentPagerAdapter adapter;
    private ViewPager viewPager;
    private FragmentManager fm;


    public BottomNavigationBar(Context context) {
        super(context);
        initView(null);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attr) {
        if (attr != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attr, R.styleable.BottomNavigationBar);
            typedArray.recycle();
        }
        setPadding(0, getPaddingTop() + 10, 0, getPaddingBottom() + 10);
        setSelectedTabIndicatorHeight(0);
        setBackgroundColor(Color.parseColor("#f8f8f8"));
    }

    public Worker setup(ViewPager viewPager, FragmentManager fm) {
        this.viewPager = viewPager;
        this.fm = fm;
        this.items = new ArrayList<>();
        this.adapter = new BottomNavigationFragmentPagerAdapter(fm);
        this.viewPager.setAdapter(adapter);
        setupWithViewPager(this.viewPager);
        return new Worker();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int realHeight = -1;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (getChildCount() != 0) {
//            View v = getChildAt(getChildCount() - 1);
//            measureChild(v, widthMeasureSpec, heightMeasureSpec);
//            MarginLayoutParams lp = (MarginLayoutParams) v.getLayoutParams();
//            realHeight = v.getMeasuredHeight() + lp.topMargin + lp.bottomMargin + getPaddingTop() + getPaddingBottom();
//            setMeasuredDimension(getMeasuredWidth(), realHeight);
//        }
    }

    public int getItemSize() {
        return items.size();
    }

    public BottomNavigationItem getTabItem(int position) {
        return items.get(position);
    }

    private TabViewHolder getNewsTabView() {
        if (tab_layout == -1)
            return new TabViewHolder(LayoutInflater.from(getContext()).inflate(DEFAULT_LAYOUT, this, false));
        else
            return new TabViewHolder(LayoutInflater.from(getContext()).inflate(tab_layout, this, false));
    }

    public void setTabLayout(@LayoutRes int tab_layout) {
        if (tab_layout == 0)
            this.tab_layout = -1;
        else
            this.tab_layout = tab_layout;
    }

    public class TabViewHolder {
        View rootView;
        TextView titleView;
        ImageView iconView;

        public TabViewHolder(View rootView) {
            this.rootView = rootView;
            this.titleView = (TextView) rootView.findViewById(R.id.tab_title);
            this.iconView = (ImageView) rootView.findViewById(R.id.tab_img);
        }

        public void setTitle(String title) {
            if (titleView != null)
                titleView.setText(title);
        }

        public void setIcon(Drawable drawable) {
            if (iconView != null)
                iconView.setImageDrawable(drawable);
        }
    }

    public class Worker {
        List<BottomNavigationItem> wokerItem = new ArrayList<>();
        private Worker(){};
        public Worker addItem(BottomNavigationItem item) {
            wokerItem.add(item);
            return this;
        }

        public void work() {
            for (int i = 0; i < wokerItem.size(); i++) {
                items.add(wokerItem.get(i));
                adapter.addFragment(wokerItem.get(i).getFragment(), items.get(i).getTitle());
            }

            for (int i = 0, size = getTabCount(); i < size; i++) {
                TabLayout.Tab tab = getTabAt(i);
                if (tab != null) {
                    TabViewHolder tabViewHolder = (TabViewHolder) tab.getTag();
                    if (tabViewHolder == null)
                        tabViewHolder = getNewsTabView();

                    tab.setCustomView(tabViewHolder.rootView);
                    tab.setTag(tabViewHolder);
                    tabViewHolder.setTitle(items.get(i).getTitle());
                    tabViewHolder.setIcon(items.get(i).getIcon(getContext()));
                }
            }
        }
    }
}
