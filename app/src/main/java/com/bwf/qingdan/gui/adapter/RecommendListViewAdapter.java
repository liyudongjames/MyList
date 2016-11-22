package com.bwf.qingdan.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseRecommendData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/3.
 */

public class RecommendListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ResponseRecommendData.DataBean.ThingsBean> beanList;

    public RecommendListViewAdapter(Context context, List<ResponseRecommendData.DataBean.ThingsBean> beanList) {
        inflater = LayoutInflater.from(context);
        this.beanList = beanList;
    }

    public void addBeanList(List<ResponseRecommendData.DataBean.ThingsBean> beanList){
        this.beanList.addAll(beanList);
        notifyDataSetChanged();
    }

    public void setBeanList(List<ResponseRecommendData.DataBean.ThingsBean> beanList){
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(beanList == null)
            return 0;
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recommend_listview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.recommendListviewTitle.setText(beanList.get(position).getFullName());
        viewHolder.recommendListviewSubtittle.setText(beanList.get(position).getName());
        viewHolder.recommendListviewItemRatingbar.setRating(beanList.get(position).getRatingScore());
        viewHolder.recommendListviewItemRatingbar.setClickable(false);
        viewHolder.recommendListviewItemHeadImageview.setImageURI(Uri.parse(beanList.get(position).getFeaturedImageUrl()));
        if(beanList.get(position).getRatingScore() != 0) {
            viewHolder.recommendListviewScoreTextview.setText((float) beanList.get(position).getRatingScore() + "分");
            viewHolder.recommendListviewCommentcountTextview.setText("(" + beanList.get(position).getReviewCount() + "人点评)");
        }else {
            viewHolder.recommendListviewScoreTextview.setText("暂无评分");
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.recommend_listview_item_head_imageview)
        ImageView recommendListviewItemHeadImageview;
        @BindView(R.id.recommend_listview_title)
        TextView recommendListviewTitle;
        @BindView(R.id.recommend_listview_subtittle)
        TextView recommendListviewSubtittle;
        @BindView(R.id.recommend_listview_item_ratingbar)
        RatingBar recommendListviewItemRatingbar;
        @BindView(R.id.recommend_listview_score_textview)
        TextView recommendListviewScoreTextview;
        @BindView(R.id.recommend_listview_commentcount_textview)
        TextView recommendListviewCommentcountTextview;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
