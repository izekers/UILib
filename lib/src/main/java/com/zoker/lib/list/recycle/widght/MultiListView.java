package com.zoker.lib.list.recycle.widght;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zoker.lib.list.recycle.interfaces.IListData;

import java.util.List;


/**
 * Created by zoker on 2018/1/23.
 */

public abstract class MultiListView extends RecyclerView implements IListData<Object> {
    private Adapter adapter;
    private List<Object> mData;

    public MultiListView(Context context) {
        super(context);
        initView();
    }

    public MultiListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MultiListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }


    private void initView() {
        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setLayoutManager(installLayoutManager());
        setAdapter(adapter = new Adapter());
    }

    public void addData(List<Object> mdata) {
        if (this.mData == null)
            this.mData = mdata;
        else
            this.mData.addAll(mdata);
        adapter.notifyDataSetChanged();
    }

    public List<Object> getData() {
        return mData;
    }

    public void setData(List<Object> mdata) {
        clear();
        this.mData = mdata;
        adapter.notifyDataSetChanged();
    }

    public void clear() {
        if (this.mData != null)
            this.mData.clear();
        adapter.notifyDataSetChanged();
    }

    public Object getItemData(int position) {
        if (mData == null)
            return null;
        return mData.get(position);
    }

    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    protected RecyclerView.LayoutManager installLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public abstract int getItemViewType(int position);

    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(ViewHolder holder, int position);

    private class Adapter extends RecyclerView.Adapter {
        @Override
        public int getItemViewType(int position) {
            return MultiListView.this.getItemViewType(position);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return MultiListView.this.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MultiListView.this.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return MultiListView.this.getItemCount();
        }
    }
}
