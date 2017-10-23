package com.zoker.uilib.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zoker.uilib.R;

/**
 *
 * Created by Administrator on 2017/9/29.
 */
public class MainViewHolder extends RecyclerView.ViewHolder {
    private static final int layout_id = R.layout.item_main;

    public TextView nameView;

    private static View createView(ViewGroup parent) {
        if (parent == null)
            return null;
        else
            return LayoutInflater.from(parent.getContext()).inflate(layout_id, parent, false);
    }

    public MainViewHolder(ViewGroup parent) {
        this(createView(parent));
    }

    private MainViewHolder(View itemView) {
        super(itemView);
        nameView = (TextView) itemView.findViewById(R.id.item_main_txt);
    }
}
