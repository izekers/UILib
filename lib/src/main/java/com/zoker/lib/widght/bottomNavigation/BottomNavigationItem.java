package com.zoker.lib.widght.bottomNavigation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;

import com.zoker.lib.util.SelectorUtils;

/**
 * Created by Administrator on 2017/10/19.
 */

public class BottomNavigationItem {
    private Fragment fragment;
    private String title;
    private int normal_icon;
    private int select_icon;


    public Fragment getFragment() {
        return fragment;
    }
    public String getTitle() {
        return title;
    }

    public int getNormal_icon() {
        return normal_icon;
    }

    public void setNormal_icon(int normal_icon) {
        this.normal_icon = normal_icon;
    }

    public int getSelect_icon() {
        return select_icon;
    }

    public void setSelect_icon(int select_icon) {
        this.select_icon = select_icon;
    }

    Drawable getIcon(Context context){

        StateListDrawable bg = new StateListDrawable();
        bg.addState(new int[] { android.R.attr.state_selected },
                select_icon == -1 ? null : context.getResources().getDrawable(select_icon));
        bg.addState(new int[] {},
                normal_icon == -1 ? null : context.getResources().getDrawable(normal_icon));
        return bg;
    }

    public BottomNavigationItem fragment(Fragment fragment) {
        this.fragment = fragment;
        return this;
    }

    public BottomNavigationItem title(String title) {
        this.title = title;
        return this;
    }


    public BottomNavigationItem icon(@DrawableRes int normal_icon,@DrawableRes int select_icon){
        this.normal_icon=normal_icon;
        this.select_icon=select_icon;
        return this;
    }

}
