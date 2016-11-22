package com.bwf.qingdan.mvp.presenter.impl;

import com.bwf.qingdan.entity.ResponseCollection;
import com.bwf.qingdan.mvp.model.CollectionModel;
import com.bwf.qingdan.mvp.model.impl.CollectionModelImpl;
import com.bwf.qingdan.mvp.presenter.CollectionPresenter;
import com.bwf.qingdan.mvp.view.CollectionView;

/**
 * Created by Administrator on 2016/11/1.
 */

public class CollectionPresenterImpl implements CollectionPresenter{

    private CollectionModel model;
    private CollectionView view;

    public CollectionPresenterImpl(CollectionView view){
        this.view = view;
        model = new CollectionModelImpl();
    }

    @Override
    public void loadCollection(int id) {
        String url = "{\"collections\":{\"method\":\"GET\",\"relative_url\":\"/v1/collections/"+id+"/articles\"},\"collection\":{\"method\":\"GET\",\"relative_url\":\"/v1/collections/"+ id +"\"}}";
        model.startCollection(url, new CollectionModel.OnCollectionListener() {
            @Override
            public void onSuccess(ResponseCollection collection) {

                view.onCollectionShow(collection);
            }

            @Override
            public void onFailed() {
                view.onCollectionFailed();
            }
        });
    }
}
