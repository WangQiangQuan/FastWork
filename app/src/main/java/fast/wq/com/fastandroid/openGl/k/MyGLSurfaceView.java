package fast.wq.com.fastandroid.openGl.k;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by admin on 2018/3/30.
 */

public class MyGLSurfaceView extends GLSurfaceView {
    private  MyGLRenderer1 mRenderer = null;
    public MyGLSurfaceView(Context context) {
        super(context);
        init();
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        // 创建OpenGL ES 2.0 的上下文
        setEGLContextClientVersion(2);

//        mRenderer = new MyGLRenderer();
        mRenderer = new MyGLRenderer1();

        // 设置Renderer 到 GLSurfaceView
        setRenderer(mRenderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }


}
