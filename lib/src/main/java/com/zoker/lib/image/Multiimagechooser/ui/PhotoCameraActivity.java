package com.zoker.lib.image.Multiimagechooser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.zoker.lib.image.Multiimagechooser.listener.OnImageSelectedListenner;
import com.zoker.lib.image.Multiimagechooser.model.ImageModel;
import com.zoker.lib.image.loader.PhotoTrans;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/4.
 */

public class PhotoCameraActivity extends AppCompatActivity {
    private static OnImageSelectedListenner listenner = null;

    private int requestCode = -1;
    private boolean isCrop = false;

    private float aspectWidth = -1;
    private float aspectHeight = -1;

    public static void setOnImageSelectedListner(OnImageSelectedListenner listenner) {
        PhotoCameraActivity.listenner = listenner;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getIntent() != null) {
            requestCode = getIntent().getIntExtra("requestCode", -1);
            isCrop = getIntent().getBooleanExtra("isCrop", false);
            aspectWidth = getIntent().getFloatExtra("aspectWidth", -1);
            aspectHeight = getIntent().getFloatExtra("aspectHeight", -1);
        }
        // TODO: 2017/10/25 添加相机功能 
        /**
        Album.camera(this) // 相机功能。
                .image() // 拍照。
                // .filePath() // 文件保存路径，非必须。
                .requestCode(3)
                .listener(new AlbumListener<String>() {
                    @Override
                    public void onAlbumResult(int requestCode, @NonNull String result) {
                        if (requestCode == 3) {
                            if (!TextUtils.isEmpty(result)) {
                                if (isCrop) {
                                    ArrayList<String> imageList = new ArrayList<>();
                                    imageList.add(result);
                                    PhotoTrans.crop(PhotoCameraActivity.this)
                                            .title("裁剪图片")
                                            .inputImagePaths(imageList)
                                            .aspectRatio(aspectWidth, aspectHeight)
                                            .requestCode(PhotoCameraActivity.this.requestCode)
                                            .start();

                                } else {
                                    setResult(result);
                                }
                            }
                        }
                    }

                    @Override
                    public void onAlbumCancel(int requestCode) {
                        Log.d("camera", "onAlbumCancel: requestCode" + requestCode);
                        finish();//解决 进入相机，取消拍照会返回空白页面
                    }
                }).start();
         **/
    }

    public void setResult(String result) {
        ArrayList<ImageModel> imageBeen = new ArrayList<ImageModel>();
        ImageModel imageBean = new ImageModel();
        imageBean.setImageUri(result);
        imageBeen.add(imageBean);
        if (listenner != null)
            listenner.OnImageSelected(requestCode, imageBeen);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO: 2017/10/25 相机功能
        /**
        if (resultCode == RESULT_OK) {
            if (requestCode == this.requestCode) {
                ArrayList<String> imagePaths = Durban.parseResult(data);
                ArrayList<ImageBean> imageBeen = new ArrayList<>();
                for (String path : imagePaths) {
                    ImageBean imageBean = new ImageBean();
                    imageBean.setImageUri(path);
                    imageBeen.add(imageBean);
                }
                if (listenner != null) {
                    listenner.OnImageSelected(this.requestCode, imageBeen);
                }
            }
        }
        finish();
         **/
    }
}
