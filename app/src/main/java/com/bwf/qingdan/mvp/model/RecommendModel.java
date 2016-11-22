package com.bwf.qingdan.mvp.model;

import com.bwf.qingdan.entity.ResponseRecommendData;

/**
 * Created by Administrator on 2016/11/3.
 */

public interface RecommendModel {
    void startRecommendHotSearchData(String url,RecommendHotSearchDataCallBack callBack);
    void startRecommendScoreSearchData(String url,RecommendScoreSearchDataCallBack callBack);
    void startRecommendNameSearchData(String url,RecommendNameSearchDataCallBack callBack);

    interface RecommendHotSearchDataCallBack{
        void onHotSearchDataSuccess(ResponseRecommendData data);
        void onHotSearchDataFailed();
    }

    interface RecommendScoreSearchDataCallBack{
        void onScoreSearchDataSuccess(ResponseRecommendData data);
        void onScoreSearchDataFailed();
    }

    interface RecommendNameSearchDataCallBack{
        void onNameSearchDataSuccess(ResponseRecommendData data);
        void onNameSearchDataFailed();
    }
}
