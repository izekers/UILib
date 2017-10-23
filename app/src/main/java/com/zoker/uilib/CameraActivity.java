package com.zoker.uilib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.zoker.lib.image.camera.ZOKCameraView;
import com.zoker.lib.image.camera.interfaces.CameraInterface;
import com.zoker.lib.util.DisplayUtil;
import com.zoker.lib.util.FileUtil;
import com.zoker.lib.util.ImageUtil;

/**
 * Created by Administrator on 2017/10/11.
 */

public class CameraActivity extends AppCompatActivity implements CameraInterface.CamOpenOverCallback {
    private static final String TAG = "CameraActivity";
    ZOKCameraView surfaceView = null;
    View shutterBtn;
    float previewRate = -1f;
    ImageView img,img2;
    LinearLayout rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initUI();
        initViewParams();

        Thread openThread = new Thread() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                CameraInterface.getInstance().doOpenCamera(CameraActivity.this);
            }
        };
        openThread.start();

        shutterBtn.setOnClickListener(new BtnListeners());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.camera, menu);
        return true;
    }

    private void initUI() {
        surfaceView = (ZOKCameraView) findViewById(R.id.camera_surfaceview);
        shutterBtn =  findViewById(R.id.btn_shutter);
        img = (ImageView) findViewById(R.id.img);
        img2 = (ImageView) findViewById(R.id.img2);
        rootView =(LinearLayout) findViewById(R.id.rootView);
    }

    private void initViewParams() {
//        ViewGroup.LayoutParams params = surfaceView.getLayoutParams();
//        Point p = DisplayUtil.getScreenMetrics(this);
//        params.width = p.x;
//        params.height = p.y;
//        previewRate = DisplayUtil.getScreenRate(this); //默认全屏的比例预览
//        surfaceView.setLayoutParams(params);

        //手动设置拍照ImageButton的大小为120dip×120dip,原图片大小是64×64
        ViewGroup.LayoutParams p2 = shutterBtn.getLayoutParams();
        p2.width = DisplayUtil.dip2px(this, 80);
        p2.height = DisplayUtil.dip2px(this, 80);
        ;
        shutterBtn.setLayoutParams(p2);

    }

    @Override
    public void cameraHasOpened() {
        // TODO Auto-generated method stub
        SurfaceHolder holder = surfaceView.getSurfaceHolder();
        CameraInterface.getInstance().doStartPreview(holder, previewRate);
    }

    private class BtnListeners implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.btn_shutter:
                    Bitmap bitmap = createViewBitmap(img2);
                    img.setImageBitmap(bitmap);
                    FileUtil.saveBitmap(bitmap);
//                    CameraInterface.getInstance().doStopCamera();
//                    Thread openThread = new Thread() {
//                        @Override
//                        public void run() {
//                            // TODO Auto-generated method stub
//                            CameraInterface.getSecondInstance().doOpenCamera(new CameraInterface.CamOpenOverCallback() {
//                                @Override
//                                public void cameraHasOpened() {
//                                    SurfaceHolder holder = surfaceView2.getSurfaceHolder();
//                                    CameraInterface.getSecondInstance().doStartPreview(holder, previewRate);
//                                    CameraInterface.getSecondInstance().doTakePicture();
//                                    CameraInterface.getSecondInstance().doStopCamera();
//
//                                    Thread openThread = new Thread() {
//                                        @Override
//                                        public void run() {
//                                            // TODO Auto-generated method stub
//                                            CameraInterface.getInstance().doOpenCamera(CameraActivity.this);
//                                        }
//                                    };
//                                    openThread.start();
//                                }
//                            });
//                        }
//                    };
//                    openThread.start();
                    break;
                default:
                    break;
            }
        }

    }

    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }
}
