package com.zoker.lib.image.Multiimagechooser.Utils;

import android.net.Uri;
import android.widget.ImageView;


/**
 * Created by Administrator on 2017/9/1.
 */

public class GlideImageLoader implements IImageLoader {
    @Override
    public void loadImage(ImageView imageView, String imagePath) {
//        Glide.with(imageView.getContext())
//                .load(imagePath)
//                .into(imageView);
    }

    @Override
    public void loadImage(ImageView imageView, Uri uri) {
//        Glide.with(imageView.getContext())
//                .load(uri)
//                .into(imageView);
    }

    @Override
    public void loadImage(ImageView imageView, String imagePath, int viewWidth, int viewHeight) {
//        Glide.with(imageView.getContext())
//                .load(imagePath)
//                .into(imageView);
    }
}
