package fast.wq.com.functionlibrary.camera.opengles;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by wangqiang on 2017/9/23.
 */

public class MyGLSurfaceView extends GLSurfaceView {

    public MyGLSurfaceView(Context context) {
        super(context);
        init();
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setRenderer(new MyRender());
    }
}
