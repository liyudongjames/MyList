package com.bwf.qingdan.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseEloquenceData;
import com.bwf.qingdan.gui.activity.MainActivity;
import com.bwf.qingdan.gui.activity.RecommendActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/26.
 */

public class NodesHeaderRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ResponseEloquenceData.DataBean.RankingsBean> rankingsBeenData;
    private LayoutInflater inflater;

    protected final int HEAD = 1001;
    protected final int FOOT = 1002;
    protected final int NORMAL = 1003;
    protected View footView;
    protected View headView;
    protected Context context;

    public NodesHeaderRecyclerViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        rankingsBeenData = new ArrayList<>();
    }

    public void addData(List<ResponseEloquenceData.DataBean.RankingsBean> data) {
        rankingsBeenData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD:
                return new MyHeaderHolder(headView);
            case NORMAL:
                View itemView = inflater.inflate(R.layout.main_recyclerview_head_item, parent, false);
                return new MyHolder(itemView);
            case FOOT:
                return new MyFooterHolder(footView);
        }
        View itemView = inflater.inflate(R.layout.main_recyclerview_head_item, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (!isFooterView(position)&&!isHeaderView(position)) {
            MyHolder myHolder = (MyHolder) holder;
            myHolder.imageViewNodesHeader.setImageURI(getItem(position).getThumbnailImageUrl());
            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity mainActivity = (MainActivity) context;
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",getItem(position).getId());
                    Intent intent = new Intent(mainActivity, RecommendActivity.class);
                    intent.putExtra("Data",bundle);
                    context.startActivity(intent);

                }
            });
        }else if (isFooterView(position)){
            MyFooterHolder myHolder = (MyFooterHolder) holder;
        }else if (isHeaderView(position)){
            MyHeaderHolder myHolder = (MyHeaderHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        int count = (rankingsBeenData == null ? 0 : rankingsBeenData.size());
        if (footView != null) {
            count++;
        }
        if (headView != null) {
            count++;
        }
        return count;
    }

    public ResponseEloquenceData.DataBean.RankingsBean getItem(int position) {
        //TODO  到时候要考虑header和footer
        if (haveHeaderView()) {
            return rankingsBeenData.get(position - 1);
        }
        return rankingsBeenData.get(position);
    }


    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return HEAD;
        } else if (isFooterView(position)) {
            return FOOT;
        } else {
            return NORMAL;
        }
    }

    public void addHeadView(View headView) {
        if (!haveHeaderView()) {
            this.headView = headView;
            notifyItemInserted(0);
        }
    }

    public void addFootView(View footView) {
        if (!haveFooterView()) {
            this.footView = footView;
            notifyItemInserted(getItemCount() - 1);
        }
    }

    protected boolean haveHeaderView() {
        return headView != null;
    }

    protected boolean haveFooterView() {
        return footView != null;
    }

    protected boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    protected boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    class MyHeaderHolder extends RecyclerView.ViewHolder {
        public MyHeaderHolder(View itemView) {
            super(itemView);
        }
    }

    class MyFooterHolder extends RecyclerView.ViewHolder {
        public MyFooterHolder(View itemView) {
            super(itemView);
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView_nodes_header)
        SimpleDraweeView imageViewNodesHeader;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
