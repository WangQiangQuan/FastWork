package fast.wq.com.fastandroid.detialsview;

import android.app.Activity;
import android.content.ContextWrapper;
import android.support.v7.view.WindowCallbackWrapper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

/**
 * Created by admin on 2018/1/23.
 */

public abstract class Page extends ContextWrapper {
    private Activity mActivity;

    public Page(Activity base) {
        super(base);
        mActivity = base;
        onCreate();
    }


    //加入生命周期

    public void onCreate() {
        if (mActivity !=  null){
            initTouch(mActivity);
        }

    }

    public void onDestroy() {
        if (mActivity !=  null){
            giveBack(mActivity);
        }
    }


    private Window.Callback mOriginalWindowCallback;
    private WindowCallbackWrapper mWindowCallbackWrapper;

    private void initTouch(Activity base) {
        Window.Callback mOriginalWindowCallback = base.getWindow().getCallback();
        if (mOriginalWindowCallback == null) {
            mOriginalWindowCallback = base;
        }
        this.mOriginalWindowCallback = mOriginalWindowCallback;
        mWindowCallbackWrapper = new WindowCallbackWrapper(mOriginalWindowCallback) {
            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                return Page.this.dispatchKeyEvent(event);
            }

            @Override
            public boolean dispatchTouchEvent(MotionEvent event) {
                return Page.this.dispatchTouchEvent(event);
            }
        };
        base.getWindow().setCallback(mWindowCallbackWrapper);
    }

    private void giveBack(Activity base) {
        base.getWindow().setCallback(mOriginalWindowCallback);
    }

    public abstract boolean dispatchKeyEvent(KeyEvent event);

    public abstract boolean dispatchTouchEvent(MotionEvent event);


}
