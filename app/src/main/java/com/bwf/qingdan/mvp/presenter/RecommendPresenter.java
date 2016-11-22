package com.bwf.qingdan.mvp.presenter;

/**
 * Created by Administrator on 2016/11/3.
 */

public interface RecommendPresenter {
    void startRecommendHotSearchData(int id,String key,int page);
    void startRecommendScoreSearchData(int id,String key,int page);
    void startRecommendNameSearchData(int id,String key,int page);
}
