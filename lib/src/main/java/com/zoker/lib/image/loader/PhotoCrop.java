package com.zoker.lib.image.loader;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/4.
 */

public class PhotoCrop {
    private Activity activity;
    private String title = null;
    private ArrayList<String> inputImagePaths = null;
    private float aspectWidth = -1, aspectHeight = -1;
    private int requestCode;

    PhotoCrop(Activity activity) {
        this.activity = activity;
    }

    public PhotoCrop title(String title) {
        this.title = title;
        return this;
    }

    public PhotoCrop inputImagePaths(ArrayList<String> imagePaths) {
        this.inputImagePaths = imagePaths;
        return this;
    }

    public PhotoCrop aspectRatio(float x, float y) {
        this.aspectWidth = x;
        this.aspectHeight = y;
        return this;
    }

    public PhotoCrop requestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    public void start() {
        /**
         Durban.with(activity)
         .title("裁剪图片")
         .statusBarColor(activity.getResources().getColor(R.color.main))
         .toolBarColor(activity.getResources().getColor(R.color.main))
         .navigationBarColor(activity.getResources().getColor(R.color.main))
         .inputImagePaths(inputImagePaths)
         .aspectRatio(aspectWidth,aspectHeight)
         // 裁剪时的手势支持：ROTATE, SCALE, ALL, NONE.
         .gesture(Durban.GESTURE_ALL)
         .controller(
         Controller.newBuilder()
         .enable(false) // 是否开启控制面板。
         .rotation(true) // 是否有旋转按钮。
         .rotationTitle(true) // 旋转控制按钮上面的标题。
         .scale(true) // 是否有缩放按钮。
         .scaleTitle(true) // 缩放控制按钮上面的标题。
         .build()) // 创建控制面板配置。
         .requestCode(requestCode)
         .start();
         */
    }
}
