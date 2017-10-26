package com.zoker.lib.image.Multiimagechooser.Utils;

import android.content.Context;

/**
 * Created by Administrator on 2017/9/1.
 */

public class ImageLoaderConfig {
    IImageLoader mLoader;
    public ImageLoaderConfig(Builder builder){
        this.mLoader = builder.mLoader == null ?
                DefaultImageLoader.getInstance() : builder.mLoader;
    }

    public IImageLoader getImageLoader() {
        return mLoader;
    }

    public static Builder newBuilder(Context context){
        return new Builder(context);
    }
    public static final class Builder {

        private IImageLoader mLoader;

        private Builder(Context context) {
        }

        public Builder setImageLoader(IImageLoader loader) {
            this.mLoader = loader;
            return this;
        }

        public ImageLoaderConfig build() {
            return new ImageLoaderConfig(this);
        }
    }
}
