package kuaiya.imitate.myapplication.rx;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * 创建 create
 * 转换 transforming
 * 过滤 filter
 * 组合 combining
 * 错误处理 error
 */

public class Opertions {
    /**
     * 创建
     * create
     * just
     * from
     * defer
     * empty
     * interval
     * range
     * repeat
     * start
     * timer
     */

    private static final String TAG = "Opertions";
    private void create(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("fjlajfldajfl");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: ");
            }
        });
    }

    private void just(){
        Observable.just("sss").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
    }

    private void from(){
        Observable.from(new Integer[]{1,2,3,4,5,6}).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer s) {

            }
        });
    }

    /**
     * 推迟
     * subscribe之后才会创建Observable
     */
    private String valuestr;
    private void defer(){
       Observable observable= Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(valuestr);
            }
        });
        valuestr = " fdkasfjklaj";
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
    }

    /**
     * 定时器
     */
    private void interval(){

    }
    private void range(){
        Observable observable = Observable.range(1,5);
        observable.subscribe(new Subscriber<Integer>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        });
    }
}
