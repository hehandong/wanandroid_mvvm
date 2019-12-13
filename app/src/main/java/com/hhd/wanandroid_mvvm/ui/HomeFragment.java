package com.hhd.wanandroid_mvvm.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhd.wanandroid_mvvm.R;
import com.hhd.wanandroid_mvvm.databinding.FragmentHomeBinding;
import com.hhd.wanandroid_mvvm.model.ArticleBean;
import com.hhd.wanandroid_mvvm.ui.adapter.HomeAdapter;
import com.hhd.wanandroid_mvvm.utils.LogUtil;
import com.hhd.wanandroid_mvvm.viewmodel.HomeViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private HomeViewModel mViewModel;
    private FragmentHomeBinding mBinding;
    private HomeAdapter mHomeAdapter;


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
        mBinding.swipeLayout.setRefreshing(true);

        subscribeUi(mViewModel.getArticleList());

        LogUtil.i(TAG,"onCreateView");

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i(TAG,"onActivityCreated");
    }

    private void initAdapter() {
        mHomeAdapter = new HomeAdapter();
        mHomeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                  mViewModel.updateData();
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

    private void subscribeUi(LiveData<List<ArticleBean>> liveData) {
        // Update the list when the data changes
        liveData.observe(this, new Observer<List<ArticleBean>>() {
            @Override
            public void onChanged(@Nullable List<ArticleBean> articleList) {
                if (articleList != null) {
                    mBinding.setIsLoading(false);
                    mHomeAdapter.setNewData(articleList);
                } else {
                    mBinding.setIsLoading(true);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private void initRefreshLayout() {
        mBinding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.refresh();
                mHomeAdapter.setEnableLoadMore(true);
                mBinding.swipeLayout.setRefreshing(false);
            }
        });
    }




}
