package com.zoker.lib.list.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by zoker on 2017/11/28.
 */

public interface IRefreshListFragment<Data,VH extends RecyclerView.ViewHolder>{
    void setRefresh(boolean isRefresh);
    void onDown();
    void onUp();
    void noMoreShow();
}
