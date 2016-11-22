package com.bwf.qingdan.mvp.model;

import com.bwf.qingdan.entity.ResponseArticleComment;
import com.bwf.qingdan.entity.ResponseArticleInterrelated;
import com.bwf.qingdan.entity.ResponseArticleTitleAndHtml;

/**
 * Created by Administrator on 2016/10/27.
 */

public interface ArticleTitleModel {
    void startFindArticleTitle(String url,ArticleTitleModel.onFindArticleTitleListener listener);
    interface onFindArticleTitleListener{
        void onSuccess(ResponseArticleTitleAndHtml responseArticleTitleAndHtml);
        void onFailed();
    }

    void startFindArticleComment(String url,onFindArticleCommentListener listener);
    interface onFindArticleCommentListener{
        void onSuccess(ResponseArticleComment response);
        void onFailed();
    }

    void startFindArticleInterrelated(String ulr,onFindArticleInterrelatedListener listener);
    interface onFindArticleInterrelatedListener{
        void onSuccess(ResponseArticleInterrelated response);
        void onFailed();
    }

}
