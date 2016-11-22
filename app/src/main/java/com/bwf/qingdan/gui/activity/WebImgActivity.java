package com.bwf.qingdan.gui.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;

import com.bwf.qingdan.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/11/9.
 */

public class WebImgActivity extends ActivityBase implements GestureDetector.OnDoubleTapListener,GestureDetector.OnGestureListener{
    private SimpleDraweeView img;
    private GestureDetector gestureDetector;
    private boolean isScale = false;
    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {
        img = (SimpleDraweeView) findViewById(R.id.web_show_image);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        img.setAspectRatio(0.5F);
        img.setImageURI(Uri.parse(url));
        gestureDetector = new GestureDetector(this,this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.web_image_show;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if(!isScale) {
            startAnimation(img,1f,2f);
            isScale = true;
        }else{
            startAnimation(img,2f,1f);
            isScale = false;
        }
        return false;
    }

    public void startAnimation(View view, float x, float y){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"scale",x,y).setDuration(200);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float change = (float) animation.getAnimatedValue();
                img.setScaleX(change);
                img.setScaleY(change);
            }
        });
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
