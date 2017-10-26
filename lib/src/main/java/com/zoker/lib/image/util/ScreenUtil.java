package com.zoker.lib.image.util;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

/**
 * Created by mummyding on 15-11-3.
 */
public class ScreenUtil {
    private static int ScreenWidth = 0;
    private static int ScreenHeight = 0;
    private static Context mContext = null;

    public static void init(Context context) {
        mContext = context;
    }

    public static int getScreenWidth() {
        if (ScreenWidth != 0) return ScreenWidth;
        Display display = ((AppCompatActivity) mContext).getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        ScreenWidth = point.x;
        return ScreenWidth;
//        return DensityUtil2.getScreenWidth(mContext);
    }

    public static int getScreenHeight() {
        if (ScreenHeight != 0) return ScreenHeight;
        Display display = ((AppCompatActivity) mContext).getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        ScreenHeight = point.y;
        return ScreenHeight;
//        return DensityUtil2.getScreenHeight(mContext);
    }
}
