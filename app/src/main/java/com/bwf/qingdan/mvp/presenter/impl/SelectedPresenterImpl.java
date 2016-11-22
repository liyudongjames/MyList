package com.bwf.qingdan.mvp.presenter.impl;

import com.bwf.qingdan.entity.ResponseSelectedData;
import com.bwf.qingdan.mvp.model.SelectedModel;
import com.bwf.qingdan.mvp.model.impl.SelectedModelImpl;
import com.bwf.qingdan.mvp.presenter.SelectedPresenter;
import com.bwf.qingdan.mvp.view.ShowSelectedView;

/**
 * Created by Administrator on 2016/11/9.
 */

public class SelectedPresenterImpl implements SelectedPresenter{
    private SelectedModel model;
    private ShowSelectedView view;

    public SelectedPresenterImpl(ShowSelectedView view){
        this.view = view;
        model = new SelectedModelImpl();
    }

    @Override
    public void startSelectedData() {
        model.getSelectedData(new SelectedModel.onSelectedDataListener() {
            @Override
            public void selectedDataSuccess(ResponseSelectedData data) {
                view.showSelectedToView(data);
            }

            @Override
            public void selectedDataLoading() {
                view.loadingSelectedData();
            }

            @Override
            public void selectedDataFailed() {
                view.getSelectedDataFailed();
            }
        });
    }
}
