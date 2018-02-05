package com.zoker.lib.list.recycle.widght;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.zoker.lib.list.recycle.interfaces.IListData;

import java.util.List;


/**
 * Created by zoker on 2018/1/16.
 */

public abstract class SingleTypeListView<Data, VH extends RecyclerView.ViewHolder> extends RecyclerView implements IListData<Data> {
    private SingleTypeListView.Adapter mAdapter;
    private List<Data> mData;


    public SingleTypeListView(Context context) {
        super(context);
        initView();
    }

    public SingleTypeListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SingleTypeListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public void addData(List<Data> mdata) {
        if (this.mData == null)
            this.mData = mdata;
        else
            this.mData.addAll(mdata);
        mAdapter.notifyDataSetChanged();
    }

    public List<Data> getData() {
        return mData;
    }

    public void setData(List<Data> mdata) {
        clear();
        this.mData = mdata;
        mAdapter.notifyDataSetChanged();
    }

    public void clear() {
        if (this.mData != null)
            this.mData.clear();
        mAdapter.notifyDataSetChanged();
    }

    public Data getItemData(int position) {
        if (mData == null)
            return null;
        return mData.get(position);
    }

    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    protected void initView() {
        setLayoutManager(installLayoutManager());
        setAdapter(mAdapter = new SingleTypeListView.Adapter());
    }

    protected RecyclerView.LayoutManager installLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent);
    }

    public abstract VH onCreateViewHolder(ViewGroup parent);

    public abstract void onBindViewHolder(VH holder, int position);

    private class Adapter extends RecyclerView.Adapter<VH> {
        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return SingleTypeListView.this.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            SingleTypeListView.this.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return SingleTypeListView.this.getItemCount();
        }
    }

}
