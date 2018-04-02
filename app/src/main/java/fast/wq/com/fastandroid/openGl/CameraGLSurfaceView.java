package fast.wq.com.fastandroid.openGl;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * https://blog.csdn.net/a296777513/article/details/63685658 :https://github.com/296777513
 * https://blog.csdn.net/huiguixian/article/details/36900865
 * GLSurfaceView 画布
 * GLSurfaceView.Renderer 画笔
 */

public class CameraGLSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener {
    private Context mContext;
    private SurfaceTexture mSurface;
    private int mTextureID = -1;

    public CameraGLSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public CameraGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        mContext = context;
        // 设置OpenGl ES的版本为2.0
        setEGLContextClientVersion(2);
        // 设置与当前GLSurfaceView绑定的Renderer
        setRenderer(this);
        // 设置渲染的模式
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    //Renderer
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//        mTextureID = GlUtil.createTextureID();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }

    /**
     * 设置位脏数据的模式，当有新数据来的时候调用，
     * 用来通知onDrawFrame
     */

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {

    }
}
