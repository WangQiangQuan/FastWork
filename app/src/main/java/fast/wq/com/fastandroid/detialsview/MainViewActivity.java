package fast.wq.com.fastandroid.detialsview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import fast.wq.com.fastandroid.R;

public class MainViewActivity extends AppCompatActivity implements ILifeCycleProvider {
    private static final String TAG = "wangQ";

    private RelativeLayout mMainLayout;
    private ImageView miv_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        Log.i(TAG, "onCreate: ");
        mMainLayout = findViewById(R.id.id_main);
        miv_image = findViewById(R.id.iv_image);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        removeDetails();

    }

    @Override
    public void onPageShow() {
        onPause();
    }

    @Override
    public void onPageResume() {
        onResume();
    }
    DetailsView m;
    public void onclick(View view) {
        m = new DetailsView(this);
        m.setLifeCallback(this);
        mMainLayout.addView(m);
        m.onPageShow();
        m.setEnterAnimation(miv_image);
    }
    public void removeDetails(){
        if (m!= null && mMainLayout!= null){
            mMainLayout.removeView(m);
        }
    }

}
