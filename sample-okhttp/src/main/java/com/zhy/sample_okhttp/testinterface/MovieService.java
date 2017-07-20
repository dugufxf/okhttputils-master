package com.zhy.sample_okhttp.testinterface;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * <pre>
 *     Created by fly
 *     e-mail : 18810086961@163.com
 *     time   : 2017/07/17
 *     desc   :
 *     version: 1.0     初始化
 *
 *  <pre>
 */
public interface MovieService {
//    @GET("top250")
//    Call<MovieSubject> getTop250(@Query("start") int start,@Query("count") int count);
    @FormUrlEncoded
    @POST("top250")
//    说明：使用POST 请求方式时，只需要更改方法定义的标签，用@POST 标签，参数标签用 @Field 或者@Body或者FieldMap，注意：使用POST 方式时注意2点，1，必须加上 @FormUrlEncoded标签，否则会抛异常。2，使用POST方式时，必须要有参数，否则会抛异常, 源码抛异常的地方如下
//    作者：依然范特稀西
//    链接：http://www.jianshu.com/p/5bc866b9cbb9
//    來源：简书
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//    Call<MovieSubject> getTop250(@Field("start") int start,@Field("count") int count);
    rx.Observable<MovieSubject> getTop250(@Field("start") int start, @Field("count") int count);
}
