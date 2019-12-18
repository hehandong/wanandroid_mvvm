/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hhd.wanandroid_mvvm.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hhd.wanandroid_mvvm.R;
import com.hhd.wanandroid_mvvm.databinding.RecycleItemHomeArticleBinding;
import com.hhd.wanandroid_mvvm.model.ArticleBean;
import com.hhd.wanandroid_mvvm.ui.callback.ArticleClickCallback;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ArticleViewHolder> {

    List<ArticleBean> mArticleList;

    @Nullable
    private final ArticleClickCallback mArticleClickCallback;

    public HomeAdapter(@Nullable ArticleClickCallback clickCallback) {
        mArticleClickCallback = clickCallback;
        setHasStableIds(true);
    }

    public void setArticleList(final List<ArticleBean> articleList) {
//        if (mArticleList == null) {
//            mArticleList = articleList;
//            notifyItemRangeInserted(0, articleList.size());
//        } else {
//            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
//                @Override
//                public int getOldListSize() {
//                    return mArticleList.size();
//                }
//
//                @Override
//                public int getNewListSize() {
//                    return articleList.size();
//                }
//
//                @Override
//                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//                    LogUtil.i(HomeFragment.TAG,"oldID:" + mArticleList.get(oldItemPosition).getId()
//                            + "newID:" + articleList.get(newItemPosition).getId()
//                            + "oldTitle:" + mArticleList.get(oldItemPosition).getTitle()
//                            + "newTitle:" + articleList.get(newItemPosition).getTitle());
//                    return mArticleList.get(oldItemPosition).getId() ==
//                            articleList.get(newItemPosition).getId();
//                }
//
//                @Override
//                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//                    ArticleBean newProduct = articleList.get(newItemPosition);
//                    ArticleBean oldProduct = mArticleList.get(oldItemPosition);
//                    return newProduct.getId() == oldProduct.getId()
//                            && Objects.equals(newProduct.getTitle(), oldProduct.getTitle());
//                }
//            });
//            mArticleList = articleList;
//            result.dispatchUpdatesTo(this);
//        }

        mArticleList = articleList;
            notifyItemRangeInserted(0, articleList.size());
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleItemHomeArticleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycle_item_home_article,
                parent, false);
        binding.setCallback(mArticleClickCallback);
        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.binding.setArticle(mArticleList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mArticleList == null ? 0 : mArticleList.size();
    }

    @Override
    public long getItemId(int position) {
        return mArticleList.get(position).getId();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        final RecycleItemHomeArticleBinding binding;

        public ArticleViewHolder(RecycleItemHomeArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
