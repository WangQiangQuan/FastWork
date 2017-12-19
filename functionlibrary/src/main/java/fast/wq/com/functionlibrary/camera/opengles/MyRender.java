package fast.wq.com.functionlibrary.camera.opengles;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by wangqiang on 2017/9/23.
 */

public class MyRender implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glDisable(GL10.GL_DITHER);
    }


    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {

    }
}
