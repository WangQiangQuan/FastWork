package fast.wq.com.fastandroid.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import fast.wq.com.fastandroid.R;

/**
 * Glide是一款由Bump Technologies开发的图片加载框架
 */
public class GlideActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
//        imageView = (ImageView) findViewById(R.id.image_view);
    }

    public void loadImage(View view) {
        String url = "https://t11.baidu.com/it/u=1674713851,1784222220&fm=173&s=F9A58C54E5793E314A02E81F030050C9&w=420&h=220&img.JPEG";
        Glide.with(this).load(url).into(imageView);

//        Glide.with(GlobalStates.getContext())
//                .load(R.drawable.banner)
//
//                .diskCacheStrategy(DiskCacheStrategy.NONE)//以禁用掉Glide的缓存功能。
//                .override(100,100)//指定图片大小 100*10像素,
//                .into(imageView);
    }
}
