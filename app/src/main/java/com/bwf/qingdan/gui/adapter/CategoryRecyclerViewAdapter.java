package com.bwf.qingdan.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseSelectedData;
import com.bwf.qingdan.gui.activity.AllCategorysActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/9.
 */

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ResponseSelectedData.DataBean.CategoriesBean.BodyBean.DataBean2.CategoriesBean1> categoriesBeans;
    private LayoutInflater inflater;
    private Context context;

    private int[] colors = {R.color.little_dark_blue,
            R.color.sky_blue, R.color.face_color, R.color.light_coffee,
            R.color.little_dark_blue, R.color.sky_blue, R.color.face_color,
            R.color.light_coffee};

    public CategoryRecyclerViewAdapter(List<ResponseSelectedData.DataBean.CategoriesBean.BodyBean.DataBean2.CategoriesBean1> categoriesBeans,
                                       Context context) {
        this.categoriesBeans = categoriesBeans;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_item, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if (position != getItemCount() - 1) {
            myViewHolder.categoryItemTitle.setText(categoriesBeans.get(position).getName());
            myViewHolder.categoryItemImg.setImageURI(Uri.parse(categoriesBeans.get(position).getFeaturedImageUrl()));

            myViewHolder.categoryItemThings.setBackgroundColor(context.getResources().getColor(colors[position]));
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, categoriesBeans.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            myViewHolder.categoryItemMoreText.setVisibility(View.VISIBLE);
            myViewHolder.categoryItemThings.setVisibility(View.GONE);
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AllCategorysActivity.class);
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_item_title)
        TextView categoryItemTitle;
        @BindView(R.id.category_item_img)
        SimpleDraweeView categoryItemImg;
        @BindView(R.id.category_item_things)
        LinearLayout categoryItemThings;
        @BindView(R.id.category_item_more_text)
        TextView categoryItemMoreText;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
