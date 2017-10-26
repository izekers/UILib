package com.zoker.lib.image.gallery;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by Administrator on 2017/10/25.
 */

public class GalleryModel {
    String source;
    int dataType = SOURCE_FORM_URL;
    Bitmap bitmap;
    public static final int SOURCE_FORM_BITMAP = 2;
    public static final int SOURCE_FORM_URL = 1;
    public static final int SOURCE_FORM_ASSEST = 3;

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public static GalleryModel createAssestModel(String fileName) {
        GalleryModel galleryModel = new GalleryModel();
        galleryModel.setSource(fileName);
        galleryModel.setDataType(SOURCE_FORM_ASSEST);
        return galleryModel;
    }
}
