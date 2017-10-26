package com.zoker.lib.image.Multiimagechooser.Utils;

import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/9/1.
 */

public interface IImageLoader {
    /**
     * Load a preview of the album file.
     * @param imageView {@link ImageView}.
     */
    void loadImage(ImageView imageView, String imagePath);

    /**
     * Load a preview of the album file.
     * @param imageView {@link ImageView}.
     */
    void loadImage(ImageView imageView, Uri uri);

    /**
     * Load a preview of the picture.
     *
     * @param imageView  {@link ImageView}.
     * @param imagePath  file path, which may be a local path or a network path.
     * @param viewWidth  the width fo target view.
     * @param viewHeight the width fo target view.
     */
    void loadImage(ImageView imageView, String imagePath, int viewWidth, int viewHeight);
}
