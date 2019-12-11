package com.hhd.wanandroid_mvvm.net.costomCore;

import retrofit2.Retrofit;

/**
 * Created by zhpan on 2017/4/1.
 */

public class CustomApi {
    public static <T> T getApiService(Class<T> cls, String baseUrl) {
        Retrofit retrofit = RetrofitBuilderUtils.getRetrofit(baseUrl);
        return retrofit.create(cls);
    }
}
