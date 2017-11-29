package com.zoker.lib.fragment.interfaces;

import android.support.v7.widget.RecyclerView;

import java.util.Date;

/**
 * Created by zoker on 2017/11/28.
 */

public interface IRefreshListFragment<Data,VH extends RecyclerView.ViewHolder>{
    void setRefresh(boolean isRefresh);
    void onDown();
    void onUp();
    void noMoreShow();
}
