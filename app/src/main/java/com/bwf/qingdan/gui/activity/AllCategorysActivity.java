package com.bwf.qingdan.gui.activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseAllCategorys;
import com.bwf.qingdan.gui.adapter.AllCategoryRecyclerViewAdapter;
import com.bwf.qingdan.gui.view.FlowLayout;
import com.bwf.qingdan.mvp.presenter.AllCategorysPresenter;
import com.bwf.qingdan.mvp.presenter.AllCategorysPresenterImpl;
import com.bwf.qingdan.mvp.view.AllCategorysView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/11.
 */

public class AllCategorysActivity extends ActivityBase implements AllCategorysView {
    @BindView(R.id.all_categorsys_recyclerview)
    RecyclerView allCategorsysRecyclerview;
    @BindView(R.id.all_category_scroll)
    ScrollView allCategoryScroll;
    @BindView(R.id.all_category_container_layout)
    LinearLayout allCategoryContainerLayout;

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {
        AllCategorysPresenter presenter = new AllCategorysPresenterImpl(this);
        presenter.getAllCategorys();
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.all_categorys_activity;
    }

    @Override
    public void startLoadingAll() {

    }

    @Override
    public void loadAllCategorysSuccess(final ResponseAllCategorys categorys) {
        Log.d("AllCategorysActivity", categorys.getMessage());
        Toast.makeText(this, categorys.getMessage(), Toast.LENGTH_SHORT).show();
        allCategoryRecyclerViewAdapter = new AllCategoryRecyclerViewAdapter(this,categorys.getData().getCategories());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allCategoryRecyclerViewAdapter.setOnRecyclerViewClickListener(new AllCategoryRecyclerViewAdapter.onRecyclerViewClickListener() {
            @Override
            public void onItemClick(View view,int position) {
                allCategoryRecyclerViewAdapter.selected(position);
                setFlowLayout(categorys,position);
            }

            @Override
            public void onItemLongClick(View view,int position) {
                allCategoryRecyclerViewAdapter.selected(position);
                setFlowLayout(categorys,position);
            }
        });
        allCategorsysRecyclerview.setAdapter(allCategoryRecyclerViewAdapter);
        allCategorsysRecyclerview.setLayoutManager(layoutManager);
        setFlowLayout(categorys,0);
    }

    public void setFlowLayout(ResponseAllCategorys categorys,int position){
        allCategoryContainerLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        List<ResponseAllCategorys.DataBean.CategoriesBean.ChildrenBean> childrenBeen
                = categorys.getData().getCategories().get(position).getChildren();
        for(int i = 0; i < childrenBeen.size(); i++){
            View view = inflater.inflate(R.layout.categorys_flow_layout,null);
            TextView textView = (TextView) view.findViewById(R.id.category_flow_title);
            FlowLayout flowLayout = (FlowLayout) view.findViewById(R.id.category_flow_flowlayout);
            textView.setText(childrenBeen.get(i).getName());
            List<ResponseAllCategorys.DataBean.CategoriesBean.ChildrenBean.ChildrenBean1> childrenBean1s =
                    childrenBeen.get(i).getChildren();
            for(int j = 0 ; j < childrenBean1s.size();j++){
                TextView childText = new TextView(this);
                childText.setBackgroundColor(Color.GRAY);
                childText.setTextSize(20);
                childText.setPadding(10,10,10,10);
                childText.setText(childrenBean1s.get(j).getName());
                flowLayout.addView(childText);
            }
            ViewGroup.LayoutParams params =
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            allCategoryContainerLayout.addView(view,params);
        }
    }

    @Override
    public void loadAllCategorysFailed() {
        Log.d("AllCategorysActivity", "failed");
        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoadingAll() {

    }

    private AllCategoryRecyclerViewAdapter allCategoryRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
