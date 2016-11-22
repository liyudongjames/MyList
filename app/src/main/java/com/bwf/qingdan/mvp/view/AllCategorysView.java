package com.bwf.qingdan.mvp.view;

import com.bwf.qingdan.entity.ResponseAllCategorys;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface AllCategorysView {
    void startLoadingAll();
    void loadAllCategorysSuccess(ResponseAllCategorys categorys);
    void loadAllCategorysFailed();
    void hideLoadingAll();
}
