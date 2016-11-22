package com.bwf.qingdan.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseSelectedData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/11.
 */

public class CategoryHotPageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ResponseSelectedData.DataBean.TagsBean.BodyBean.DataBean1.TagsBean1> tagsBeans;
    private LayoutInflater inflater;
    private Context context;

    public CategoryHotPageRecyclerViewAdapter(List<ResponseSelectedData.DataBean.TagsBean.BodyBean.DataBean1.TagsBean1> tagsBeans, Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.tagsBeans = tagsBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.categroy_hotpage_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if(position != getItemCount()-1){
            viewHolder.hotpageItemTextview.setText(tagsBeans.get(position).getName());
            viewHolder.hotpageItemImageview.setImageURI(tagsBeans.get(position).getCoverImage().getThumbnail().getImageUrl());
            viewHolder.hotpageItemImageview.setAspectRatio(1.5f);
        }else {
            viewHolder.hotpageItemImageview.setVisibility(View.GONE);
            viewHolder.hotpageItemTextview.setVisibility(View.GONE);
            viewHolder.hotpageItemMoreText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hotpage_item_imageview)
        SimpleDraweeView hotpageItemImageview;
        @BindView(R.id.hotpage_item_textview)
        TextView hotpageItemTextview;
        @BindView(R.id.hotpage_item_more_text)
        TextView hotpageItemMoreText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
