package fast.wq.com.fastandroid.view.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by admin on 2018/2/11.
 */

public class MyRelativeLayout extends RelativeLayout {

    private static final String TAG = "MyRelativeLayout";

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "dispatchTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "dispatchTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "dispatchTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "dispatchTouchEvent: ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
//        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onInterceptTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onInterceptTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onInterceptTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "onInterceptTouchEvent: ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "onTouchEvent: ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}

/**
 02-12 10:28:53.616 22792-22792/fast.wq.com.fastandroid I/DispatchActivity: dispatchTouchEvent: ACTION_DOWN
 02-12 10:28:53.617 22792-22792/fast.wq.com.fastandroid I/MyRelativeLayout: dispatchTouchEvent: ACTION_DOWN
 02-12 10:28:53.617 22792-22792/fast.wq.com.fastandroid I/MyRelativeLayout: onInterceptTouchEvent: ACTION_DOWN
 02-12 10:28:53.617 22792-22792/fast.wq.com.fastandroid I/MyTextView: dispatchTouchEvent: ACTION_DOWN
 02-12 10:28:53.617 22792-22792/fast.wq.com.fastandroid I/DispatchActivity: onTouch: ACTION_DOWN
 02-12 10:28:53.617 22792-22792/fast.wq.com.fastandroid I/MyTextView: onTouchEvent: ACTION_DOWN
 02-12 10:28:53.733 22792-22792/fast.wq.com.fastandroid I/DispatchActivity: dispatchTouchEvent: ACTION_UP
 02-12 10:28:53.734 22792-22792/fast.wq.com.fastandroid I/MyRelativeLayout: dispatchTouchEvent: ACTION_UP
 02-12 10:28:53.734 22792-22792/fast.wq.com.fastandroid I/MyRelativeLayout: onInterceptTouchEvent: ACTION_UP
 02-12 10:28:53.734 22792-22792/fast.wq.com.fastandroid I/MyTextView: dispatchTouchEvent: ACTION_UP
 02-12 10:28:53.734 22792-22792/fast.wq.com.fastandroid I/DispatchActivity: onTouch: ACTION_UP
 02-12 10:28:53.734 22792-22792/fast.wq.com.fastandroid I/MyTextView: onTouchEvent: ACTION_UP
 02-12 10:28:53.739 22792-22792/fast.wq.com.fastandroid I/DispatchActivity: onClick:mytv2
 */

/**
 * 结论
 * onInterceptTouchEvent true 拦截
 */