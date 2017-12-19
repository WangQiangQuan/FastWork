package fast.wq.com.fastandroid.view.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by wangqiang on 2017/9/16.
 */

public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback ,Runnable{
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private boolean isRunning;
    private Thread t;
    public SurfaceViewTemplate(Context context) {
        this(context,null);
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        isRunning = true;
        t = new Thread( this);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    @Override
    public void run() {

        while (isRunning){
            draw();
        }
    }

    private void draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            if (mSurfaceHolder != null) {
                // draw smething
            }
        } catch (Exception e) {

        } finally {
            if (mCanvas == null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }
}
