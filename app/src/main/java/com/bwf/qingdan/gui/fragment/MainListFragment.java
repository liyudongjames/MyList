package com.bwf.qingdan.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseEloquenceData;
import com.bwf.qingdan.entity.ResponseMainListData;
import com.bwf.qingdan.gui.activity.MainActivity;
import com.bwf.qingdan.gui.adapter.ArticlesRecycleViewAdapter;
import com.bwf.qingdan.gui.adapter.BaseMainListRecycleViewAdapter;
import com.bwf.qingdan.gui.adapter.CollectionsRecycleViewAdapter;
import com.bwf.qingdan.gui.adapter.NodesHeaderRecyclerViewAdapter;
import com.bwf.qingdan.gui.adapter.NodesRecycleViewAdapter;
import com.bwf.qingdan.mvp.presenter.MainListPresenter;
import com.bwf.qingdan.mvp.presenter.impl.MainListPresenterImpl;
import com.bwf.qingdan.mvp.view.MainListView;
import com.bwf.qingdan.utils.Contants;
import com.bwf.qingdan.utils.http.HttpUtils;
import com.bwf.qingdan.utils.http.Request;
import com.bwf.qingdan.utils.http.qingdan.HttpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LG on 2016/10/20.
 * Tips:
 */

public class MainListFragment extends FragmentBase  implements MainListView{
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_main_list;
    }
    /***代表是哪一种数据类型**/
    private int dataCategoryTag;
    /**代表访问数据的接口**/
    private String urlTag;

    private MainListPresenter presenter;
    private BaseMainListRecycleViewAdapter recycleViewAdapter;
    private LinearLayoutManager layoutManager;

    private LayoutInflater inflater;
    public static MainListFragment newInstance(int categoryTag,String urlTag){
        MainListFragment fragment = new MainListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("dataCategoryTag",categoryTag);
        bundle.putString("urlTag",urlTag);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager);

        Bundle bundle = getArguments();
        dataCategoryTag = bundle.getInt("dataCategoryTag");
        urlTag = bundle.getString("urlTag");
        Log.d("MainListFragment", "dataCategoryTag:" + dataCategoryTag);
        Log.d("MainListFragment", "urlTag:" + urlTag);

        //根据数据的类型 来创建对应的Adapter
        switch (dataCategoryTag){
            case Contants.TAG_ARTICLES:
                recycleViewAdapter = new ArticlesRecycleViewAdapter(getActivity());
                break;
            case Contants.TAG_COLLECTIONS:
                recycleViewAdapter = new CollectionsRecycleViewAdapter(getActivity());
                break;
            case Contants.TAG_NODES:
                recycleViewAdapter = new NodesRecycleViewAdapter(getActivity());
                break;
        }
        recycleViewAdapter.setOnRestryDataListener(new BaseMainListRecycleViewAdapter.OnRestryDataListener() {
            @Override
            public void onRestry() {
                presenter.loadNextPageDatas();
                isLoading = true;
            }
        });
        recycleView.setAdapter(recycleViewAdapter);

        recycleView.addOnScrollListener(onScrollListener);

        MainFragment fragment = (MainFragment) (((MainActivity)getActivity()).getMainFragment());
        fragment.addOnFabClickListener(urlTag,new MainFragment.onFabClickListener() {
            @Override
            public void onFabClick() {
                recycleView.smoothScrollToPosition(0);
            }
        });

        //创建Presenter对象
        presenter = new MainListPresenterImpl(this,urlTag);
        presenter.loadNextPageDatas();
        isLoading = true;
    }

    private boolean isNoMoreData;
    private boolean isLoading;
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if(isNoMoreData){//如果没有下一页数据就不再触发获取下一页数据
                return;
            }
            if(!isLoading && layoutManager.findLastVisibleItemPosition() == layoutManager.getItemCount() - 1){
                isLoading = true;
                presenter.loadNextPageDatas();
            }

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void showNodesToView(List<ResponseMainListData.DataBean.NodesBean> nodes) {
        recycleViewAdapter.addDatas(nodes);
        inflater = LayoutInflater.from(getActivity());
        final View view = inflater.inflate(R.layout.main_recyclerview_head,recycleView,false);
        HttpClient client = new HttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url("http://api.eqingdan.com/v1/rankings/front")
                .get();
        client.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseEloquenceData data = JSON.parseObject(response, ResponseEloquenceData.class);
                NodesHeaderRecyclerViewAdapter adapter = new NodesHeaderRecyclerViewAdapter(getActivity());
                adapter.addData(data.getData().getRankings());
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.main_head_recyclerview);
                recyclerView.setAdapter(adapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(layoutManager);
                View header = inflater.inflate(R.layout.img_ranking_topictitle_layout,recyclerView,false);
                View footer = inflater.inflate(R.layout.img_ranking_alltopics_layout,recyclerView,false);
                adapter.addHeadView(header);
                adapter.addFootView(footer);
            }

            @Override
            public void onError() {

            }
        });

        recycleViewAdapter.addHeadView(view);
        isLoading = false;
    }

    @Override
    public void showArticlesToView(List<ResponseMainListData.DataBean.ArticlesBean> articles) {
        recycleViewAdapter.addDatas(articles);
        isLoading = false;

    }

    @Override
    public void showCollectionsToView(List<ResponseMainListData.DataBean.CollectionsBean> collections) {
        recycleViewAdapter.addDatas(collections);
        isLoading = false;
    }

    @Override
    public void showRecycleViewFooterLoading() {
        recycleViewAdapter.changeFootView(BaseMainListRecycleViewAdapter.IS_LOADING);
    }

    @Override
    public void showRecycleViewFooterLoadFailed() {
        isLoading = false;
        recycleViewAdapter.changeFootView(BaseMainListRecycleViewAdapter.LOADING_FAILED);
    }

    @Override
    public void showRecycleViewFooterNoMoreData() {
        isNoMoreData = true;
        Toast.makeText(getActivity(),"没有下一页了！",Toast.LENGTH_SHORT).show();
        recycleViewAdapter.changeFootView(BaseMainListRecycleViewAdapter.NO_MORE_DATE);
    }
}
