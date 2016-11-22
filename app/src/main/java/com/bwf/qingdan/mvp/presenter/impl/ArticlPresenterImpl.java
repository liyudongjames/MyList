package com.bwf.qingdan.mvp.presenter.impl;

import com.bwf.qingdan.entity.ResponseArticleComment;
import com.bwf.qingdan.entity.ResponseArticleInterrelated;
import com.bwf.qingdan.entity.ResponseArticleTitleAndHtml;
import com.bwf.qingdan.mvp.model.ArticleTitleModel;
import com.bwf.qingdan.mvp.model.impl.AriticleTitleModelImpl;
import com.bwf.qingdan.mvp.presenter.ArticlePresenter;
import com.bwf.qingdan.mvp.view.ArticleView;

/**
 * Created by Administrator on 2016/10/27.
 */

public class ArticlPresenterImpl implements ArticlePresenter{

    private ArticleView view;
    private ArticleTitleModel model;

    public ArticlPresenterImpl(ArticleView view){
        model = new AriticleTitleModelImpl();
        this.view = view;
    }

    @Override
    public void wakeUpModelfindArticleTittle(String url) {
        model.startFindArticleTitle(url, new ArticleTitleModel.onFindArticleTitleListener() {
            @Override
            public void onSuccess(ResponseArticleTitleAndHtml responseArticleTitleAndHtml) {
                view.showTitle(responseArticleTitleAndHtml);
            }

            @Override
            public void onFailed() {
                view.getTitleFailed();
            }
        });
    }

    @Override
    public void wakeUpModelfindArticleComment(String url) {
        model.startFindArticleComment(url, new ArticleTitleModel.onFindArticleCommentListener() {
            @Override
            public void onSuccess(ResponseArticleComment comment) {
                view.showComment(comment);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void wakeUpModelfindArticleInterrelated(String url) {
        model.startFindArticleInterrelated(url, new ArticleTitleModel.onFindArticleInterrelatedListener() {
            @Override
            public void onSuccess(ResponseArticleInterrelated responseArticle) {
                view.showInterrelated(responseArticle);
            }

            @Override
            public void onFailed() {

            }
        });
    }

}
