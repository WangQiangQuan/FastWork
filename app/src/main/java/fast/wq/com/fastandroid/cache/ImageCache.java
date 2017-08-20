package fast.wq.com.fastandroid.cache;

import android.graphics.Bitmap;

/**
 * 缓存接口
 */

public interface ImageCache {
    public Bitmap get(String url);
    public void put(String url,Bitmap bitmap );
}
