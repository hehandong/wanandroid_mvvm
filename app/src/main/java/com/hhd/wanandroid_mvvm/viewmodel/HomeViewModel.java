package com.hhd.wanandroid_mvvm.viewmodel;

import android.app.Application;

import com.hehandong.retrofithelper.utils.RxUtil;
import com.hhd.wanandroid_mvvm.model.ArticleBean;
import com.hhd.wanandroid_mvvm.model.ListModel;
import com.hhd.wanandroid_mvvm.net.WanAndroidManager;
import com.hhd.wanandroid_mvvm.net.costomCore.CustomObserver;
import com.hhd.wanandroid_mvvm.net.module.WanBaseModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class HomeViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<ArticleBean>> mObservableArticleList;
    private int mNextRequestPage = 0;
    private final List<ArticleBean> mArticleList;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mObservableArticleList = new MediatorLiveData<>();
        mArticleList = new ArrayList<>();
        updateData();
    }
    // TODO: Implement the ViewModel

    public LiveData<List<ArticleBean>> getArticleList() {
        return mObservableArticleList;
    }


    public void updateData() {
        WanAndroidManager.getAPI()
                .getHomeList(mNextRequestPage)
                .compose(RxUtil.<WanBaseModel<ListModel>>rxSchedulerHelper())
                .subscribe(new CustomObserver<WanBaseModel<ListModel>>() {
                    @Override
                    public void onSuccess(WanBaseModel<ListModel> model) {
                        if (model.errorCode == 0) {
                            List<ArticleBean> datas = model.data.getDatas();
                            mNextRequestPage++;
                            if (!mArticleList.containsAll(datas)){
                                mArticleList.addAll(datas);
                            }
                            mObservableArticleList.setValue(mArticleList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
//                        ToastUtil.showToastLong(App.getInstance(),"ERROR");
                    }
                });
    }

    public void refresh() {
        mNextRequestPage = 0;
        updateData();
    }
}
