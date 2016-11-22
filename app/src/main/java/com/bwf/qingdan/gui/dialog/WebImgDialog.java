package com.bwf.qingdan.gui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2016/11/9.
 */

public class WebImgDialog extends Dialog{
    public WebImgDialog(Context context) {
        super(context);
    }

    public WebImgDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected WebImgDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder{
        private Context context;
        private View convertView;
        public Builder(Context context){
            this.context = context;
        }

        public Builder getConvertView(View convertView){
            this.convertView = convertView;
            return this;
        }

        public WebImgDialog create(){
            WebImgDialog dialog = new WebImgDialog(context);
            dialog.setContentView(convertView);
            return dialog;
        }

        public WebImgDialog create(int themeResId){
            WebImgDialog dialog = new WebImgDialog(context,themeResId);
            dialog.setContentView(convertView);
            return dialog;
        }
    }
}
