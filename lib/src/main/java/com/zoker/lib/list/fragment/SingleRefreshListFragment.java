package com.zoker.lib.list.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zoker.lib.list.interfaces.IListFragment;
import com.zoker.lib.list.interfaces.IRefreshListFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */

public abstract class SingleRefreshListFragment<Data, VH extends RecyclerView.ViewHolder> extends Fragment implements IListFragment<Data,VH>,IRefreshListFragment<Data, VH> {
    private PageDataProvider<List<Data>> provider;
    private int firstPageIndex = 1;
    private int pageCount = 20;
    private Adapter adapter;
    private List<Data> mData;

    public void setFirstPageIndex(int firstPageIndex) {
        this.firstPageIndex = firstPageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public void onDown() {
        provider.setCurrentPage(firstPageIndex - 1);
        provider.setPageCount(pageCount);
        provider.next();
    }

    public abstract void onDownUI();
    public abstract void onUpUI();

    @Override
    public void onUp() {
        if (!provider.next())
            noMoreShow();
    }

    public abstract List<Data> sourse(int page);

    public void onData(int page, List<Data> data){
        if (page <= 1) {
            clear();
        }
        addData(data);
        adapter.notifyDataSetChanged();
//        mListView.onRefreshComplete();
    }


    public void onError(String msg) {

    }

    @Override
    public void noMoreShow() {

    }

    public class PageDataProvid extends PageDataProvider<List<Data>> {

        @Override
        public List<Data> sourse(int page) {
            return SingleRefreshListFragment.this.sourse(page);
        }

        @Override
        public void onError(String msg) {
            SingleRefreshListFragment.this.onError(msg);
        }

        @Override
        public void onData(int page, List<Data> data) {
            SingleRefreshListFragment.this.onData(page, data);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private class Adapter extends RecyclerView.Adapter<VH> {
        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return SingleRefreshListFragment.this.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            SingleRefreshListFragment.this.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return SingleRefreshListFragment.this.getItemCount();
        }
    }

    @Override
    public void addData(List mdata) {
        if (this.mData == null)
            this.mData = mdata;
        else
            this.mData.addAll(mdata);
        notifyDataSetChanged();
    }

    @Override
    public void setData(List<Data> mdata) {
        clear();
        this.mData = mdata;
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        if (this.mData!=null)
            this.mData.clear();
        notifyDataSetChanged();
    }

    private void notifyDataSetChanged(){
        if (adapter!=null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public Data getItemData(int position) {
        if (mData == null)
            return null;
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }
}
