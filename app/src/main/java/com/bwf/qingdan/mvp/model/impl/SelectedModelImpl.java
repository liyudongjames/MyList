package com.bwf.qingdan.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.qingdan.entity.ResponseSelectedData;
import com.bwf.qingdan.mvp.model.SelectedModel;
import com.bwf.qingdan.utils.http.FormBody;
import com.bwf.qingdan.utils.http.HttpUtils;
import com.bwf.qingdan.utils.http.Request;
import com.bwf.qingdan.utils.http.RequestBody;
import com.bwf.qingdan.utils.http.qingdan.HttpClient;

/**
 * Created by Administrator on 2016/11/9.
 */

public class SelectedModelImpl implements SelectedModel{
    @Override
    public void getSelectedData(final onSelectedDataListener listener) {
        RequestBody body = new FormBody.Builder()
                .add("requests","{\"tags\":{\"method\":\"GET\",\"relative_url\":\"/v1/tags/recommended\"},\"categories\":{\"method\":\"GET\",\"relative_url\":\"/v1/categories\"}}")
                .build();
        Request.Builder builder = new Request.Builder()
                .addHeader("qd-manufacturer", "Xiaomi")
                .addHeader("qd-model", "MI 5")
                .addHeader("qd-os", "Android")
                .addHeader("qd-os-version", "6.0")
                .addHeader("qd-screen-width", "1080")
                .addHeader("qd-screen-height", "1920")
                .addHeader("qd-carrier", "%E4%B8%AD%E5%9B%BD%E8%81%94%E9%80%9A")
                .addHeader("qd-network-type", "WIFI")
                .addHeader("qd-app-id", "com.eqingdan")
                .addHeader("qd-app-version", "2.6")
                .addHeader("qd-app-channel", "mi")
                .addHeader("qd-track-device-id", "eb51c9b1f01ac05c32170fc4cf18d0e7")
                .addHeader("User-Agent", "EQingDan/2.5 (Android; okhttp/2.4.0)")
                .post(body)
                .url("http://api.eqingdan.com/v1/batching");
                listener.selectedDataLoading();
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("SelectedModelImpl", response);
                ResponseSelectedData data = JSON.parseObject(response,ResponseSelectedData.class);
                listener.selectedDataSuccess(data);
            }

            @Override
            public void onError() {
                listener.selectedDataFailed();
            }
        });
    }
}
