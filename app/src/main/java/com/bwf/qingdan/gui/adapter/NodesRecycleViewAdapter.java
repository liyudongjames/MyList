package com.bwf.qingdan.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwf.qingdan.entity.ResponseMainListData;
import com.bwf.qingdan.gui.activity.Article_Activity;
import com.bwf.qingdan.gui.activity.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by LG on 2016/10/24.
 * Tips:
 */

public class NodesRecycleViewAdapter extends BaseMainListRecycleViewAdapter<ResponseMainListData.DataBean.NodesBean> {

    public NodesRecycleViewAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!isFooterView(position)&&!isHeaderView(position)) {
            MyViewHolder mHolder = (MyViewHolder) holder;
            ButterKnife.bind(mHolder, holder.itemView);
//            MyViewHolder mHolder = (MyViewHolder) holder;
            final ResponseMainListData.DataBean.NodesBean nodesBean = getItem(position);
            mHolder.imageViewFrontTopImage.setImageURI(nodesBean.getFeaturedImageUrl());
            mHolder.textViewFrontMainTitle.setText(nodesBean.getTitle());
            mHolder.textViewFrontSubtitle.setText(nodesBean.getSubtitle());
            mHolder.textViewFrontNumLiked.setText(nodesBean.getLikeCount() + "");
            mHolder.textViewNumReviews.setText(nodesBean.getHitCount() + "");
            if (!nodesBean.getCategories().isEmpty()) {
                mHolder.ralaSpecialTag.setText(nodesBean.getCategories().get(0).getName());
            }else{
                mHolder.ralaSpecialTag.setVisibility(View.INVISIBLE);
            }
            mHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity mainActivity = (MainActivity) context;
                    Intent intent = new Intent(mainActivity, Article_Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",nodesBean.getTarget_id());
                    intent.putExtra("Data",bundle);
                    context.startActivity(intent);
                }
            });
        }else if(isFooterView(position)){
            FootViewHolder mHolder = (FootViewHolder) holder;
            switch (foot_State){
                case IS_LOADING:
                    mHolder.footerProgressBar.setVisibility(View.VISIBLE);
                    mHolder.footerTextviewIsFailed.setVisibility(View.INVISIBLE);
                    mHolder.footerTextviewNoMoreData.setVisibility(View.INVISIBLE);
                    break;
                case NO_MORE_DATE:
                    mHolder.footerProgressBar.setVisibility(View.INVISIBLE);
                    mHolder.footerTextviewIsFailed.setVisibility(View.INVISIBLE);
                    mHolder.footerTextviewNoMoreData.setVisibility(View.VISIBLE);
                    break;
                case LOADING_FAILED:
                    mHolder.footerProgressBar.setVisibility(View.INVISIBLE);
                    mHolder.footerTextviewIsFailed.setVisibility(View.VISIBLE);
                    mHolder.footerTextviewNoMoreData.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }
}
