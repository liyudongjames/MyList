package com.bwf.qingdan.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.qingdan.entity.ResponseRecommendData;
import com.bwf.qingdan.mvp.model.RecommendModel;
import com.bwf.qingdan.utils.UnicodeParser;
import com.bwf.qingdan.utils.http.HttpUtils;
import com.bwf.qingdan.utils.http.Request;
import com.bwf.qingdan.utils.http.qingdan.HttpClient;

/**
 * Created by Administrator on 2016/11/3.
 */

public class RecommendModelImpl implements RecommendModel{

    @Override
    public void startRecommendHotSearchData(String url, final RecommendHotSearchDataCallBack callBack) {
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
                .get()
                .url(url);
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("RecommendModelImpl", response);
                ResponseRecommendData data = JSON.parseObject(response,ResponseRecommendData.class);
                callBack.onHotSearchDataSuccess(data);
            }

            @Override
            public void onError() {
                callBack.onHotSearchDataFailed();
            }
        });
    }

    @Override
    public void startRecommendScoreSearchData(String url, final RecommendScoreSearchDataCallBack callBack) {
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
                .get()
                .url(url);
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("RecommendModelImpl", response);
                ResponseRecommendData data = JSON.parseObject(response,ResponseRecommendData.class);
                callBack.onScoreSearchDataSuccess(data);
            }

            @Override
            public void onError() {
                callBack.onScoreSearchDataFailed();
            }
        });
    }

    @Override
    public void startRecommendNameSearchData(String url, final RecommendNameSearchDataCallBack callBack) {
        //{"code":0,"message":"Success","data":{"things":[],"meta":{"pagination":{"total":0,"count":0,"per_page":10,"current_page":1,"total_pages":0,"links":[]}}}}
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
                .get()
                .url(url);
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("RecommendModelImpl", response);
                ResponseRecommendData data = JSON.parseObject(response,ResponseRecommendData.class);
                callBack.onNameSearchDataSuccess(data);
            }

            @Override
            public void onError() {
                callBack.onNameSearchDataFailed();
            }
        });
    }
}
