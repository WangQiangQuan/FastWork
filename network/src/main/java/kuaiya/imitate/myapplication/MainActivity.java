package kuaiya.imitate.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import kuaiya.imitate.myapplication.bean.MovieSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * https://www.jianshu.com/p/5bc866b9cbb9
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "network";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        test();
        testRxget();
        Log.i(TAG, "onCreate: ");
    }

    /**
     * 1 创建Retrofit实例对象
     * 2 创建接口MovieService
     * 3 用Retrofit 创建 接口实例 MoiveService,
     * 4 call.enqueue
     */
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private void test(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService  movieService = retrofit.create(MovieService.class);
        Call<MovieSubject> call = movieService.getTop250(0,20);
        call.enqueue(new Callback<MovieSubject>() {
            @Override
            public void onResponse(Call<MovieSubject> call, Response<MovieSubject> response) {
                response.body();
                Log.i(TAG, "onResponse: "+response.body().subjects.size());
            }

            @Override
            public void onFailure(Call<MovieSubject> call, Throwable t) {

            }
        });

    }

    public void testRxget(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//创建Retrofit 的时候添加如下代码
                .build();

        MovieService  movieService = retrofit.create(MovieService.class);

        Subscription subscription = movieService.getRxTop250(0,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieSubject>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(MovieSubject movieSubject) {
                        Log.i(TAG, "onResponse: "+movieSubject.subjects.size());
                    }
                });


    }
}
