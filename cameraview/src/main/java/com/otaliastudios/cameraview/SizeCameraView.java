package com.otaliastudios.cameraview;

import android.content.Context;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by v-banko on 11.01.18.
 */

public class SizeCameraView extends CameraView{

    private int videoHeight;
    private int videoWidth;
    private int photoHeight;
    private int photoWidth;


    public SizeCameraView(@NonNull Context context) {
        super(context);
    }

    public SizeCameraView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraController getCameraController(){
        return mCameraController;
    }


    public List<Camera.Size> getSupportedVideoSizes(){
        Camera camera = ((Camera1) getCameraController()).mCamera;
        if (camera == null) return null;
        return camera.getParameters().getSupportedVideoSizes();
    }


    public List<Camera.Size> getSupportedPictureSizes(){
        Camera camera = ((Camera1) getCameraController()).mCamera;
        if (camera == null) return null;
        return camera.getParameters().getSupportedPictureSizes();
    }


    public List<Camera.Size> getSupportedPreviewSizes(){
        Camera camera = ((Camera1) getCameraController()).mCamera;
        if (camera == null) return null;
        return camera.getParameters().getSupportedPreviewSizes();
    }


    public void setPreviewSize(CameraController.PreviewSelector selector){
        getCameraController().setPreviewSize(selector);
    }


    public void setPhotoSize(final int width, final int height){
        getCameraController().setOnPhotoSizeListener(new Runnable() {
            @Override
            public void run() {
                Camera camera = ((Camera1) getCameraController()).mCamera;
                if (camera == null) return;
                Camera.Parameters parameters = camera.getParameters();
                parameters.setPictureSize(width,height);
                camera.setParameters(parameters);
            }
        });
    }



    public void setVideoSize(final int width, final int height){
        getCameraController().setOnVideoSizeListener(new Runnable() {
            @Override
            public void run() {
                ((Camera1)getCameraController()).getMediaRecorder().setVideoSize(width, height);
            }
        });
    }

}
