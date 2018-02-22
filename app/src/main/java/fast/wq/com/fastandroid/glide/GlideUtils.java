package fast.wq.com.fastandroid.glide;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import fast.wq.com.fastandroid.gloable.GlobalStates;

/**
 * http://blog.csdn.net/guolin_blog/article/details/78357251
 * Glide最基本的使用方式:关键的三步走：先with()，再load()，最后into()
 */

public class GlideUtils {
    private static final String TAG = "GlideUtils";

    //load本地图片
    public void loadLocale(ImageView mImage,String path){
        File file = new File(path);
        Glide.with(GlobalStates.getContext()).load(file).into(mImage);
    }

    //load本地图片
    public void loadRes(ImageView mImage,int id){
        Glide.with(GlobalStates.getContext()).load(id).into(mImage);
    }
    //load 二进制流
    public void loadLocale(ImageView mImage,byte[] bimages){
        Glide.with(GlobalStates.getContext()).load(bimages).into(mImage);
    }
    //load uri
    public void loadLocale(ImageView mImage,Uri uri){
        Glide.with(GlobalStates.getContext()).load(uri).into(mImage);
    }

    //占位符
    public void loadWithplaceHolder(ImageView mImage,String url,int placeHolder,int error){
        Glide.with(GlobalStates.getContext())
                .load(url)
                .placeholder(placeHolder)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)//以禁用掉Glide的缓存功能。
                .override(100,100)//100*100像素
                .into(mImage);
    }

    /**
     * 缓存
     * 在缓存这一功能上，Glide又将它分成了两个模块，一个是内存缓存，一个是硬盘缓存。
     * 内存缓存的主要作用是防止应用重复将图片数据读取到内存当中，
     * 而硬盘缓存的主要作用是防止应用重复从网络或其他地方重复下载和读取数据。
     */

    //默认情况下，Glide自动就是开启内存缓存的。
    public void loadCache(ImageView mImage,String url){
        Glide.with(GlobalStates.getContext())
                .load(url)
                .skipMemoryCache(true)//就表示禁用掉Glide的内存缓存功能。
                .into(mImage);
    }

    //硬盘缓存

    /**
     DiskCacheStrategy.NONE： 表示不缓存任何内容。
     DiskCacheStrategy.SOURCE： 表示只缓存原始图片。
     DiskCacheStrategy.RESULT： 表示只缓存转换过后的图片（默认选项）。
     DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
     */

    public void loadDiskCache(ImageView mImage,String url){
        Glide.with(GlobalStates.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mImage);
    }
    /**
     * 回调和监听
     */

    public void loadSimpleTarget(final ImageView mImage,String url){
        //我们使用它可以将Glide加载出来的图片对象获取到
        SimpleTarget<GlideDrawable> simpleTarget = new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation glideAnimation) {
                mImage.setImageDrawable(resource);
            }
        };
        Glide.with(GlobalStates.getContext())
                .load(url)
                .into(mImage);
    }

    /**
     * 如果我们在RequestListener的onResourceReady()方法中返回了true，
     * 那么就不会再回调Target的onResourceReady()方法了。
     */
    public void loadListener(final ImageView mImage, String url) {
        Glide.with(GlobalStates.getContext())
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        Log.i(TAG, "onException: ");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.i(TAG, "onResourceReady: ");
                        return false;
                    }
                })
                .into(mImage);
    }

    /**
     * 预加载
     * preload()方法有两个方法重载，
     * 一个不带参数，表示将会加载图片的原始尺寸，另一个可以通过参数指定加载图片的宽和高
     * 替换into()方法的
     */

    public void loadpreload(String url){
        Glide.with(GlobalStates.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .preload();
    }
    /**
     * 直接访问这些缓存文件
     * downloadOnly()
     * 顾名思义，downloadOnly()方法表示只会下载图片，而不会对图片进行加载。当图片下载完成之后，我们可以得到图片的存储路径，以便后续进行操作。
     */

    /**
     * 变换
     * 更多变换
     * glide-transformations的项目主页地址是 https://github.com/wasabeef/glide-transformations 。
     */

    public void transCricle(ImageView mImage,String url){
        Glide.with(GlobalStates.getContext())
                .load(url)
                .transform(new CircleCrop(GlobalStates.getContext()))
                .into(mImage);
    }

    /**
     * 实现下载进度
     * 向OkHttp中添加一个自定义的拦截器，就可以在拦截器中捕获到整个HTTP的通讯过程，然后加入一些自己的逻辑来计算下载进度，这样就可以实现下载进度监听的功能了。
     */

}
