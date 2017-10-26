package com.zoker.lib.image.Multiimagechooser.Utils;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;

/**
 * Created by Zoker on 2017/9/1.
 */
public class DefaultImageLoader implements IImageLoader {
    public static DefaultImageLoader mInstance;

    @Override
    public void loadImage(ImageView imageView, String imagePath) {
        if (URLUtil.isNetworkUrl(imagePath)) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
        }
    }

    @Override
    public void loadImage(ImageView imageView, Uri uri) {
        Log.e(DefaultImageLoader.class.getSimpleName(),"#uri默认加载尚未完成,影响一部分功能");
    }

    @Override
    public void loadImage(ImageView imageView, String imagePath, int viewWidth, int viewHeight) {

    }

    public static DefaultImageLoader getInstance() {
        if (mInstance == null)
            synchronized (DefaultImageLoader.class) {
                if (mInstance == null)
                    mInstance = new DefaultImageLoader();
            }
        return mInstance;
    }
}
