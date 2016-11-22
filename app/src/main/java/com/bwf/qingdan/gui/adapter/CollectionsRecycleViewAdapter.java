package com.bwf.qingdan.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwf.qingdan.entity.ResponseMainListData;
import com.bwf.qingdan.gui.activity.Article_Activity;
import com.bwf.qingdan.gui.activity.CollectionActivity;
import com.bwf.qingdan.gui.activity.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by LG on 2016/10/24.
 * Tips:
 */

public class CollectionsRecycleViewAdapter extends BaseMainListRecycleViewAdapter<ResponseMainListData.DataBean.CollectionsBean>{

    public CollectionsRecycleViewAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!isFooterView(position)&&!isHeaderView(position)) {
            MyViewHolder mHolder = (MyViewHolder) holder;
            ButterKnife.bind(mHolder, holder.itemView);
//            MyViewHolder mHolder = (MyViewHolder) holder;
            final ResponseMainListData.DataBean.CollectionsBean nodesBean = getItem(position);
            mHolder.imageViewFrontTopImage.setImageURI(nodesBean.getFeaturedImageUrl());
            mHolder.textViewFrontMainTitle.setText(nodesBean.getTitle());
            mHolder.textViewFrontSubtitle.setText(nodesBean.getSubtitle());
            mHolder.linearBottomCount.setVisibility(View.GONE);
            mHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity mainActivity = (MainActivity) context;
                    Intent intent = new Intent(mainActivity, CollectionActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",nodesBean.get_id());
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
