package com.bwf.qingdan.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseCollection;
import com.bwf.qingdan.gui.adapter.CollectionListViewAdapter;
import com.bwf.qingdan.mvp.presenter.CollectionPresenter;
import com.bwf.qingdan.mvp.presenter.impl.CollectionPresenterImpl;
import com.bwf.qingdan.mvp.view.CollectionView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/2.
 */

public class CollectionActivity extends ActivityBase implements CollectionView{

    @BindView(R.id.imageView_collection_front_top_image)
    SimpleDraweeView imageViewCollectionFrontTopImage;
    @BindView(R.id.collection_rela_special_tag)
    TextView collectionRelaSpecialTag;
    @BindView(R.id.collction_listView)
    ListView collctionListView;
    @BindView(R.id.collction_show_layout)
    LinearLayout collctionShowLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.collction_progressbar_layout)
    LinearLayout collctionProgressbarLayout;
    @BindView(R.id.collction_title_textView)
    TextView collctionTittleTextView;
    @BindView(R.id.collction_subtittle_textView)
    TextView collectionSubTittleTextView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this, getWindow().getDecorView());
        CollectionPresenter presenter = new CollectionPresenterImpl(this);
        int id = (int) getIntent().getBundleExtra("Data").get("id");
        presenter.loadCollection(id);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_collection;
    }

    @Override
    public void onCollectionShow(ResponseCollection collection) {
        collctionProgressbarLayout.setVisibility(View.GONE);
        collctionShowLayout.setVisibility(View.VISIBLE);
        final List<ResponseCollection.DataBean.CollectionsBean.BodyBean.DataBean1.ArticlesBean> beens = collection.getData().getCollections().getBody().getData().getArticles();
        imageViewCollectionFrontTopImage.setImageURI(collection.getData().getCollection().getBody().getData().getFeaturedImageUrl());
        collctionTittleTextView.setText(collection.getData().getCollection().getBody().getData().getTitle());
        collectionSubTittleTextView.setText(collection.getData().getCollection().getBody().getData().getExcerpt());
        CollectionListViewAdapter adapter = new CollectionListViewAdapter(this,beens);
        collctionListView.setAdapter(adapter);
        collctionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CollectionActivity.this,Article_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",beens.get(position).getId());
                intent.putExtra("Data",bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCollectionFailed() {
        Toast.makeText(this, "加载失败", Toast.LENGTH_SHORT).show();
    }
}
