package com.bwf.qingdan.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseAllCategorys;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/11.
 */

public class AllCategoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ResponseAllCategorys.DataBean.CategoriesBean> categoriesBeen;
    private LayoutInflater inflater;

    public AllCategoryRecyclerViewAdapter( Context context,List<ResponseAllCategorys.DataBean.CategoriesBean> categoriesBeen) {
        this.categoriesBeen = categoriesBeen;
        inflater = LayoutInflater.from(context);
        Log.d("AllCategoryRecyclerView", categoriesBeen.get(0).getName());
    }

    public void setData(List<ResponseAllCategorys.DataBean.CategoriesBean> categoriesBeen) {
        this.categoriesBeen = categoriesBeen;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.all_categorys_recyclerview_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        Log.d("AllCategoryRecyclerView", "position:" + position);
        ViewHolder viewHolder = (ViewHolder) holder;
        if(position == selectedPosition){
            Log.d("AllCategoryRecyclerView", categoriesBeen.get(position).getIcons().getActive().getImageUrl());
            viewHolder.allCategoryRecyclerviewItemImg.setImageURI(categoriesBeen.get(position).getIcons().getActive().getImageUrl());
        }else {
            Log.d("AllCategoryRecyclerView", categoriesBeen.get(position).getIcons().getInactive().getImageUrl());
            viewHolder.allCategoryRecyclerviewItemImg.setImageURI(categoriesBeen.get(position).getIcons().getInactive().getImageUrl());
        }
        if(listener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(v,position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        Log.d("AllCategoryRecyclerView", "categoriesBeen.size():" + categoriesBeen.size());
        return categoriesBeen.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.all_category_recyclerview_item_img)
        SimpleDraweeView allCategoryRecyclerviewItemImg;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private onRecyclerViewClickListener listener;
    public void setOnRecyclerViewClickListener(onRecyclerViewClickListener listener){
        this.listener = listener;
    }

    public interface onRecyclerViewClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    private int selectedPosition = 0;
    public void selected(int position){
        selectedPosition = position;
        notifyDataSetChanged();
    }

}
