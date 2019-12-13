package com.hhd.wanandroid_mvvm.ui.diffUtil;


import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhd.wanandroid_mvvm.R;
import com.hhd.wanandroid_mvvm.model.ArticleBean;
import com.hhd.wanandroid_mvvm.ui.App;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Create adapter
 */
public class HomeDiffAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {
    public static final int TITLE_PAYLOAD = 899;
    public static final int CONTENT_PAYLOAD = 900;
    public static final int ITEM_0_PAYLOAD = 901;

    public HomeDiffAdapter(List<ArticleBean> list) {
        super(R.layout.item_home2, list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ArticleBean item) {
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

    /**
     * This method will only be executed when there is payload info
     *
     * 当有 payload info 时，只会执行此方法
     *
     * @param helper   A fully initialized helper.
     * @param item     The item that needs to be displayed.
     * @param payloads payload info.
     */
    @Override
    protected void convertPayloads(@NonNull BaseViewHolder helper, ArticleBean item, @NonNull List<Object> payloads) {
        for (Object p : payloads) {
            int payload = (int) p;
            if (payload == TITLE_PAYLOAD) {

            } else if (payload == CONTENT_PAYLOAD) {

            } else if (payload == ITEM_0_PAYLOAD) {

            }
        }
    }
}
