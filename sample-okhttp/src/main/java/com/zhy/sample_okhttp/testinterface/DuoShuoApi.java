package com.zhy.sample_okhttp.testinterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
public class DuoShuoApi {
    public static DuoShuoApi getApi() {
        return SingleHolder.duoShuoApi;
    }

    public static class SingleHolder {
        public static DuoShuoApi duoShuoApi = new DuoShuoApi();
    }

    private DuoShuoApi service;

    private DuoShuoApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:\\api.duoshuo.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(DuoShuoApi.class);
    }

    public DuoShuoApi getService() {
        return service;
    }


}
