package com.bwf.qingdan.mvp.presenter;

import com.bwf.qingdan.entity.ResponseAllCategorys;
import com.bwf.qingdan.mvp.model.AllCategorysModel;
import com.bwf.qingdan.mvp.model.impl.AllCategorysModelImpl;
import com.bwf.qingdan.mvp.view.AllCategorysView;

/**
 * Created by Administrator on 2016/11/11.
 */

public class AllCategorysPresenterImpl implements AllCategorysPresenter{
    private AllCategorysModel model;
    private AllCategorysView view;

    public AllCategorysPresenterImpl(AllCategorysView view){
        this.view = view;
        this.model = new AllCategorysModelImpl();
    }

    @Override
    public void getAllCategorys() {
        view.startLoadingAll();
        model.startGetAllCategorys(new AllCategorysModel.allCategoryGetListener() {
            @Override
            public void getSuccess(ResponseAllCategorys categorys) {
                view.hideLoadingAll();
                view.loadAllCategorysSuccess(categorys);
            }

            @Override
            public void getFailed() {
                view.hideLoadingAll();
                view.loadAllCategorysFailed();
            }
        });
    }
}
