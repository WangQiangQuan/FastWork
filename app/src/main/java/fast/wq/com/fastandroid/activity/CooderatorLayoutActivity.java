package fast.wq.com.fastandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fast.wq.com.fastandroid.R;

/**
 * http://blog.csdn.net/xyz_lmn/article/details/48055919
 */
public class CooderatorLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooderator_layout);

//        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Snackbar.make(view,"FAB",Snackbar.LENGTH_LONG)
//                        .setAction("cancel", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                //这里的单击事件代表点击消除Action后的响应事件
//
//                            }
//                        })
//                        .show();
//            }
//        });
    }
}
