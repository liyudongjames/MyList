package com.bwf.qingdan.mvp.view;

import com.bwf.qingdan.entity.ResponseArticleComment;
import com.bwf.qingdan.entity.ResponseArticleInterrelated;
import com.bwf.qingdan.entity.ResponseArticleTitleAndHtml;

/**
 * Created by Administrator on 2016/10/27.
 */

public interface ArticleView {
    void showTitle(ResponseArticleTitleAndHtml responseArticleTitleAndHtml);
    void getTitleFailed();
    void showInterrelated(ResponseArticleInterrelated responseArticle);
    void getInterrelatedFailed();
    void showComment(ResponseArticleComment comment);
    void getCommentFailed();
}
