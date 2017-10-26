package com.zoker.lib.image.Multiimagechooser.adapter;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zoker.lib.image.util.ScreenUtil;
import com.zoker.lib.image.Multiimagechooser.model.ImageModel;

import java.util.List;

/**
 * Created by mummyding on 15-11-3.
 */
public abstract class BasicAdapter extends BaseAdapter {
    protected List<ImageModel> mList;
    protected Context mContext;
    protected static int imageWidth;
    protected static LinearLayout.LayoutParams linearParams;
    protected static FrameLayout.LayoutParams frameParams;

    static {
        imageWidth = ScreenUtil.getScreenWidth() / 3;
        linearParams = new LinearLayout.LayoutParams(imageWidth, imageWidth);
        frameParams = new FrameLayout.LayoutParams(imageWidth, imageWidth);
    }

    public BasicAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BaseAdapter setList(List<ImageModel> list) {
        this.mList = list;
        return this;
    }

    public BaseAdapter updateUI() {
        this.notifyDataSetChanged();
        return this;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<ImageModel> getList() {
        return mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).getID();
    }
}
