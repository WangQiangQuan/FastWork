package fast.wq.com.fastandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import fast.wq.com.fastandroid.R;

public class CustomToActivity extends AppCompatActivity {

    private Activity mAc;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_to);

        image = findViewById(R.id.image);
        image.setImageResource(R.drawable.dmloading);
        parms(image);
        mAc = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                    mAc.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            parms(image);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Override
    public void onBackPressed() {
        this.finish();
    }

    private void parms(View view){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height = 720;
        params.width = 1080;
        view.setLayoutParams(params);
    }
}
