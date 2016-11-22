package com.bwf.qingdan.gui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseRecommendData;
import com.bwf.qingdan.gui.adapter.RecommendListViewAdapter;
import com.bwf.qingdan.mvp.presenter.RecommendPresenter;
import com.bwf.qingdan.mvp.presenter.impl.RecommendPresenterImpl;
import com.bwf.qingdan.mvp.view.RecommendView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/3.
 */

public class RecommendActivity extends ActivityBase implements RecommendView {

    public final int HOT = 1;
    public final int SCORE = 2;
    public final int NAME = 3;

    @BindView(R.id.recommend_tittle)
    TextView recommendTittle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recommend_search_textView)
    EditText recommendSearchTextView;
    @BindView(R.id.recommend_hot_textView)
    TextView recommendHotTextView;
    @BindView(R.id.recommend_score_textView)
    TextView recommendScoreTextView;
    @BindView(R.id.recommend_name_textView)
    TextView recommendNameTextView;
    @BindView(R.id.recommend_listView)
    ListView recommendListView;
    @BindView(R.id.recommend_search_delete_imageview)
    ImageView recommendSearchDeleteImageview;
    @BindView(R.id.recommend_search_cancel_textview)
    TextView recommendSearchCancelTextview;

    private void setTextColor(int id) {
        recommendHotTextView.setTextColor(Color.BLACK);
        recommendNameTextView.setTextColor(Color.BLACK);
        recommendScoreTextView.setTextColor(Color.BLACK);
        switch (id) {
            case HOT:
                recommendHotTextView.setTextColor(getResources().getColor(R.color.purple));
                break;
            case SCORE:
                recommendScoreTextView.setTextColor(getResources().getColor(R.color.purple));
                break;
            case NAME:
                recommendNameTextView.setTextColor(getResources().getColor(R.color.purple));
                break;
        }
    }

    @Override
    protected void initDatas() {

    }

    private RecommendPresenter presenter;
    private int id;
    private String keyWord="";

    @Override
    protected void initViews() {
        id = getIntent().getBundleExtra("Data").getInt("id");
        Log.d("RecommendActivity", "id:" + id);
        presenter = new RecommendPresenterImpl(this);
        presenter.startRecommendHotSearchData(id,keyWord,1);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.recommend_activity;
    }

    private int targetList;

    private int hotPage = 1;
    private int namePage = 1;
    private int scorePage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        adapter = new RecommendListViewAdapter(this,null);
        recommendListView.setAdapter(adapter);
        recommendHotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetList = HOT;
                setTextColor(targetList);
                presenter.startRecommendHotSearchData(id,keyWord,1);
            }
        });

        recommendScoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetList = SCORE;
                setTextColor(targetList);
                presenter.startRecommendScoreSearchData(id,keyWord,1);
            }
        });

        recommendNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetList = NAME;
                setTextColor(targetList);
                presenter.startRecommendNameSearchData(id,keyWord,1);
            }
        });
        targetList = HOT;
        setTextColor(HOT);

        editTextListener();

        recommendSearchDeleteImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendSearchTextView.setText("");
            }
        });

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.recommend_listview_footer, null);
        recommendListView.addFooterView(view);
        recommendListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (view.getLastVisiblePosition() == view.getCount() - 1 ) {
                    switch (targetList){
                        case HOT:
                            presenter.startRecommendHotSearchData(id,keyWord,hotPage);
                            break;
                        case NAME:
                            presenter.startRecommendNameSearchData(id,keyWord,namePage);
                            break;
                        case SCORE:
                            presenter.startRecommendScoreSearchData(id,keyWord,scorePage);
                            break;
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        recommendSearchCancelTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (targetList){
                    case HOT:
                        recommendSearchTextView.setText("");
                        presenter.startRecommendHotSearchData(id,keyWord,1);
                        break;
                    case SCORE:
                        recommendSearchTextView.setText("");
                        presenter.startRecommendScoreSearchData(id,keyWord,1);
                        break;
                    case NAME:
                        recommendSearchTextView.setText("");
                        presenter.startRecommendNameSearchData(id,keyWord,1);
                        break;
                }
            }
        });
    }

    private void editTextListener(){
        recommendSearchTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyWord = s.toString();
                if(s.toString().isEmpty()){
                    recommendSearchDeleteImageview.setVisibility(View.INVISIBLE);
                }else{
                    recommendSearchDeleteImageview.setVisibility(View.VISIBLE);
                }
            }
        });

        recommendSearchTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    recommendSearchCancelTextview.setVisibility(View.VISIBLE);
                }
            }
        });

        recommendSearchTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    switch (targetList) {
                        case HOT:
                            hotPage = 1;
                            presenter.startRecommendHotSearchData(id, keyWord,1);
                            break;
                        case SCORE:
                            scorePage = 1;
                            presenter.startRecommendScoreSearchData(id, keyWord,1);
                            break;
                        case NAME:
                            namePage = 1;
                            presenter.startRecommendNameSearchData(id, keyWord,1);
                            break;
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private RecommendListViewAdapter adapter;
    @Override
    public void setRecommendHotSearchData(ResponseRecommendData data) {
        int currentPage = data.getData().getMeta().getPagination().getCurrent_page();
        Log.d("RecommendActivity", data.getData().getMeta().getPagination().getCurrent_page() + "");
        if(data.getData().getMeta().getPagination().getTotal_pages() == hotPage - 1)return;
        if(data.getData().getMeta().getPagination().getCurrent_page() == 1) {
            adapter.setBeanList(data.getData().getThings());
        }else{
            adapter.addBeanList(data.getData().getThings());
        }
        if(data.getData().getMeta().getPagination().getTotal_pages()==1) {
            hotPage = 1;
        }else {
            hotPage = currentPage + 1;
        }
    }

    @Override
    public void setRecommendScoreSearchData(ResponseRecommendData data) {
        int currentPage = data.getData().getMeta().getPagination().getCurrent_page();
        Log.d("RecommendActivity", data.getData().getMeta().getPagination().getCurrent_page() + "");
        if(data.getData().getMeta().getPagination().getTotal_pages() == scorePage - 1)return;
        if(data.getData().getMeta().getPagination().getCurrent_page() == 1) {
            adapter.setBeanList(data.getData().getThings());
        }else{
            adapter.addBeanList(data.getData().getThings());
        }
        if(data.getData().getMeta().getPagination().getTotal_pages()==1) {
            scorePage = 1;
        }else {
            scorePage = currentPage + 1;
        }
    }

    @Override
    public void setRecommendNameSearchData(ResponseRecommendData data) {
        int currentPage = data.getData().getMeta().getPagination().getCurrent_page();
        Log.d("RecommendActivity", data.getData().getMeta().getPagination().getCurrent_page() + "");
        if(data.getData().getMeta().getPagination().getCurrent_page() == 1) {
            adapter.setBeanList(data.getData().getThings());
        }else{
            adapter.addBeanList(data.getData().getThings());
        }
        if(data.getData().getMeta().getPagination().getTotal_pages()==1) {
            namePage = 1;
        }else {
            namePage = currentPage + 1;
        }
    }

    @Override
    public void setRecommentHotSearchDatafailed() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRecommendScoreSearchDatafailed() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRecommendNameSearchDatafailed() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }
}
