package com.zoker.lib.list.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zoker.lib.list.SimpleViewHolder;

/**
 * 简化版ListFragment
 * Created by Zoker on 2017/11/28.
 */

public abstract class SimpleSingleListFragment<Data, VH extends SimpleViewHolder<Data>> extends SingleListFragment<Data, VH> {
    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setData(getItemData(position));
    }

    @Override
    protected RecyclerView.LayoutManager installLayoutManager() {
        return new LinearLayoutManager(getContext());
    }
}
