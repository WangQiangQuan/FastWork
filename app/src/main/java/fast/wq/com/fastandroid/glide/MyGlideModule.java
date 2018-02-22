package fast.wq.com.fastandroid.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

import fast.wq.com.fastandroid.glide.progress.ProgressInterceptor;
import okhttp3.OkHttpClient;

/**
 * 自定义模块的基本用法
 * 这两个方法分别就是用来更改Glide和配置以及替换Glide组件的
 * ExternalCacheDiskCacheFactory的默认缓存路径是在sdcard/Android/包名/cache/image_manager_disk_cache目录当中
 */

public class MyGlideModule implements GlideModule {
    //默认硬盘缓存大小都是250M
    public static final int DISK_CACHE_SIZE = 500 * 1024 * 1024;
    //更改Glide配置
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //图片都换存在sd卡上
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,DISK_CACHE_SIZE));
        //图片质量变好了，但同时内存开销也会明显增大
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, DISK_CACHE_SIZE));
    }

    //替换Glide组件
    //默认情况下，Glide使用的是基于原生HttpURLConnection进行订制的HTTP通讯组件，此将Glide中的HTTP通讯组件修改成OkHttp的这个需求比较常见，
    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory());

        //使用拦截器
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ProgressInterceptor());
        OkHttpClient okHttpClient = builder.build();
        glide.register(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory(okHttpClient));
    }
}
/**
 setMemoryCache()
 用于配置Glide的内存缓存策略，默认配置是LruResourceCache。

 setBitmapPool()
 用于配置Glide的Bitmap缓存池，默认配置是LruBitmapPool。

 setDiskCache()
 用于配置Glide的硬盘缓存策略，默认配置是InternalCacheDiskCacheFactory。

 setDiskCacheService()
 用于配置Glide读取缓存中图片的异步执行器，默认配置是FifoPriorityThreadPoolExecutor，也就是先入先出原则。

 setResizeService()
 用于配置Glide读取非缓存中图片的异步执行器，默认配置也是FifoPriorityThreadPoolExecutor。

 setDecodeFormat()
 用于配置Glide加载图片的解码模式，默认配置是RGB_565。
 */
/**
 * Glide官方给我们提供了非常简便的HTTP组件替换方式
 dependencies {
 compile 'com.squareup.okhttp3:okhttp:3.9.0'
 compile 'com.github.bumptech.glide:okhttp3-integration:1.5.0@aar'
 }
 */
