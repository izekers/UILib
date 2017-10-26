package com.zoker.lib.image.loader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/9/1.
 */

public class PictureLoader {
    private Context context;
    private ImageView imageView;
    private String path;
    private Uri uri;

    PictureLoader(Context context){
        this.context = context;
    }

    public PictureLoader load(String path){
        this.path = path;
        this.uri = null;
        return this;
    }

    public PictureLoader load(Uri uri){
        this.uri = uri;
        this.path =null;
        return this;
    }

    public void into(ImageView view){
        if (this.path==null)
            PhotoTrans.getmImageConfig().getImageLoader().loadImage(view,uri);
        else if (this.uri == null)
            PhotoTrans.getmImageConfig().getImageLoader().loadImage(view,path);
    };
}
