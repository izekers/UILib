package com.zoker.lib.list.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2017/11/28.
 */

public interface ITypesListFragment<VH extends RecyclerView.ViewHolder> extends IListFragment<Object, VH> {
    int getItemViewType(int position);

}
