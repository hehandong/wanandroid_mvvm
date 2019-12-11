package com.hhd.wanandroid_mvvm.net.api;


import com.hhd.wanandroid_mvvm.model.ListModel;
import com.hhd.wanandroid_mvvm.model.ProjectTree;
import com.hhd.wanandroid_mvvm.net.module.WanBaseModel;
import com.hhd.wanandroid_mvvm.net.module.WxMenuListModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Author dong
 * @Date 2019-06-04 15:40
 * @Description TODO
 * GitHub：https://github.com/hehandong
 * Email：hehandong@qq.com
 * @Version 1.0
 */
public interface WanAndroidAPI {
    @GET("wxarticle/chapters/json")
    Observable<WxMenuListModel> getWxMenuList();

    //https://wanandroid.com/wxarticle/list/408/2/json
    @GET("wxarticle/list/{chapterId}/{pageIndex}/json")
    Observable<WanBaseModel<ListModel>> getWxArticleList(
            @Path("chapterId") int chapterId,
            @Path("pageIndex") int pageIndex
    );

    //首页文章列表
    //https://www.wanandroid.com/article/list/0/json
    @GET("article/list/{pageIndex}/json")
    Observable<WanBaseModel<ListModel>> getHomeList(
            @Path("pageIndex") int pageIndex
    );

    //最新项目tab (首页的第二个tab)
    //https://wanandroid.com/article/listproject/0/json
    @GET("article/listproject/{pageIndex}/json")
    Observable<WanBaseModel<ListModel>> getProjectList(
            @Path("pageIndex") int pageIndex
    );

    // https://www.wanandroid.com/project/tree/json
    @GET("project/tree/json")
    Observable<ProjectTree> getProjectTree();

    //https://www.wanandroid.com/project/list/1/json?cid=294
    @GET("project/list/{pageIndex}/json")
    Observable<WanBaseModel<ListModel>> getProjectTreeTitle(
            @Path("pageIndex") int pageIndex,
            @Query("cid") long cid
    );

}
