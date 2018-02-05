package com.zoker.lib.list.recycle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by zoker on 2018/1/16.
 */

public abstract class SingleTypeFragment<Data, VH extends RecyclerView.ViewHolder> extends ListFragment<Data> {
    protected RecyclerView rootView;
    private SingleTypeFragment.Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = new RecyclerView(getContext());
        rootView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rootView.setLayoutManager(installLayoutManager());
        rootView.setAdapter(adapter = new SingleTypeFragment.Adapter());
        initView();
    }

    protected void initView() {

    }

    protected RecyclerView.LayoutManager installLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    private class Adapter extends RecyclerView.Adapter<VH> {
        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return SingleTypeFragment.this.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            SingleTypeFragment.this.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return SingleTypeFragment.this.getItemCount();
        }
    }


    protected void notifyDataSetChanged() {
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent);
    }

    @Override
    public void clear() {
        super.clear();
        notifyDataSetChanged();
    }

    @Override
    public void setData(List<Data> mdata) {
        super.setData(mdata);
        notifyDataSetChanged();
    }

    @Override
    public void addData(List<Data> mdata) {
        super.addData(mdata);
        notifyDataSetChanged();
    }

    public abstract VH onCreateViewHolder(ViewGroup parent);

    public abstract void onBindViewHolder(VH holder, int position);
}
