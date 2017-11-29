package com.zoker.uilib.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zoker.lib.fragment.list.SingleRefreshListFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */

public class DemoRefreshListFragment extends SingleRefreshListFragment{
    @Override
    public void setRefresh(boolean isRefresh) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onDownUI() {

    }

    @Override
    public void onUpUI() {

    }

    @Override
    public List sourse(int page) {
        return null;
    }
}
