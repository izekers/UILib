package com.zoker.lib.image.loader;

import android.content.Context;
import android.content.Intent;

import com.zoker.lib.image.Multiimagechooser.listener.OnImageSelectedListenner;
import com.zoker.lib.image.Multiimagechooser.ui.PhotoCameraActivity;

/**
 * Created by Administrator on 2017/9/4.
 */

public class PhotoCamera {
    private Context context;
    private int requestCode = -1;
    private float aspectWidth=-1,aspectHeight=-1;
    private boolean isCrop = false;

    private OnImageSelectedListenner listenner;

    PhotoCamera(Context context){
        PhotoCameraActivity.setOnImageSelectedListner(null);
        this.context =context;
    }


    public PhotoCamera requestCode(int requestCode){
        this.requestCode = requestCode;
        return this;
    }

    public PhotoCamera aspectRatio(float x,float y){
        this.aspectWidth = x;
        this.aspectHeight = y;
        return this;
    }

    public PhotoCamera listener(OnImageSelectedListenner listenner) {
        this.listenner = listenner;
        return this;
    }


    public PhotoCamera crop(boolean isCrop){
        this.isCrop = isCrop;
        return this;
    }

    public PhotoCamera start(){
        PhotoCameraActivity.setOnImageSelectedListner(listenner);
        Intent intent = new Intent();
        intent.setClass(context, PhotoCameraActivity.class);
        intent.putExtra("requestCode", requestCode);
        intent.putExtra("aspectWidth",aspectWidth);
        intent.putExtra("aspectHeight",aspectHeight);
        intent.putExtra("isCrop",isCrop);
        context.startActivity(intent);
        return this;
    }
}
