package com.zhy.sample_okhttp.testinterface;

import android.app.Activity;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * <pre>
 *     Created by fly
 *     e-mail : 18810086961@163.com
 *     time   : 2017/07/18
 *     desc   :
 *     version: 1.0     初始化
 *
 *  <pre>
 */
public interface ApiService {
   @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
    @GET("service/getInfo.php")
    Call<GetIpinfoResponse> getIpInfp(@Query("ip") String ip);

//    使用@Field时记得添加@FormUrlEncoded
//Post 提交JSON数据
//    有时提交的数据量比较大时，用键值对的方式提交参数不太方便，
// Retrofit可以通过@Body注释，直接传递一个对象给请求主体，Retrofit通过JSON转化器，把对象映射成JSON数据。
//    假设我们需要提交的数据为
//
//    {
//        "id": 1,
//            "text": "my task title"
//    }

    @FormUrlEncoded
    @POST("Comments/{newsId}")
    Call<GetIpinfoResponse> postIpInfp(@Path("newsId") String commentId,
                                       @Query("access_token") String acess_token,
                                       @Field("reason") String reason,
                                       @Body CommentBean commentBean);
//    若需要重新定义接口地址，可以使用@url，将地址以参数的形式传入即可
    @GET
    Call<List<Activity>> getActivityList(@Url String url, @QueryMap Map<String,String> map);
}
