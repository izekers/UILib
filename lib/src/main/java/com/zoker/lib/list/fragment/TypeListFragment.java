package com.zoker.lib.list.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.zoker.lib.list.interfaces.ITypesListFragment;

import java.util.List;

/**
 *
 * Created by zoker on 2017/11/28.
 */

public abstract class TypeListFragment extends Fragment implements ITypesListFragment<TypeListFragment.TypeViewHolder> {
    private RecyclerView rootView;
    private TypeAdapter adapter;
    private List<Object> mData;
    protected abstract RecyclerView.LayoutManager installLayoutManager();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = new RecyclerView(getContext());
        rootView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rootView.setLayoutManager(installLayoutManager());
        rootView.setAdapter(adapter = new TypeAdapter());
    }

    public class TypeAdapter extends RecyclerView.Adapter<TypeViewHolder> {
        @Override
        public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return TypeListFragment.this.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(TypeViewHolder holder, int position) {
            TypeListFragment.this.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return TypeListFragment.this.getItemCount();
        }

        @Override
        public int getItemViewType(int position) {
            return TypeListFragment.this.getItemViewType(position);
        }
    }

    @Override
    public void setData(List<Object> mdata) {
        clear();
        this.mData = mdata;
        notifyDataSetChanged();
    }

    @Override
    public void addData(List<Object> mdata) {
        if (this.mData == null)
            this.mData = mdata;
        else
            this.mData.addAll(mdata);
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
    public Object getItemData(int position) {
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

    @Override
    public void onBindViewHolder(TypeViewHolder holder, int position) {
        if (checkData(position))
            holder.setData(getItemData(position));
    }

    public boolean checkData(int position){
        return true;
    }

    public RecyclerView getRootView() {
        return rootView;
    }

    public TypeAdapter getAdapter() {
        return adapter;
    }

    public List<Object> getmData() {
        return mData;
    }

    public static abstract class TypeViewHolder<data> extends RecyclerView.ViewHolder {

        public TypeViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
            super(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));

        }

        public TypeViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void setData(data data);
    }
}
