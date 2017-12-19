package fast.wq.com.functionlibrary.camera;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import fast.wq.com.functionlibrary.R;

public class CameraActivity extends Activity implements SurfaceHolder.Callback {

    private SurfaceView mPreView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
//        mPreView
    }

    private void startCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (camera == null){
            camera = getCamera();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releasePreview();
    }


    Camera camera;
    /**
     * 获取
     * @return
     */
    private Camera getCamera() {
        try {
            camera = Camera.open();
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;
    }

    /**
     * 开始相机预览
     */
    private void setStartPreview(){

    }

    /**
     * 释放相机资源
     */
    private void releasePreview(){
        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
