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

package com.hhd.wanandroid_mvvm.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhd.wanandroid_mvvm.R;
import com.hhd.wanandroid_mvvm.model.ArticleBean;

public class HomeAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {
    public HomeAdapter() {
        super(R.layout.item_home2, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        if (item != null) {
            helper.setText(R.id.tv_type, item.getChapterName());
            helper.setText(R.id.tv_title, item.getTitle());
            TextView contentTv = helper.getView(R.id.tv_content);
            //item.getDesc()如果包含标记符<p>，显示出来不好看
            if (TextUtils.isEmpty(item.getDesc()) || item.getDesc().contains("<p>")) {
                contentTv.setVisibility(View.GONE);
            } else {
                contentTv.setVisibility(View.VISIBLE);
                contentTv.setText(item.getDesc());
            }

            helper.setText(R.id.tv_date, item.getNiceDate());
            String author = App.getInstance().getString(R.string.author) + item.getAuthor();
            helper.setText(R.id.tv_author, author);

        }
    }

}
