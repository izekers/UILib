package com.zoker.uilib.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.zoker.lib.list.fragment.TypeListFragment;
import com.zoker.uilib.bean.Demo1;
import com.zoker.uilib.bean.Demo2;
import com.zoker.uilib.viewholder.Demo1ViewHolder;
import com.zoker.uilib.viewholder.Demo2ViewHolder;

/**
 * Created by Administrator on 2017/11/28.
 */

public class DemoListFramg extends TypeListFragment {
    @Override
    public int getItemViewType(int position) {
        if (getItemData(position) instanceof Demo1)
            return 1;
        else if (getItemData(position) instanceof Demo2)
            return 2;
        return 0;
    }

    @Override
    public boolean checkData(int position) {
        if ((getItemViewType(position) == 1 && getItemData(position) instanceof Demo1) || (getItemViewType(position) == 2 && getItemData(position) instanceof Demo2))
            return true;
        else throw new RuntimeException("类型不对");
    }

    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new Demo1ViewHolder(parent);
            case 2:
                return new Demo2ViewHolder(parent);
        }
        return null;
    }

    @Override
    protected RecyclerView.LayoutManager installLayoutManager() {
        return new LinearLayoutManager(getContext());
    }
}
