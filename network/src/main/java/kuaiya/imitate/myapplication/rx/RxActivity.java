package kuaiya.imitate.myapplication.rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import kuaiya.imitate.myapplication.R;
import rx.Observable;
import rx.Subscriber;

/**
 * onComplete和onError 不能同时调用。
 *
 */
public class RxActivity extends AppCompatActivity {

    private static final String TAG = "RxActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
    }

    private void hellowold(){
        //1 创建被观察者
        Observable mObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world!");
                subscriber.onCompleted();
            }
        });
        //2 创建观察者
        Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, "onNext: ");
            }
        };
        
        //订阅
        mObservable.subscribe(subscriber);
    }
}
