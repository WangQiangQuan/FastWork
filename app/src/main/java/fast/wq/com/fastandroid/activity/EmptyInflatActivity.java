package fast.wq.com.fastandroid.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.utils.ViewUtil;

public class EmptyInflatActivity extends AppCompatActivity {
    private ViewStub mViewSub;
    boolean isInflate = false;//避免反复填充
    RelativeLayout loading_layout;
    LinearLayout fail_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_empty_inflat);
        setContentView(R.layout.layout_viewstub);

        mViewSub = (ViewStub) findViewById(R.id.id_empty_stub);
        mViewSub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub viewStub, View view) {
                isInflate = true;
            }
        });

//        new Timer().schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                handler.sendEmptyMessage(1);
//            }
//        }, 1500);// 延迟1秒,然后加载
    }


    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            View pic_view = mViewSub.inflate();// ①
            //pic_sub.setVisibility(View.VISIBLE);// ②
            RelativeLayout loading_layout = (RelativeLayout) pic_view.findViewById(R.id.loading_layout);
            loading_layout.setVisibility(View.VISIBLE);
            View view = findViewById(R.id.id_empty_stub);//③ null
            view = findViewById(R.id.id_after_inflate);//④
        }

        ;
    };

    //初始化
    private View initViewStub(ViewStub stubMatchSingle) {//填充ViewStub的方法
        if (!isInflate) {//如果没有填充则执行inflate操作
            View view = stubMatchSingle.inflate();

//            //初始化ViewStub的layout里面的控件
            View my = findViewById(R.id.id_after_inflate);
            loading_layout = (RelativeLayout) my.findViewById(R.id.loading_layout);
            fail_layout = (LinearLayout) my.findViewById(R.id.fail_layout);
            return view;

        }
        return null;
    }

    public void showerror(View view) {
        initViewStub(mViewSub);
        loading_layout.setVisibility(View.GONE);
        fail_layout.setVisibility(View.VISIBLE);
    }

    public void showempty(View view) {

        initViewStub(mViewSub);

        loading_layout.setVisibility(View.VISIBLE);
        fail_layout.setVisibility(View.GONE);
        view = findViewById(R.id.id_after_inflate);//④
    }

    public void hide(View view) {
        if (mViewSub != null ) {

            ViewUtil.gone(loading_layout);
            ViewUtil.gone(fail_layout);
        }
    }
}
