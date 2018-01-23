package fast.wq.com.fastandroid.detialsview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import fast.wq.com.fastandroid.R;

public class MainViewActivity extends AppCompatActivity implements ILifeCycleProvider {
    private static final String TAG = "wangQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        Log.i(TAG, "onCreate: ");
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
    }

    @Override
    public void onPageShow() {
        onPause();
    }

    @Override
    public void onPageResume() {
        onResume();
    }

    public void onclick(View view) {
        DetailsView m = new DetailsView(this);
        m.setLifeCallback(this);
        m.onPageShow();
    }
}
