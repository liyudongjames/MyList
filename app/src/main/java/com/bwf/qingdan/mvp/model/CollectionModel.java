package com.bwf.qingdan.mvp.model;

import com.bwf.qingdan.entity.ResponseCollection;

/**
 * Created by Administrator on 2016/11/1.
 */

public interface CollectionModel {
    void startCollection(String url,OnCollectionListener listener);

    interface OnCollectionListener{
        void onSuccess(ResponseCollection collection);
        void onFailed();
    }
}
