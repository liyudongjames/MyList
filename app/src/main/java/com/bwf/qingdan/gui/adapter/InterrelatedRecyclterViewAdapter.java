package com.bwf.qingdan.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseArticleInterrelated;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/31.
 */

public class InterrelatedRecyclterViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    List<ResponseArticleInterrelated.DataBean.ArticlesBean> data;

    public InterrelatedRecyclterViewAdapter(List<ResponseArticleInterrelated.DataBean.ArticlesBean> data , Context context) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.interrelated_recycler_view_itme, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.interrelatiedRecyclerviewItemTextView.setText(data.get(position).getTitle());
        myHolder.interrelatiedRecyclerviewItemImageView.setImageURI(data.get(position).getFeaturedImageUrl());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.interrelatied_recyclerview_item_imageView)
        public SimpleDraweeView interrelatiedRecyclerviewItemImageView;
        @BindView(R.id.interrelatied_recyclerview_item_textView)
        public TextView interrelatiedRecyclerviewItemTextView;

        public MyHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
