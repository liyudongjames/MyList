package com.bwf.qingdan.mvp.model;

import com.bwf.qingdan.entity.ResponseAllCategorys;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface AllCategorysModel {
    void startGetAllCategorys(allCategoryGetListener listener);

    interface allCategoryGetListener{
        void getSuccess(ResponseAllCategorys categorys);
        void getFailed();
    }
}
