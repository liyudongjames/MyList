package com.bwf.qingdan.mvp.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.bwf.qingdan.entity.ResponseRecommendData;
import com.bwf.qingdan.mvp.model.RecommendModel;
import com.bwf.qingdan.mvp.model.impl.RecommendModelImpl;
import com.bwf.qingdan.mvp.presenter.RecommendPresenter;
import com.bwf.qingdan.mvp.view.RecommendView;
import com.bwf.qingdan.utils.UnicodeParser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/11/3.
 */

public class RecommendPresenterImpl implements RecommendPresenter{

    private RecommendView view;
    private RecommendModel model;

    public RecommendPresenterImpl(RecommendView view){
        this.view = view;
        model = new RecommendModelImpl();
    }

    @Override
    public void startRecommendHotSearchData(int id, String key,int page) {
        key = URLEncoder.encode(key);
        String url = "http://api.eqingdan.com/v1/rankings/"+ id +"/things?keyword=" + key + "&sort=review-count-desc&page=" + page;
        model.startRecommendHotSearchData(url, new RecommendModel.RecommendHotSearchDataCallBack() {
            @Override
            public void onHotSearchDataSuccess(ResponseRecommendData data) {
                Log.d("RecommendPresenterImpl", data.getMessage());
                view.setRecommendHotSearchData(data);
            }

            @Override
            public void onHotSearchDataFailed() {
                view.setRecommentHotSearchDatafailed();
            }
        });
    }

    @Override
    public void startRecommendScoreSearchData(int id, String key,int page) {
        key = URLEncoder.encode(key);
        String url = "http://api.eqingdan.com/v1/rankings/" + id +"/things?keyword=" + key + "&sort=rating-score-desc&page=" + page;
        model.startRecommendScoreSearchData(url, new RecommendModel.RecommendScoreSearchDataCallBack() {
            @Override
            public void onScoreSearchDataSuccess(ResponseRecommendData data) {
                Log.d("RecommendPresenterImpl", data.getMessage());
                view.setRecommendScoreSearchData(data);
            }

            @Override
            public void onScoreSearchDataFailed() {
                view.setRecommendScoreSearchDatafailed();
            }
        });
    }


    @Override
    public void startRecommendNameSearchData(int id, String key,int page) {
        key = URLEncoder.encode(key);
        String url = "http://api.eqingdan.com/v1/rankings/"+ id +"/things?keyword="+ key +"&sort=brand-name-asc&page=" + page;
        model.startRecommendNameSearchData(url, new RecommendModel.RecommendNameSearchDataCallBack() {
            @Override
            public void onNameSearchDataSuccess(ResponseRecommendData data) {
                Log.d("RecommendPresenterImpl", data.getMessage());
                view.setRecommendNameSearchData(data);
            }

            @Override
            public void onNameSearchDataFailed() {
                view.setRecommendNameSearchDatafailed();
            }
        });
    }
}
