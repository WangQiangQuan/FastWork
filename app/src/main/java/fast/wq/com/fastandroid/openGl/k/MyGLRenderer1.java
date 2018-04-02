package fast.wq.com.fastandroid.openGl.k;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by admin on 2018/3/30.
 * 在屏幕中绘制红色
 */

public class MyGLRenderer1 implements GLSurfaceView.Renderer{
    private Triangle mTriangle;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 初始化一个三角形
        mTriangle = new Triangle();

    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }
}
