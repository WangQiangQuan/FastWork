package fast.wq.com.fastandroid.view.dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by admin on 2018/2/11.
 */

public class MyTextView extends TextView{
    private static final String TAG = "MyTextView";
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
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
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
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
 02-11 17:51:37.655 6356-6356/fast.wq.com.fastandroid I/DispatchActivity: dispatchTouchEvent: ACTION_DOWN
 02-11 17:51:37.656 6356-6356/fast.wq.com.fastandroid I/MyTextView: dispatchTouchEvent: ACTION_DOWN
 02-11 17:51:37.656 6356-6356/fast.wq.com.fastandroid I/DispatchActivity: onTouch: ACTION_DOWN
 02-11 17:51:37.656 6356-6356/fast.wq.com.fastandroid I/MyTextView: onTouchEvent: ACTION_DOWN

 02-11 17:51:37.769 6356-6356/fast.wq.com.fastandroid I/DispatchActivity: dispatchTouchEvent: ACTION_MOVE
 02-11 17:51:37.770 6356-6356/fast.wq.com.fastandroid I/MyTextView: dispatchTouchEvent: ACTION_MOVE
 02-11 17:51:37.770 6356-6356/fast.wq.com.fastandroid I/DispatchActivity: onTouch: ACTION_MOVE
 02-11 17:51:37.770 6356-6356/fast.wq.com.fastandroid I/MyTextView: onTouchEvent: ACTION_MOVE

 02-11 17:51:37.772 6356-6356/fast.wq.com.fastandroid I/DispatchActivity: dispatchTouchEvent: ACTION_UP
 02-11 17:51:37.773 6356-6356/fast.wq.com.fastandroid I/MyTextView: dispatchTouchEvent: ACTION_UP
 02-11 17:51:37.773 6356-6356/fast.wq.com.fastandroid I/DispatchActivity: onTouch: ACTION_UP
 02-11 17:51:37.773 6356-6356/fast.wq.com.fastandroid I/MyTextView: onTouchEvent: ACTION_UP
 02-11 17:51:37.777 6356-6356/fast.wq.com.fastandroid I/DispatchActivity: onClick:
 */

/**
 * dispatchTouchEvent      return false;
 02-11 18:08:00.451 8694-8694/fast.wq.com.fastandroid I/DispatchActivity: dispatchTouchEvent: ACTION_DOWN
 02-11 18:08:00.452 8694-8694/fast.wq.com.fastandroid I/MyTextView: dispatchTouchEvent: ACTION_DOWN

 02-11 18:08:00.452 8694-8694/fast.wq.com.fastandroid I/DispatchActivity: onTouchEvent: ACTION_DOWN

 02-11 18:08:00.537 8694-8694/fast.wq.com.fastandroid I/DispatchActivity: dispatchTouchEvent: ACTION_UP
 02-11 18:08:00.537 8694-8694/fast.wq.com.fastandroid I/DispatchActivity: onTouchEvent: ACTION_UP
 */

/**    return true;
 02-11 18:12:12.967 9336-9336/fast.wq.com.fastandroid I/DispatchActivity: dispatchTouchEvent: ACTION_DOWN
 02-11 18:12:12.968 9336-9336/fast.wq.com.fastandroid I/MyTextView: dispatchTouchEvent: ACTION_DOWN
 02-11 18:12:13.064 9336-9336/fast.wq.com.fastandroid I/DispatchActivity: dispatchTouchEvent: ACTION_UP
 02-11 18:12:13.065 9336-9336/fast.wq.com.fastandroid I/MyTextView: dispatchTouchEvent: ACTION_UP
 */
/**
 * 结论
 * super. 按从外层到内层顺序执行
 * true 提前消费，结束
 * false 处理不了返回给父类处理
 */