package com.bwf.qingdan.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bwf.qingdan.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LG on 2016/10/25.
 * Tips:
 */

public abstract class BaseMainListRecycleViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> datas;

    protected final int HEAD = 1001;
    protected final int FOOT = 1002;
    protected final int NORMAL = 1003;
    protected View footView;
    protected View headView;

    protected Context context;
    protected LayoutInflater inflater;

    public BaseMainListRecycleViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
        this.context = context;
    }

    public interface OnRestryDataListener{
        void onRestry();
    }
    private OnRestryDataListener listener;
    public void setOnRestryDataListener(OnRestryDataListener listener){
        this.listener = listener;
    }
    /**
     * 新增数据
     *
     * @param datas
     */
    public void addDatas(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        //TODO  到时候要考虑header和footer
        int count = (datas == null ? 1 : datas.size() + 1);
//        if (footView != null) {
//            count++;
//        }

        if (headView != null) {
            count++;
        }
        return count;
    }

    /**
     * 获取对应位置的数据
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        //TODO  到时候要考虑header和footer
        if (haveHeaderView()) {
            return datas.get(position - 1);
        }
        return datas.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD:
                return new MyViewHolder(headView);
            case NORMAL:
                View itemView = inflater.inflate(R.layout.main_list_item, parent, false);
                return new MyViewHolder(itemView);
            case FOOT:
                footView = inflater.inflate(R.layout.main_recyclerview_footer, parent, false);
                return new FootViewHolder(footView);
        }
        View itemView = inflater.inflate(R.layout.main_list_item, parent, false);
        return new MyViewHolder(itemView);
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

    public static final int IS_LOADING = 1005;
    public static final int NO_MORE_DATE = 1006;
    public static final int LOADING_FAILED = 1007;
    protected int foot_State = IS_LOADING;

    public void changeFootView(int state){
        switch (state){
            case IS_LOADING:
                foot_State = IS_LOADING;
                notifyDataSetChanged();
                break;
            case NO_MORE_DATE:
                foot_State = NO_MORE_DATE;
                notifyDataSetChanged();
                break;
            case LOADING_FAILED:
                foot_State = LOADING_FAILED;
                notifyDataSetChanged();
                break;
        }
    }

    public void addHeadView(View headView) {
        if (!haveHeaderView()) {
            this.headView = headView;
            notifyItemInserted(0);
        }
    }

    protected boolean haveHeaderView() {
        return headView != null;
    }

    protected boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    protected boolean isFooterView(int position) {
        return position == getItemCount() - 1;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView_front_top_image)
        SimpleDraweeView imageViewFrontTopImage;
        @BindView(R.id.textView_front_main_title)
        TextView textViewFrontMainTitle;
        @BindView(R.id.textView_front_subtitle)
        TextView textViewFrontSubtitle;
        @BindView(R.id.textView_front_num_liked)
        TextView textViewFrontNumLiked;
        @BindView(R.id.textView_num_reviews)
        TextView textViewNumReviews;
        @BindView(R.id.rotate_textView_date)
        TextView rotateTextViewDate;
        @BindView(R.id.linear_bottom_count)
        LinearLayout linearBottomCount;
        @BindView(R.id.rela_special_tag)
        TextView ralaSpecialTag;
//        @BindView(R.id.header_text_view)
//        TextView headerTextView;

        MyViewHolder(View view) {
            super(view);
//            ButterKnife.bind(this, view);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.footer_progress_bar)
        ProgressBar footerProgressBar;
        @BindView(R.id.footer_textview_no_more_data)
        TextView footerTextviewNoMoreData;
        @BindView(R.id.footer_textview_is_failed)
        TextView footerTextviewIsFailed;

        FootViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            footerTextviewIsFailed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onRestry();
                    }
                }
            });
        }
    }
}
