package fast.wq.com.fastandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.bean.ParcelableBean;
import fast.wq.com.fastandroid.bean.SerializableBean;
import fast.wq.com.fastandroid.utils.IntentUtils;

public class ImageViewActivity extends AppCompatActivity {

    private static final String TAG = "wang";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);


        ParcelableBean mb1 = new ParcelableBean();
        SerializableBean mb2 = new SerializableBean();

        Intent mIntent = getIntent();
        mb1 = (ParcelableBean) IntentUtils.getParcelable(mIntent,"a");
        mb2 = (SerializableBean) IntentUtils.getSerial(mIntent,"b");


        Log.i(TAG, "onCreate1: "+mb1.name);
        Log.i(TAG, "onCreate2: "+mb2.name);
    }
}
