package com.zoker.lib.list.fragment;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 简化版ListFragment
 * Created by Zoker on 2017/11/28.
 */

public abstract class SimpleSingleListFragment<Data, VH extends SimpleSingleListFragment.SimpleViewHolder<Data>> extends SingleListFragment<Data, VH> {
    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setData(getItemData(position));
    }

    @Override
    protected RecyclerView.LayoutManager installLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public static abstract class SimpleViewHolder<Data> extends RecyclerView.ViewHolder {
        public SimpleViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
            super(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
        }

        public SimpleViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void setData(Data data);
    }
}
