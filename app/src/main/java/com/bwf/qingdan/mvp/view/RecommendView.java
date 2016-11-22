package com.bwf.qingdan.mvp.view;

import com.bwf.qingdan.entity.ResponseRecommendData;

/**
 * Created by Administrator on 2016/11/3.
 */

public interface RecommendView {
    void setRecommendHotSearchData(ResponseRecommendData data);
    void setRecommendScoreSearchData(ResponseRecommendData data);
    void setRecommendNameSearchData(ResponseRecommendData data);

    void setRecommentHotSearchDatafailed();
    void setRecommendScoreSearchDatafailed();
    void setRecommendNameSearchDatafailed();
}
