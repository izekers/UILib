package com.zoker.lib.image.camera;

import android.hardware.Camera;

/**
 * Created by Administrator on 2017/10/11.
 */

public class ZOKCamera implements Camera.PreviewCallback {
    private Camera camera ;
    private int cameraNumber;
    public void init(){
        cameraNumber =Camera.getNumberOfCameras();

        if (cameraNumber!=0) {
            camera = openCamera(0);
        }
        camera.startPreview();
        camera.setPreviewCallback(this);
    }

    public static Camera openCamera(int cameraId){
        try{
            return Camera.open(cameraId);
        }catch (Exception e){
            return null;
        }
    }

    public int getCameraNumber(){
        return cameraNumber;
    }

    public class Parameters{
        Parameters(Camera camera){

        }


    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

    }
}
