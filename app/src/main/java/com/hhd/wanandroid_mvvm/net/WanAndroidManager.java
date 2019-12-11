package com.hhd.wanandroid_mvvm.net;


import com.hhd.wanandroid_mvvm.net.api.WanAndroidAPI;
import com.hhd.wanandroid_mvvm.net.common.WanAndrodCons;
import com.hhd.wanandroid_mvvm.net.costomCore.CustomApi;

public class WanAndroidManager {

    private static WanAndroidAPI sWanAndroidAPI;
    public static WanAndroidAPI getAPI() {
        if (sWanAndroidAPI == null){
            sWanAndroidAPI = CustomApi.getApiService(WanAndroidAPI.class, WanAndrodCons.BASE_URL);
        }
        return sWanAndroidAPI;
    }

}
