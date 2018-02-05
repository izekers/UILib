package com.zoker.lib.list.recycle.widght;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/11/1.
 */
public abstract class BaseView extends FrameLayout {
    private View rootView;

    public BaseView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public abstract int getLayoutID();

    public abstract void initView();

    public abstract void initAttribut(AttributeSet attrs);

    public void init(AttributeSet attrs) {
        if (getLayoutID() != 0)
            rootView = LayoutInflater.from(getContext()).inflate(getLayoutID(), this, false);
        if (rootView != null) {
            this.addView(rootView);
            initView();
            initAttribut(attrs);
        }
    }

    public View getContentView() {
        return rootView;
    }
}
