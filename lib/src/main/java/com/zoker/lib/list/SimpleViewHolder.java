package com.zoker.lib.list;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zoker on 2017/11/30.
 */
public abstract class SimpleViewHolder<Data> extends RecyclerView.ViewHolder {
    public SimpleViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    public SimpleViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setData(Data data);
}
