package com.hhd.wanandroid_mvvm.viewmodel;

import android.app.Application;

import com.hhd.wanandroid_mvvm.db.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class ProductListViewModel extends AndroidViewModel {


    private final MediatorLiveData<List<ProductEntity>> mObservableProducts;

    public ProductListViewModel(@NonNull Application application) {
        super(application);
        mObservableProducts = new MediatorLiveData<>();

        List<ProductEntity> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(i);
            productEntity.setName("计数：" + i);
            productEntity.setPrice(i);
            temp.add(productEntity);
        }

        mObservableProducts.setValue(temp);

    }
    // TODO: Implement the ViewModel

    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }


}
