package com.zoker.lib.widght;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoker on 2018/1/22.
 */

public class TabListView extends LinearLayout {
    private TabLayout tabLayout;
    private List<TabLayout.Tab> tabs = new ArrayList<>();
    private String[] datas;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Adapter adapter;

    public TabListView(Context context) {
        super(context);
        initView();
    }

    public TabListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TabListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public TabListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    boolean isScroll = true;

    private void initView() {
        this.setOrientation(VERTICAL);
        tabLayout = new TabLayout(this.getContext());
        tabLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView = new WecatRecyclerView(getContext());
        recyclerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(layoutManager = new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter = new Adapter());
        this.addView(tabLayout);
        this.addView(recyclerView);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tabs.indexOf(tab);
                if (isScroll)
                    recyclerView.smoothScrollToPosition(index * 2);
                else
                    isScroll = true;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastTabIndex = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取可视的第一个view
                int currentPosition = layoutManager.findFirstVisibleItemPosition();
                int currentTabIndex = currentPosition / 2;
                if (currentPosition % 2 == 0 && currentTabIndex != lastTabIndex) {
                    lastTabIndex = currentTabIndex;
                    isScroll = false;
                    tabs.get(currentTabIndex).select();
                }
            }
        });
    }

    public void setData(String[] strings) {
        datas = strings;
        tabs.clear();
        for (int i = 0; i < strings.length; i++) {
            if (i % 2 == 0) {
                TabLayout.Tab tab = tabLayout.newTab().setText(strings[i]);
                tabs.add(tab);
                tabLayout.addTab(tab);
            }
        }
        adapter.notifyDataSetChanged();
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

    public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1)
                return new TitleViewHolder(parent);
            else
                return new ItemViewHolder(parent);
        }

        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0)
                return 1;
            else
                return 2;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((TextView) holder.itemView).setText(datas[position]);
        }

        @Override
        public int getItemCount() {
            return datas == null ? 0 : datas.length;
        }
    }
}
