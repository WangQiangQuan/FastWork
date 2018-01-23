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

    public Page(Activity base) {
        super(base);
    }


    //加入生命周期

    public void onCreate(){

    }

    //拦截事件

    private WindowCallbackWrapper mWindowCallbackWrapper;
    private void initTouch(Activity base){
        Window.Callback wrapped = base.getWindow().getCallback();
        if (wrapped == null){
            wrapped = base;
        }
        mWindowCallbackWrapper = new WindowCallbackWrapper(wrapped){
            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                return Page.this.dispatchKeyEvent(event);
            }

            @Override
            public boolean dispatchTouchEvent(MotionEvent event) {
                return Page.this.dispatchTouchEvent(event);
            }
        };
    }
    public abstract boolean dispatchKeyEvent(KeyEvent event);
    public abstract boolean dispatchTouchEvent(MotionEvent event);


}
