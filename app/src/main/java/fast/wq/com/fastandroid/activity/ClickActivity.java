package fast.wq.com.fastandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fast.wq.com.fastandroid.R;

public class ClickActivity extends AppCompatActivity {
    private static final String TAG = "ClickActivity";
    Button mbtn,mBtn2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        mbtn = findViewById(R.id.btn);
        mBtn2 = findViewById(R.id.btn2);
        
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick:1111 ");
                mBtn2.performClick();
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick:22222 ");
            }
        });
    }
}
