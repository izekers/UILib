package com.zoker.lib.image.loader;

import android.app.Activity;
import android.content.Context;

import com.zoker.lib.image.Multiimagechooser.Utils.ImageLoaderConfig;
import com.zoker.lib.image.util.ScreenUtil;


/**
 * Created by Administrator on 2017/9/1.
 */

public class PhotoTrans {
    private static ImageLoaderConfig mImageConfig;

    /**
     * Initialize ImageLoaderManager.
     */
    public static void initialize(ImageLoaderConfig imageConfig) {
        if (mImageConfig == null)
            mImageConfig = imageConfig;
    }

    public static PhotoAlbum album(Context context){
        ScreenUtil.init(context);
        return new PhotoAlbum(context);
    }

    public static PhotoCrop crop(Activity activity){
        return new PhotoCrop(activity);
    }
    public static PictureLoader loader(Context context){
        return new PictureLoader(context);
    }
    public static PhotoCamera camera(Context context){return new PhotoCamera(context);}

    static ImageLoaderConfig getmImageConfig() {
        return mImageConfig;
    }
}
