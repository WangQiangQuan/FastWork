package fast.wq.com.fastandroid.glide;

import com.bumptech.glide.load.model.GlideUrl;

/**
 * 他们项目的图片资源都是存放在七牛云上面的，而七牛云为了对图片资源进行保护，会在图片url地址的基础之上再加上一个token参数
 * 就因为token不断在改变，导致Glide的缓存功能完全失效了。
 *
 * 重写getCacheKey 用去掉token作为key
 */

public class MyGlideUrl extends GlideUrl{
    private String mUrl;
    public MyGlideUrl(String url) {
        super(url);
        mUrl = url;
    }

    @Override
    public String getCacheKey() {
        return mUrl.replace(findTokenParam(), "");
    }

    private String findTokenParam() {
        String tokenParam = "";
        int tokenKeyIndex = mUrl.indexOf("?token=") >= 0 ? mUrl.indexOf("?token=") : mUrl.indexOf("&token=");
        if (tokenKeyIndex != -1) {
            int nextAndIndex = mUrl.indexOf("&", tokenKeyIndex + 1);
            if (nextAndIndex != -1) {
                tokenParam = mUrl.substring(tokenKeyIndex + 1, nextAndIndex + 1);
            } else {
                tokenParam = mUrl.substring(tokenKeyIndex);
            }
        }
        return tokenParam;
    }
}
/**
 * Glide.with(this)
 .load(new MyGlideUrl(url))
 .into(imageView);
 */
