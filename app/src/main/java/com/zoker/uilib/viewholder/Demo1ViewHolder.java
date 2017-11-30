package com.zoker.uilib.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zoker.lib.list.fragment.TypeListFragment;
import com.zoker.uilib.R;
import com.zoker.uilib.bean.Demo1;

/**
 * Created by Administrator on 2017/11/28.
 */

public class Demo1ViewHolder extends TypeListFragment.TypeViewHolder<Demo1> {
    private static final int layout_res = R.layout.demo_txt;

    public Demo1ViewHolder(ViewGroup parent) {
        super(parent, layout_res);

    }

    public Demo1ViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(parent, layoutRes);
    }

    @Override
    public void setData(Demo1 demo1) {
        ((TextView) itemView).setText(demo1.getTitle());
    }
}