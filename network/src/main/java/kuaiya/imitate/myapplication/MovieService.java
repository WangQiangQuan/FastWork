package kuaiya.imitate.myapplication;


import kuaiya.imitate.myapplication.bean.MovieSubject;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by admin on 2018/2/23.
 * https://www.jianshu.com/p/308f3c54abdd
 */

public interface MovieService {
    //获取豆瓣Top250 榜单
    @GET("top250")
    Call<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);

    //用@POST 标签，参数标签用 @Field 或者@Body或者FieldMap
    @FormUrlEncoded//必须加上 @FormUrlEncoded标签，否则会抛异常
    @POST("top250")
    Call<MovieSubject> postTop250(@Field("start") int start,@Field("count") int count);


    @GET("top250")
    Observable<MovieSubject> getRxTop250(@Query("start") int start, @Query("count")int count);
}
