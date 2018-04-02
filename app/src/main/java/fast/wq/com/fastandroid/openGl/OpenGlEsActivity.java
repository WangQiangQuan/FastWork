package fast.wq.com.fastandroid.openGl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fast.wq.com.fastandroid.openGl.k.MyGLSurfaceView;

/**
 * https://blog.csdn.net/a296777513/article/details/54729264
 */
public class OpenGlEsActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_open_gl_es);
        // 创建一个GLSurfaceView实例然后设置为activity的ContentView.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }
}
