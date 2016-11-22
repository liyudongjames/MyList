package com.bwf.qingdan.mvp.view;

import com.bwf.qingdan.entity.ResponseSelectedData;

/**
 * Created by Administrator on 2016/11/9.
 */

public interface ShowSelectedView {
    void showSelectedToView(ResponseSelectedData data);

    void getSelectedDataFailed();
    void loadingSelectedData();
}
