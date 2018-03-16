package fast.wq.com.fastandroid.view.texture;

import android.graphics.SurfaceTexture;
import android.view.TextureView;

/**
 * http://blog.csdn.net/HardWorkingAnt/article/details/72784044
 * SurfaceView的工作方式是创建一个置于应用窗口之后的新窗口。
 * 这种方式的效率非常高，因为SurfaceView窗口刷新的时候不需要重绘应用程序的窗口
 * http://blog.csdn.net/conowen/article/details/7821409
 * 极客学院
 * http://wiki.jikexueyuan.com/project/deep-android-v1/surface.html
 */

public class FTextureView implements TextureView.SurfaceTextureListener {
    //在SurfaceTexture准备使用时调用。
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    /**
     * 当SurfaceTexture缓冲区大小更改时调用。
     * @param surfaceTexture
     * @param i
     * @param i1
     */
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    /**
     * 即将被销毁时调用。如果返回true，则调用此方法后，表面纹理中不会发生渲染。
     * 如果返回false，则客户端需要调用release()。大多数应用程序应该返回true。
     * @param surfaceTexture
     * @return
     */
    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    /**
     * 当指定SurfaceTexture的更新时调用updateTexImage()。
     * @param surfaceTexture
     */
    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }
}
