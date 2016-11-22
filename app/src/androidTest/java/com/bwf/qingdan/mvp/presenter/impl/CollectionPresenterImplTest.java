package com.bwf.qingdan.mvp.presenter.impl;

import android.util.Log;

import com.bwf.qingdan.entity.ResponseCollection;
import com.bwf.qingdan.mvp.view.CollectionView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CollectionPresenterImplTest implements CollectionView{
    private CollectionPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new CollectionPresenterImpl(this);
    }

    @Test
    public void loadCollection() throws Exception {
        presenter.loadCollection(78);
    }

    @Override
    public void onCollectionShow(ResponseCollection collection) {
        Log.d("CollectionPresenterImpl", collection.getData().getCollections().getBody().getData().getArticles().get(0).getTitle());
    }

    @Override
    public void onCollectionFailed() {

    }
}