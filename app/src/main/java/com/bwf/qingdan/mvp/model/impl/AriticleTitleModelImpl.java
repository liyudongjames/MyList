package com.bwf.qingdan.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.qingdan.entity.ResponseArticleComment;
import com.bwf.qingdan.entity.ResponseArticleInterrelated;
import com.bwf.qingdan.entity.ResponseArticleTitleAndHtml;
import com.bwf.qingdan.mvp.model.ArticleTitleModel;
import com.bwf.qingdan.utils.http.HttpUtils;
import com.bwf.qingdan.utils.http.Request;
import com.bwf.qingdan.utils.http.qingdan.HttpClient;

/**
 * Created by Administrator on 2016/10/27.
 */

public class AriticleTitleModelImpl implements ArticleTitleModel {

    @Override
    public void startFindArticleTitle(String url, final ArticleTitleModel.onFindArticleTitleListener listener) {
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
                .get()
                .url(url);
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("AriticleTitleModelImpl", response);
                ResponseArticleTitleAndHtml responseArticleTitleAndHtml = JSON.parseObject(response,ResponseArticleTitleAndHtml.class);
                listener.onSuccess(responseArticleTitleAndHtml);

            }

            @Override
            public void onError() {
                listener.onFailed();
            }
        });
    }

    @Override
    public void startFindArticleComment(String url, final onFindArticleCommentListener listener) {
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
                .get()
                .url(url);
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("AriticleTitleModelImpl", response);
                ResponseArticleComment comment = JSON.parseObject(response,ResponseArticleComment.class);
                listener.onSuccess(comment);
            }

            @Override
            public void onError() {
                listener.onFailed();
            }
        });
    }

    @Override
    public void startFindArticleInterrelated(String url, final onFindArticleInterrelatedListener listener) {
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
                .get()
                .url(url);
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseArticleInterrelated responseArticle = JSON.parseObject(response,ResponseArticleInterrelated.class);
                listener.onSuccess(responseArticle);
            }

            @Override
            public void onError() {
                listener.onFailed();
            }
        });
    }
}
