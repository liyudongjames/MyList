package com.bwf.qingdan.mvp.model;

import com.bwf.qingdan.entity.ResponseSelectedData;

/**
 * Created by Administrator on 2016/11/9.
 */

public interface SelectedModel {
    void getSelectedData(onSelectedDataListener listener);

    interface onSelectedDataListener{
        void selectedDataSuccess(ResponseSelectedData data);
        void selectedDataLoading();
        void selectedDataFailed();
    }
}
