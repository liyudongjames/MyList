package com.bwf.qingdan.mvp.presenter.impl;

import android.util.Log;

import com.bwf.qingdan.entity.ResponseMainListData;
import com.bwf.qingdan.mvp.view.MainListView;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MainListPresenterImplTest implements MainListView{
    private MainListPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainListPresenterImpl(this,"http://api.eqingdan.com/v1/categories/nursing/articles?page=1");

    }

    @Test
    public void loadNextPageDatas() throws Exception {
        presenter.loadNextPageDatas();
    }

    @Override
    public void showNodesToView(List<ResponseMainListData.DataBean.NodesBean> nodes) {
        Log.d("MainListPresenterImplTe", "nodes.size():" + nodes.size());
    }

    @Override
    public void showArticlesToView(List<ResponseMainListData.DataBean.ArticlesBean> articles) {

    }

    @Override
    public void showCollectionsToView(List<ResponseMainListData.DataBean.CollectionsBean> collections) {

    }

    @Override
    public void showRecycleViewFooterLoading() {

    }

    @Override
    public void showRecycleViewFooterLoadFailed() {

    }

    @Override
    public void showRecycleViewFooterNoMoreData() {

    }
}