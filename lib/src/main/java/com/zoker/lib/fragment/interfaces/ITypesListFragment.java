package com.zoker.lib.fragment.interfaces;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/28.
 */

public interface ITypesListFragment<VH extends RecyclerView.ViewHolder> extends IListFragment<Object, VH> {
    int getItemViewType(int position);

}
