package com.zoker.uilib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zoker.lib.image.gallery.GalleryModel;
import com.zoker.lib.image.gallery.GalleryView;
import com.zoker.lib.image.largeimage.LargeImageView;
import com.zoker.lib.image.largeimage.factory.InputStreamBitmapDecoderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LargeImageActivity extends AppCompatActivity {
    GalleryView galleryview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image);
        galleryview = (GalleryView) findViewById(R.id.galleryview);
        List<GalleryModel> galleryModels =new ArrayList<>();
        galleryModels.add(GalleryModel.createAssestModel("testimg.jpg"));
        galleryModels.add(GalleryModel.createAssestModel("testimg2.jpg"));
        galleryModels.add(GalleryModel.createAssestModel("testimg3.jpg"));
        galleryview.setData(galleryModels);
    }
}
