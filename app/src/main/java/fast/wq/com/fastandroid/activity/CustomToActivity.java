package fast.wq.com.fastandroid.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
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

        addTransitionListener();
        image = findViewById(R.id.image);
        image.setImageResource(R.drawable.dmloading);
        postponeEnterTransition();
        scheduleStartPostponedTransition(image);
//        parms(image);
//        mAc = this;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(300);
//                    mAc.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
////                            parms(image);
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
    @Override
    public void onBackPressed() {
//        this.finish();
        finishAfterTransition();
    }

    private void parms(View view){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height = 720;
        params.width = 1080;
        view.setLayoutParams(params);
    }

    private boolean addTransitionListener() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
            Transition transition = getWindow().getSharedElementEnterTransition();
            if (transition != null) {
                transition.setDuration(1000);
                transition.addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {

                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {

                        transition.removeListener(this);
                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });
                return true;
            }
        }
        return false;
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        Log.e("Donald", "scheduleStartPostponedTransition");
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        //启动动画
                        Log.e("Donald", "scheduleStartPostponedTransition draw");
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }
}
