package com.zoker.uilib.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zoker.lib.fragment.list.SimpleSingleListFragment;
import com.zoker.lib.fragment.list.TypeListFragment;
import com.zoker.uilib.R;
import com.zoker.uilib.bean.Demo2;
import com.zoker.uilib.bean.Demo3;

/**
 *
 * Created by Administrator on 2017/11/28.
 */

public class Demo3ViewHolder extends SimpleSingleListFragment.SimpleViewHolder<Demo3> {
    private static final int layout_res = R.layout.demo_txt;

    public Demo3ViewHolder(ViewGroup parent) {
        super(parent, layout_res);

    }

    public Demo3ViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(parent, layoutRes);
    }

    @Override
    public void setData(Demo3 demo3) {
        ((TextView) itemView).setText(demo3.getTitle());
    }
}
