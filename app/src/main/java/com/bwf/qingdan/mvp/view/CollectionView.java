package com.bwf.qingdan.mvp.view;

import com.bwf.qingdan.entity.ResponseCollection;

/**
 * Created by Administrator on 2016/11/1.
 */

public interface CollectionView {
    void onCollectionShow(ResponseCollection collection);
    void onCollectionFailed();
}
