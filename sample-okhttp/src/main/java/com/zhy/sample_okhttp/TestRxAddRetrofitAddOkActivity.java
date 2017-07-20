package com.zhy.sample_okhttp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zhy.sample_okhttp.testinterface.ApiService;
import com.zhy.sample_okhttp.testinterface.CommitResult;
import com.zhy.sample_okhttp.testinterface.DuoShuoApi;
import com.zhy.sample_okhttp.testinterface.GetIpinfoResponse;
import com.zhy.sample_okhttp.testinterface.MovieService;
import com.zhy.sample_okhttp.testinterface.MovieSubject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

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
public class TestRxAddRetrofitAddOkActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final String TAG = TestRxAddRetrofitAddOkActivity.class.getSimpleName();
    private ApiService apiService;
    private DuoShuoApi duoShuoApi;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
//          retrofit的网络请求
        duoShuoApi = DuoShuoApi.getApi();


        //okHttpClient的配置
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);


        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
//        RxJavaCallAdapterFactory这个类。用过的朋友们都知道，它是用来把Retrofit转成RxJava可用的适配类
        Call<GetIpinfoResponse> commitResultCall = apiService.getIpInfp("123");
        commitResultCall.enqueue(new Callback<GetIpinfoResponse>() {
            @Override
            public void onResponse(Call<GetIpinfoResponse> call, Response<GetIpinfoResponse> response) {
                if (response.isSuccessful()) {
                    response.body().toString();
                }
            }

            @Override
            public void onFailure(Call<GetIpinfoResponse> call, Throwable t) {

            }
        });
        try {
            commitResultCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MovieService movieService = retrofit.create(MovieService.class);
        Subscription subscription = movieService.getTop250(0, 20)
//                - Schedulers.io()      io操作的线程, 通常io操作,如文件读写.
//                - Schedulers.computation()      计算线程,适合高计算,数据量高的操作.
//                - Schedulers.newThread()      创建一个新线程,适合子线程操作.
//                - AndroidSchedulers.mainThread()      Android的主线程,主线程
//
//        链接：http://www.jianshu.com/p/7c0640963bac
//subscribeOn(),只有在第一次调用的时候生效,之后不管调用多少次,只会以第一次为准.
                //observeOn(),可以被调用多次,每次调用都会更改线程.
                .subscribeOn(Schedulers.io())
               // .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieSubject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(MovieSubject movieSubject) {

                    }
                });
//        Call<MovieSubject> call= movieService.getTop250(0,20);
//        call.enqueue(new Callback<MovieSubject>() {
//            @Override
//            public void onResponse(Call<MovieSubject> call, Response<MovieSubject> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieSubject> call, Throwable t) {
//
//            }
//        });
//
//        try {
//            Response<MovieSubject> response=call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//okhttp的使用


    }

    OkHttpClient okHttpClient = new OkHttpClient();
   // HTTP GET
    String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        okhttp3.Response response = okHttpClient.newCall(request).execute();
        //            public boolean isSuccessful()
//            Returns true if the code is in [200..300),
        if (response.isSuccessful()) {
            response.body().string();
        } else {

        }
        return response.body().string();
    }
//    HTTP POST
//    POST提交Json数据
    public  static  final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
    public final static long DEFAULT_TIME_OUT = 5000;
//    String post(String url,String json) throws  IOException{
//        RequestBody body=RequestBody.create(JSON,json);
//        Request request=new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        okhttp3.Response response=okHttpClient.newCall(request).execute();
//        if(response.isSuccessful()){
//            return  response.body().string();
//        }else {
//            throw new IOException("Unexpected code" +response);
//        }
//    }
    //POST提交键值对
    String post(String url,String json) throws IOException{
       RequestBody  formBody=new FormBody.Builder()
               .add("platform","android")
               .add("name","bug")
               .build();
        Request request=new Request.Builder()
                .url(url)
                .header("","")
                .addHeader("","")
                .post(formBody)
                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"),""))
                .build();
        okhttp3.Response response=okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            return  response.body().string();
        }else {
            throw  new IOException("unexpected code "+response);
        }
    }
    private Callback<CommitResult> callback = new Callback<CommitResult>() {
        @Override
        public void onResponse(Call<CommitResult> call, Response<CommitResult> response) {
//            public boolean isSuccessful()
//            Returns true if the code is in [200..300),
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
            } else {
                Log.e(TAG, "+++" + response.message());
            }
        }

        @Override
        public void onFailure(Call<CommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

}
