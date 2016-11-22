package com.bwf.qingdan.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseCollection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/2.
 */

public class CollectionListViewAdapter extends BaseAdapter {
    private List<ResponseCollection.DataBean.CollectionsBean.BodyBean.DataBean1.ArticlesBean> beens;
    private LayoutInflater inflater;

    public CollectionListViewAdapter(Context context, List<ResponseCollection.DataBean.CollectionsBean.BodyBean.DataBean1.ArticlesBean> beens) {
        this.beens = beens;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beens.size();
    }

    @Override
    public Object getItem(int position) {
        return beens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.collection_listview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.collectionListviewItemImageView.setImageURI(Uri.parse(beens.get(position).getFeaturedImageUrl()));
        viewHolder.collectionListViewItemTittleTextView.setText(beens.get(position).getTitle());
        viewHolder.collectionLikeTextView.setText(beens.get(position).getLikeCount()+"");
        viewHolder.collectionViewTextView.setText(beens.get(position).getHitCount()+"");
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.collection_listview_item_imageView)
        ImageView collectionListviewItemImageView;
        @BindView(R.id.collection_listView_item_tittle_textView)
        TextView collectionListViewItemTittleTextView;
        @BindView(R.id.collection_like_imageView)
        ImageView collectionLikeImageView;
        @BindView(R.id.collection_like_textView)
        TextView collectionLikeTextView;
        @BindView(R.id.collection_view_imageView)
        ImageView collectionViewImageView;
        @BindView(R.id.collection_view_textView)
        TextView collectionViewTextView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
