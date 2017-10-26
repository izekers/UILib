package com.zoker.lib.image.loader;

import android.content.Context;
import android.content.Intent;

import com.zoker.lib.image.Multiimagechooser.listener.OnImageSelectedListenner;
import com.zoker.lib.image.Multiimagechooser.model.ImageModel;
import com.zoker.lib.image.Multiimagechooser.ui.PhotoWallActivity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class PhotoAlbum {
    private Context context;

    private List<ImageModel> selectedImage;
    private int columnCount = 1;
    private int requestCode = -1;
    private boolean isCrop = false;
    private float aspectWidth=-1,aspectHeight=-1;
    private OnImageSelectedListenner listenner;

    PhotoAlbum(Context context) {
        PhotoWallActivity.setOnImageSelectedListner(null);
        this.context = context;
    }

    public PhotoAlbum selectImageList(List<ImageModel> selectedImage) {
        this.selectedImage = selectedImage;
        return this;
    }

    public PhotoAlbum aspectRatio(float x,float y){
        this.aspectWidth = x;
        this.aspectHeight = y;
        return this;
    }

    public PhotoAlbum columnCount(int columnCount) {
        this.columnCount = columnCount;
        return this;
    }

    public PhotoAlbum requestCode(int requestCode){
        this.requestCode = requestCode;
        return this;
    }

    public PhotoAlbum listener(OnImageSelectedListenner listenner) {
        this.listenner = listenner;
        return this;
    }

    public PhotoAlbum crop(boolean isCrop){
        this.isCrop = isCrop;
        return this;
    }
    public void start() {
        PhotoWallActivity.setOnImageSelectedListner(listenner);
        Intent intent = new Intent();
        intent.setClass(context, PhotoWallActivity.class);
        intent.putExtra("selectedImage", (Serializable) selectedImage);
        intent.putExtra("columnCount", columnCount);
        intent.putExtra("requestCode", requestCode);
        intent.putExtra("isCrop",isCrop);
        intent.putExtra("aspectWidth",aspectWidth);
        intent.putExtra("aspectHeight",aspectHeight);
        context.startActivity(intent);
    }
}
