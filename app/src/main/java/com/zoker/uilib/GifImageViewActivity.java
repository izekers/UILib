package com.zoker.uilib;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zoker.lib.image.gifImage.GifImageView;

import java.util.ArrayList;

public class GifImageViewActivity extends AppCompatActivity {
    GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_image_view);
        gifImageView = (GifImageView)findViewById(R.id.imageview);
        ArrayList<Drawable> drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.drawable.nor_icon));
        drawables.add(getResources().getDrawable(R.drawable.placeholder));
        drawables.add(getResources().getDrawable(R.drawable.popmenu_border));
        drawables.add(getResources().getDrawable(R.drawable.programnews_itemb_default));
        gifImageView.startFrameGif(drawables,300);
    }
}
