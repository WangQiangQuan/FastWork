package fast.wq.com.fastandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fast.wq.com.fastandroid.activity.VGHActivity;
import fast.wq.com.fastandroid.badge.BadgeChangedListener;
import fast.wq.com.fastandroid.badge.BadgeMessage;
import fast.wq.com.fastandroid.gloable.GlobalStates;
import fast.wq.com.fastandroid.utils.LinkListUtils;
import fast.wq.com.fastandroid.view.DynamicView;
import fast.wq.com.fastandroid.view.TaskLinerLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    TaskLinerLayout mtaskLinerlayout;
    private ImageView image;
    private DynamicView mdynamicView;
    private float mStartX,mStopX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String result = getArgUrl(GS_HOME_RES_RECORD_URL,"1");
//        Log.d(TAG, "onCreate() called with: result = [" + result + "]");


//        mtaskLinerlayout = (TaskLinerLayout) findViewById(R.id.layout);
//        addVIew(10);
//
//       String url= StringUtils.getPageName("http://downloadb.dewmobile.net/z/qiangjing13.apk");
//        Log.i("wang",url);

//        image = (ImageView) findViewById(R.id.image);
//        BitmapUtils.calScleType(image,98,74,200,200);
//        image.setImageResource(R.drawable.test);

//        PushBadgeProcessor mPushProcessor = new PushBadgeProcessor(this,1);
//        mPushProcessor.registerBadgeChangeListener(mListener);
//        mPushProcessor.reloadData();

        GlobalStates.setContext(this.getApplication());

        mdynamicView = (DynamicView) findViewById(R.id.mdynamicView);
        LinkListUtils list = new LinkListUtils();
        list.add(111);
        list.add(222);
        list.add(333);
        list.cauclate();

        Intent mintent = new Intent(this, VGHActivity.class);
        this.startActivity(mintent);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                mStopX = ev.getRawX();
                mdynamicView.updateView(mStartX,mStopX);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    BadgeChangedListener mListener = new BadgeChangedListener(){

        @Override
        public void onBadgeChanged(BadgeMessage badgeMessage) {
            Log.i("wang","badgeMessage"+badgeMessage.toString());
        }
    };

    private void addVIew(int size){
        for (int i=0;i<size;i++){

            View  view = this.getLayoutInflater().inflate(R.layout.item_row,null);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            TextView action = (TextView) view.findViewById(R.id.action);
            onclick(i, action);
            tv.setText("ss = "+i);
            mtaskLinerlayout.addView(view);
        }
    }

    private void onclick(final int i, TextView action) {
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("wang","i="+i);
            }
        });
    }


}
