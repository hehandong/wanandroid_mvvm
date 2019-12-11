package com.hhd.wanandroid_mvvm.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhd.wanandroid_mvvm.R;
import com.hhd.wanandroid_mvvm.databinding.FragmentHomeBinding;
import com.hhd.wanandroid_mvvm.db.entity.ProductEntity;
import com.hhd.wanandroid_mvvm.model.Product;
import com.hhd.wanandroid_mvvm.viewmodel.HomeViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment {


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private HomeViewModel mViewModel;
    private FragmentHomeBinding mBinding;
    private HomeAdapter mHomeAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        mHomeAdapter = new HomeAdapter(mProductClickCallback);
        mBinding.productsList.setAdapter(mHomeAdapter);
        subscribeUi(mViewModel.getProducts());
        return mBinding.getRoot();
    }

    private void subscribeUi(LiveData<List<ProductEntity>> liveData) {
        // Update the list when the data changes
        liveData.observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntity> myProducts) {
                if (myProducts != null) {
//                    mBinding.setIsLoading(false);
                    mHomeAdapter.setProductList(myProducts);
                } else {
//                    mBinding.setIsLoading(true);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private final ProductClickCallback mProductClickCallback = new ProductClickCallback() {
        @Override
        public void onClick(Product product) {

//            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
//                ((MainActivity) getActivity()).show(product);
//            }

            Log.i("ProductListFragment","mProductClickCallback");
        }
    };

}
