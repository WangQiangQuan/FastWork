package fast.wq.com.fastandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.anim.animUtils;

public class AnimationActivity extends Activity {

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mImageView = findViewById(R.id.image);

        int[] screenLocation = new int[2];
        mImageView.getLocationOnScreen(screenLocation);

        animUtils.anim(screenLocation[0],screenLocation[1],mImageView.getWidth(),mImageView.getHeight(),720,1280);
    }

}
