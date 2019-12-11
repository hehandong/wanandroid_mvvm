package com.hhd.wanandroid_mvvm.net;


import com.hhd.wanandroid_mvvm.net.api.Api;
import com.hhd.wanandroid_mvvm.net.api.WanAndroidAPI;
import com.hhd.wanandroid_mvvm.net.common.APIConstants;
import com.hhd.wanandroid_mvvm.net.common.WanAndrodCons;
import com.hhd.wanandroid_mvvm.net.costomCore.CustomApi;

public class RetrofitHelper {
    private static Api sAPI;
    private static IdeaApiService mIdeaApiService;
    private static WanAndroidAPI sWanAndroidAPI;

    public static Api getCustomApiService() {
        if (sAPI == null){
            sAPI = CustomApi.getApiService(Api.class, APIConstants.HOST);
        }
        return sAPI;
    }

    public static WanAndroidAPI getWanAndroidAPI() {
        if (sWanAndroidAPI == null){
            sWanAndroidAPI = CustomApi.getApiService(WanAndroidAPI.class, WanAndrodCons.BASE_URL);
        }
        return sWanAndroidAPI;
    }

//    public static IdeaApiService getApiService() {
//        if (mIdeaApiService == null){
//            mIdeaApiService = IdeaApi.getApiService(IdeaApiService.class, Constants.API_SERVER_URL);
//        }
//        return mIdeaApiService;
//    }
}
