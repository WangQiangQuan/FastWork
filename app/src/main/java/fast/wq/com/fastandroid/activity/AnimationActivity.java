package fast.wq.com.fastandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import fast.wq.com.fastandroid.R;

/**
 * http://blog.csdn.net/qibin0506/article/details/48129139/
 */
public class AnimationActivity extends Activity {

    private ImageView mImageView;

    private Bitmap mBitmap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mImageView = findViewById(R.id.image);

//
//        int[] screenLocation = new int[2];
//        mImageView.getLocationOnScreen(screenLocation);
//
//        animUtils.anim(screenLocation[0],screenLocation[1],mImageView.getWidth(),mImageView.getHeight(),720,1280);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                start();
//                scale(mImageView);
//                scaleThumbnail(mImageView);
                makeSceneTransitionAnimation(view);
//                go();
            }
        });
    }

    /**
     * 就这5个方法可以调用，也就是说为我们提供了这么5中过度方式，在使用的时候我们需要在theme指定:
     * <item name="android:windowContentTransitions">true</item>
     */

//    private void test(){
//        ActivityOptionsCompat.makeCustomAnimation()
//    }
    private void scale(View view) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view,
                view.getWidth() / 2, view.getHeight() / 2, view.getWidth(), view.getHeight());
        ActivityCompat.startActivity(this, new Intent(this, CustomToActivity.class),
                compat.toBundle());
    }

    private void scaleThumbnail(View view) {
        mBitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.dmloading);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(view,mBitmap,1,1
              );
        ActivityCompat.startActivity(this, new Intent(this, CustomToActivity.class),
                compat.toBundle());
    }

    private void makeSceneTransitionAnimation(View view){
        ActivityOptionsCompat compat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        view, "wangq");
        ActivityCompat.startActivity(this, new Intent(this,
                CustomToActivity.class), compat.toBundle());
    }
    int fromid;
    int toid;

    private void start() {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(this, fromid, toid);

        Intent mIntent = new Intent(this, CustomToActivity.class);
        ActivityCompat.startActivity(this, mIntent, compat.toBundle());
    }

    /**
     * -------
     */
    private void go(){
        startActivity(new Intent(this,CustomT2Activity.class));
//        overridePendingTransition(R.anim.ac_enter_anim,0);
        overridePendingTransition(R.anim.ac_enter_anim_tr,0);
//        overridePendingTransition(R.anim.ac_enter_anim,0);
//        overridePendingTransition(R.anim.ac_enter_anim,0);
//        overridePendingTransition(R.anim.ac_enter_anim,0);
    }

}
