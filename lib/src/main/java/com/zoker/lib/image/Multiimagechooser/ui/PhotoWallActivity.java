package com.zoker.lib.image.Multiimagechooser.ui;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.zoker.lib.R;
import com.zoker.lib.image.Multiimagechooser.adapter.SelectImageAdapter;
import com.zoker.lib.image.Multiimagechooser.listener.OnImageSelectedListenner;
import com.zoker.lib.image.Multiimagechooser.model.ImageModel;
import com.zoker.lib.image.loader.PhotoTrans;
import com.zoker.lib.image.util.PhotoUtil;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
import static android.provider.MediaStore.MediaColumns.DATA;
import static android.provider.MediaStore.MediaColumns.MIME_TYPE;

public class PhotoWallActivity extends AppCompatActivity {

    private final int REQUEST_CODE
            = 1;

    private static OnImageSelectedListenner listenner = null;

    public static void setOnImageSelectedListner(OnImageSelectedListenner listenner) {
        PhotoWallActivity.listenner = listenner;
    }


    private Toolbar toolbar;
    private GridView gridView;
    private SelectImageAdapter adapter;
    private List<ImageModel> imageList = new ArrayList<>();
    private List<ImageModel> originalImageList = new ArrayList<>();

    private int columnCount = 1;
    private int requestCode = -1;
    private boolean isCrop = false;

    private float aspectWidth = -1;
    private float aspectHeight = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_wall);
        initData();
    }

    private void initData() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        gridView = (GridView) findViewById(R.id.image_grid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getData();
//        }
        getSupportActionBar().setTitle("选择图片(" + adapter.checkedList.size() + "/" + columnCount + ")");
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(adapter);
        if (getIntent() != null) {
            columnCount = getIntent().getIntExtra("columnCount", 1);
            requestCode = getIntent().getIntExtra("requestCode", -1);
            isCrop = getIntent().getBooleanExtra("isCrop", false);
            aspectWidth = getIntent().getFloatExtra("aspectWidth", -1);
            aspectHeight = getIntent().getFloatExtra("aspectHeight", -1);
        }

        adapter.setMaxNumber(columnCount);
    }

    /*
      *  Get images from System Storage by Content Provider
      */
    private void getData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //动态申请权限
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //进入到这里代表没有权限.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            } else {
                getImgData();
            }
        } else {
            getImgData();
        }
    }

    public void getImgData() {
        adapter = (SelectImageAdapter) new SelectImageAdapter(this).setList(imageList);
        Intent intent = getIntent();
        if (getIntent() != null) {
            if (intent.getSerializableExtra("selectedImage") != null)
                adapter.setCheckedList((List<ImageModel>) intent.getSerializableExtra("selectedImage"));
        }
        if (adapter.checkedList == null)
            adapter.setCheckedList(null);

        final ContentResolver cr = getContentResolver();
        final String selection = "((" + MIME_TYPE + "=?)or(" + MIME_TYPE + "=?))";
        final String[] selectionArgs = new String[]{"image/jpeg", "image/png"};
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = cr.query(EXTERNAL_CONTENT_URI, null,
                        selection, selectionArgs, null);
                while (cursor != null && cursor.moveToNext()) {
                    String path = cursor.getString(
                            cursor.getColumnIndex(DATA));
                    ImageModel imageBean = new ImageModel().setImageUri(
                            Uri.fromFile(new File(path)).toString()).setID(imageList.size());
                    imageList.add(imageBean);
                }
                for (ImageModel imageBean : adapter.checkedList) {
                    imageBean.setIsChecked(true);
                    originalImageList.add(imageBean);
                    imageList.set(imageBean.getID(), imageBean);
                    handler.sendEmptyMessage(0);
                    gridView.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意授权
                    getData();
                } else {
                    //用户拒绝授权
                }
                break;
        }
    }

    /*
         * update UI
         */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
            return true;
        }
    });

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_done, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * 选择照片，点击完成事件
     */
    public void onDone(MenuItem item) {
        if (isCrop) {
            ArrayList<String> imageList = new ArrayList<>();
            for (ImageModel imageBean : adapter.checkedList) {
                imageList.add(PhotoUtil.getRealFilePath(this, Uri.parse(imageBean.getImageUri())));
            }
            PhotoTrans.crop(this)
                    .title("裁剪图片")
                    .inputImagePaths(imageList)
                    .aspectRatio(aspectWidth, aspectHeight)
                    .requestCode(requestCode)
                    .start();

        } else {
            if (listenner != null)
                listenner.OnImageSelected(requestCode, adapter.checkedList);

            Intent intent = new Intent();
            intent.putExtra("selectedImage", (Serializable) adapter.checkedList);
            setResult(1, intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
        if (resultCode == RESULT_OK) {
            if (requestCode == this.requestCode) {
                ArrayList<String> imagePaths = Durban.parseResult(data);
                ArrayList<ImageModel> imageBeen = new ArrayList<>();
                for (String path : imagePaths) {
                    ImageModel imageBean = new ImageModel();
                    imageBean.setImageUri(path);
                    imageBeen.add(imageBean);
                }
                if (listenner != null) {
                    listenner.OnImageSelected(this.requestCode, imageBeen);
                }

                Intent intent = new Intent();
                intent.putExtra("selectedImage", imageBeen);
                setResult(1, intent);
                finish();
            }
        }
         **/
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("selectedImage", (Serializable) originalImageList);
        setResult(1, intent);
        super.onBackPressed();
    }
}
