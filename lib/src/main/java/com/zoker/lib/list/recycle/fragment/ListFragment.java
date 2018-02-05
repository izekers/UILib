package com.zoker.lib.list.recycle.fragment;

import android.support.v4.app.Fragment;

import com.zoker.lib.list.recycle.interfaces.IListData;

import java.util.List;

/**
 * Created by zoker on 2018/1/16.
 */

public abstract class ListFragment<Data> extends Fragment implements IListData<Data> {
    private List<Data> mData;

    public void addData(List<Data> mdata) {
        if (this.mData == null)
            this.mData = mdata;
        else
            this.mData.addAll(mdata);
    }

    public List<Data> getData() {
        return mData;
    }

    public void setData(List<Data> mdata) {
        clear();
        this.mData = mdata;
    }

    public void clear() {
        if (this.mData != null)
            this.mData.clear();
    }

    public Data getItemData(int position) {
        if (mData == null)
            return null;
        return mData.get(position);
    }

    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }
}
