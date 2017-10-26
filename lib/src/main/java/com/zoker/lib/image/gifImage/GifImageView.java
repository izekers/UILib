package com.zoker.lib.image.gifImage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.zoker.lib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 可以播放gif的ImageView
 * Created by Zoker on 2017/10/26.
 */

public class GifImageView extends android.support.v7.widget.AppCompatImageView {

    public GifImageView(Context context) {
        super(context);
    }

    public GifImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public GifImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attri) {
        if (attri != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attri, R.styleable.GifImageView);

            typedArray.recycle();
        }
    }

    public void startFrameGif(List<Drawable> drawables, int everyDuration) {
        startFrameGif(drawables, everyDuration, false);
    }

    public void startFrameGif(List<Drawable> drawables, int everyDuration, boolean isOnShot) {
        AnimationDrawable drawable = createAnimationDrawable(drawables, everyDuration);
        drawable.setOneShot(isOnShot);
        this.setImageDrawable(drawable);
        drawable.start();
    }

    private AnimationDrawable createAnimationDrawable(List<Drawable> drawables, int duration) {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        for (Drawable drawable : drawables) {
            animationDrawable.addFrame(drawable, duration);
        }
        return animationDrawable;
    }
}
