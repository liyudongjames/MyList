package com.bwf.qingdan.mvp.model;

import com.bwf.qingdan.entity.ResponseMainListData;

import java.util.List;

/**
 * Created by LG on 2016/10/24.
 * Tips:
 */

public interface MainListModel {
    void loadNextPageDatas(String url,Callback callback);

    public interface Callback{
        void loadCollectionsSuccess(List<ResponseMainListData.DataBean.CollectionsBean> collections);
        void loadNodesSuccess(List<ResponseMainListData.DataBean.NodesBean> nodes);
        void loadArticlesSuccess(List<ResponseMainListData.DataBean.ArticlesBean> articles);
        void loadFailed();
        void noMoreData();
    }
}
