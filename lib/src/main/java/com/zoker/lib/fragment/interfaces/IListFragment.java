package com.zoker.lib.fragment.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoker on 2017/11/28.
 */

public interface IListFragment<Data,VH extends RecyclerView.ViewHolder>{
    void setData(List<Data> mdata);
    void addData(List<Data> mdata);
    void clear();
    Data getItemData(int position);
    int getItemCount();
    void onBindViewHolder(VH holder,int position);
    VH onCreateViewHolder(ViewGroup parent,int viewType);
}
