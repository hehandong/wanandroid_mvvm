package com.hhd.wanandroid_mvvm.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hehandong.retrofithelper.utils.RxUtil;
import com.hhd.wanandroid_mvvm.R;
import com.hhd.wanandroid_mvvm.databinding.FragmentHomeBinding;
import com.hhd.wanandroid_mvvm.model.ArticleBean;
import com.hhd.wanandroid_mvvm.model.ListModel;
import com.hhd.wanandroid_mvvm.model.Product;
import com.hhd.wanandroid_mvvm.net.WanAndroidManager;
import com.hhd.wanandroid_mvvm.net.costomCore.CustomObserver;
import com.hhd.wanandroid_mvvm.net.module.WanBaseModel;
import com.hhd.wanandroid_mvvm.utils.ToastUtil;
import com.hhd.wanandroid_mvvm.viewmodel.HomeViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private HomeViewModel mViewModel;
    private FragmentHomeBinding mBinding;
    private HomeAdapter mHomeAdapter;

    private boolean mAtTop = true;
    private static final int PAGE_SIZE = 6;
    private int mNextRequestPage = 1;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        initAdapter();
        initRefreshLayout();
        refresh();

        return mBinding.getRoot();
    }

    private void initAdapter() {
        mHomeAdapter = new HomeAdapter();
        mHomeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                updateUI();
            }
        }, mBinding.productsList);
        mHomeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mBinding.productsList.setAdapter(mHomeAdapter);

        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArticleBean item = mHomeAdapter.getItem(position);
                String title = item.getTitle();
                String link = item.getLink();
//                ((SupportFragment) getParentFragment()).start(ArticleFragment.newInstance(title, link));
            }
        });
    }

    private void initRefreshLayout() {
        mBinding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }



    private void refresh() {
        mNextRequestPage = 0;
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        mHomeAdapter.setEnableLoadMore(false);
        // Init Datas
        updateUI();
    }

    public void updateUI() {
        WanAndroidManager.getAPI()
                .getHomeList(mNextRequestPage)
                .compose(RxUtil.<WanBaseModel<ListModel>>rxSchedulerHelper())
                .subscribe(new CustomObserver<WanBaseModel<ListModel>>() {
                    @Override
                    public void onSuccess(WanBaseModel<ListModel> model) {
                        if (model.errorCode == 0) {
                            List<ArticleBean> datas = model.data.getDatas();
                            boolean isRefresh = mNextRequestPage == 0;
                            setData(isRefresh, datas);
                            if (isRefresh) {
                                mHomeAdapter.setEnableLoadMore(true);
                                mBinding.swipeLayout.setRefreshing(false);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtil.showToastLong(App.getInstance(),"ERROR");
                        boolean isRefresh = mNextRequestPage == 0;
                        if (isRefresh) {
                            mHomeAdapter.setEnableLoadMore(true);
                            mBinding.swipeLayout.setRefreshing(false);
                        } else {
                            mHomeAdapter.loadMoreFail();
                        }
                    }
                });
    }


    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mHomeAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mHomeAdapter.addData(data);
            }
        }

        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mHomeAdapter.loadMoreEnd(isRefresh);
            ToastUtil.showToastLong(App.getInstance(),"no more data");
        } else {
            mHomeAdapter.loadMoreComplete();
        }
    }


    //    private void subscribeUi(LiveData<List<ProductEntity>> liveData) {
//        // Update the list when the data changes
//        liveData.observe(this, new Observer<List<ProductEntity>>() {
//            @Override
//            public void onChanged(@Nullable List<ProductEntity> myProducts) {
//                if (myProducts != null) {
////                    mBinding.setIsLoading(false);
//                    mHomeAdapter.setProductList(myProducts);
//                } else {
////                    mBinding.setIsLoading(true);
//                }
//                // espresso does not know how to wait for data binding's loop so we execute changes
//                // sync.
//                mBinding.executePendingBindings();
//            }
//        });
//    }

    private final ProductClickCallback mProductClickCallback = new ProductClickCallback() {
        @Override
        public void onClick(Product product) {

//            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
//                ((MainActivity) getActivity()).show(product);
//            }
            Log.i("ProductListFragment", "mProductClickCallback");
        }
    };

}
