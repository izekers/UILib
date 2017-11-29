package com.zoker.uilib.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zoker.lib.fragment.interfaces.ITypesListFragment;
import com.zoker.lib.fragment.list.TypeListFragment;
import com.zoker.uilib.R;
import com.zoker.uilib.bean.Demo1;
import com.zoker.uilib.bean.Demo2;

/**
 *
 * Created by Administrator on 2017/11/28.
 */

public class Demo2ViewHolder extends TypeListFragment.TypeViewHolder<Demo2> {
    private static final int layout_res = R.layout.demo_txt;

    public Demo2ViewHolder(ViewGroup parent) {
        super(parent, layout_res);

    }

    public Demo2ViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(parent, layoutRes);
    }

    @Override
    public void setData(Demo2 demo1) {
        ((TextView) itemView).setText(demo1.getTitle());
    }
}