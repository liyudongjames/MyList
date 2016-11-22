package com.bwf.qingdan.mvp.model.impl;

import com.alibaba.fastjson.JSON;
import com.bwf.qingdan.entity.ResponseCollection;
import com.bwf.qingdan.mvp.model.CollectionModel;
import com.bwf.qingdan.utils.http.FormBody;
import com.bwf.qingdan.utils.http.HttpUtils;
import com.bwf.qingdan.utils.http.Request;
import com.bwf.qingdan.utils.http.RequestBody;
import com.bwf.qingdan.utils.http.StringBody;
import com.bwf.qingdan.utils.http.qingdan.HttpClient;

/**
 * Created by Administrator on 2016/11/1.
 */

public class CollectionModelImpl implements CollectionModel{
    @Override
    public void startCollection(String url, final OnCollectionListener listener) {
        RequestBody body = new FormBody.Builder()
                .add("requests",url)
                .build();

        Request.Builder builder = new Request.Builder()
                .addHeader("qd-manufacturer", "Xiaomi")
                .addHeader("qd-model", "MI 5")
                .addHeader("qd-os", "Android")
                .addHeader("qd-os-version","6.0")
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
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseCollection collection = JSON.parseObject(response,ResponseCollection.class);
                listener.onSuccess(collection);
            }

            @Override
            public void onError() {
                listener.onFailed();
            }
        });
    }
}
