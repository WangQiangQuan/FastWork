package fast.wq.com.fastandroid.view.dispatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import fast.wq.com.fastandroid.R;

/**
 * https://zhuanlan.zhihu.com/p/26834562
 */
public class DispatchActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{

    private static final String TAG = "DispatchActivity";

    private TextView myTextview,myTv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);

        myTextview = findViewById(R.id.mytv);
        myTextview.setOnClickListener(this);
        myTextview.setOnTouchListener(this);

        myTv2 = findViewById(R.id.mytv2);
        myTv2.setOnClickListener(this);
        myTv2.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mytv:
                Log.i(TAG, "onClick: ");
                break;
            case R.id.mytv2:
                Log.i(TAG, "onClick:mytv2 ");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.mytv:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG, "onTouch: ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i(TAG, "onTouch: ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i(TAG, "onTouch: ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.i(TAG, "onTouch: ACTION_CANCEL");
                        break;
                    default:
                        break;
                }
                break;

            case R.id.mytv2:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG, "onTouch: ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i(TAG, "onTouch: ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i(TAG, "onTouch: ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.i(TAG, "onTouch: ACTION_CANCEL");
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
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

        return super.dispatchTouchEvent(ev);
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
