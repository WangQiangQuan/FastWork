package fast.wq.com.fastandroid.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fast.wq.com.fastandroid.cache.ImageCache;
import fast.wq.com.fastandroid.cache.ImageMemoryCache;

/**
 * 图片加载
 */

public class ImageLoader {
    ImageCache imageCache = new ImageMemoryCache();
    ExecutorService mExecutorService
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //注入具体的实现类
    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    public void displayImage(final ImageView mImageView, final String imageUrl) {
        Bitmap mBitmap = imageCache.get(imageUrl);
        if (mBitmap != null) {
            mImageView.setImageBitmap(mBitmap);
            return;
        }
        submitLoadRequest(mImageView, imageUrl);
    }

    private void submitLoadRequest(final ImageView mImageView, final String imageUrl) {
        mImageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap mBitmap = downLoadImage(imageUrl);
                if (mBitmap == null) {
                    return;
                }

                if (mImageView.getTag().equals(imageUrl)) {
                    mImageView.setImageBitmap(mBitmap);
                }

                imageCache.put(imageUrl, mBitmap);
            }
        });
    }

    private Bitmap downLoadImage(String imageUrl) {
        Bitmap mBitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            mBitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mBitmap;
    }
}
