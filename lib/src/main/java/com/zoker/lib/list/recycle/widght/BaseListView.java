package com.zoker.lib.list.recycle.widght;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.zoker.lib.list.recycle.interfaces.IListData;

import java.util.List;

/**
 * Created by zoker on 2018/1/17.
 */

public abstract class BaseListView<Data> extends BaseView implements IListData<Data> {
    public BaseListView(@NonNull Context context) {
        super(context);
    }

    public BaseListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

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
