package com.zoker.lib.fragment.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zoker.lib.fragment.interfaces.IListFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */

public abstract class SingleListFragment<Data,VH extends RecyclerView.ViewHolder> extends Fragment implements IListFragment<Data,VH>{

    private RecyclerView rootView;
    private Adapter adapter;
    private List<Data> mData;
    protected abstract RecyclerView.LayoutManager installLayoutManager();

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
        rootView.setAdapter(adapter = new Adapter());
    }

    private class Adapter extends RecyclerView.Adapter<VH> {
        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return SingleListFragment.this.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            SingleListFragment.this.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return SingleListFragment.this.getItemCount();
        }
    }

    @Override
    public void addData(List<Data> mdata) {
        if (this.mData == null)
            this.mData = mdata;
        else
            this.mData.addAll(mdata);
        notifyDataSetChanged();
    }

    @Override
    public void setData(List<Data> mdata) {
        clear();
        this.mData = mdata;
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        if (this.mData!=null)
            this.mData.clear();
        notifyDataSetChanged();
    }

    private void notifyDataSetChanged(){
        if (adapter!=null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public Data getItemData(int position) {
        if (mData == null)
            return null;
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }
}
