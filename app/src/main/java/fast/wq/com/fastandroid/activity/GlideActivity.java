package fast.wq.com.fastandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import fast.wq.com.fastandroid.R;

/**
 * with
 * with()方法的重载种类非常多:主要是为了获得RequestManager
 * load：
 * 返回一个 DrawableTypeRequest
 * DrawableTypeRequest的父类是DrawableRequestBuilder 中load
 * 这里就是我们常用的各种api
 * into()：
 * DrawableRequestBuilder 的父类GenericRequestBuilder 中into
 * 申城request
 *
 * HttpUrlFetcher 返回一个InputStream
 *
 * StreamBitmapDecoder 中decode Bitmap
 */

public class GlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        ImageView view = null;
        Glide.with(this).load("").into(view);
    }
}
