package com.zoker.lib.list.recycle.interfaces;

import java.util.List;

/**
 * Created by zoker on 2018/1/17.
 */

public interface IListData<Data> {
    void addData(List<Data> mdata);

    List<Data> getData();

    void setData(List<Data> mdata);

    void clear();

    Data getItemData(int position);

    int getItemCount();

}
