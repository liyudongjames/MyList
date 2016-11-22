package com.bwf.qingdan.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseSelectedData;
import com.bwf.qingdan.gui.adapter.CategoryHotPageRecyclerViewAdapter;
import com.bwf.qingdan.gui.adapter.CategoryRecyclerViewAdapter;
import com.bwf.qingdan.mvp.presenter.SelectedPresenter;
import com.bwf.qingdan.mvp.presenter.impl.SelectedPresenterImpl;
import com.bwf.qingdan.mvp.view.ShowSelectedView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LG on 2016/10/14.
 * Tips:
 */

public class CategoryFragment extends FragmentBase implements ShowSelectedView {
    @BindView(R.id.category_recyclerView)
    RecyclerView categoryRecyclerView;
    @BindView(R.id.hot_tag_recyclerView)
    RecyclerView hotTagRecyclerView;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_category;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showSelectedToView(ResponseSelectedData data) {
        Log.d("CategoryFragment", data.getMessage());
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(data.getData().getCategories().getBody().getData().getCategories(),getActivity());
        CategoryHotPageRecyclerViewAdapter hotPageAdapter = new CategoryHotPageRecyclerViewAdapter(data.getData().getTags().getBody().getData().getTags(),getActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        GridLayoutManager hotPagelayoutManager = new GridLayoutManager(getActivity(),3);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(adapter);
        hotTagRecyclerView.setLayoutManager(hotPagelayoutManager);
        hotTagRecyclerView.setAdapter(hotPageAdapter);
    }

    @Override
    public void getSelectedDataFailed() {
        Log.d("CategoryFragment", "selected Failed");
    }

    @Override
    public void loadingSelectedData() {
        Log.d("CategoryFragment", "isLoading");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        SelectedPresenter presenter = new SelectedPresenterImpl(this);
        presenter.startSelectedData();
        return rootView;
    }
}
