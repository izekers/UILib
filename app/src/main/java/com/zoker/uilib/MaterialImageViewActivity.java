package com.zoker.uilib;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zoker.lib.image.MaterialImageView;

/**
 * Created by Administrator on 2017/9/29.
 */

public class MaterialImageViewActivity extends AppCompatActivity {
    private RadioGroup modeGroup;
    private RadioButton modeNormalView;
    private RadioButton modeCircleView;
    private RadioButton modeRoundedCornersView;
    private MaterialImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materail_image_view);
        imageView = (MaterialImageView) findViewById(R.id.img);
        modeGroup = (RadioGroup) findViewById(R.id.radiogroup_mode);
        modeNormalView = (RadioButton) findViewById(R.id.radio_normal);
        modeCircleView = (RadioButton) findViewById(R.id.radio_circle);
        modeRoundedCornersView = (RadioButton) findViewById(R.id.radio_rounded_corners);
        modeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_circle:
                        imageView.setMode(MaterialImageView.MODE_CIRCLE);
                        break;
                    case R.id.radio_normal:
                        imageView.setMode(MaterialImageView.MODE_NORMAL);
                        break;
                    case R.id.radio_rounded_corners:
                        imageView.setMode(MaterialImageView.MODE_ROUNDED_CORNERS);
                        break;
                }
            }
        });
    }
}
