package fast.wq.com.functionlibrary.camera;

import android.hardware.Camera;

/**
 * Created by wangqiang on 2017/9/17.
 */

public class CustomCamera {
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
            camera.release();
        }
    }
}
