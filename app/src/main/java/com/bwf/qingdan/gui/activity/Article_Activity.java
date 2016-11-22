package com.bwf.qingdan.gui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.qingdan.R;
import com.bwf.qingdan.entity.ResponseArticleComment;
import com.bwf.qingdan.entity.ResponseArticleInterrelated;
import com.bwf.qingdan.entity.ResponseArticleTitleAndHtml;
import com.bwf.qingdan.gui.adapter.InterrelatedRecyclterViewAdapter;
import com.bwf.qingdan.gui.dialog.WebImgDialog;
import com.bwf.qingdan.gui.view.NoScrollRecyclerView;
import com.bwf.qingdan.mvp.presenter.ArticlePresenter;
import com.bwf.qingdan.mvp.presenter.impl.ArticlPresenterImpl;
import com.bwf.qingdan.mvp.view.ArticleView;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Article_Activity extends ActivityBase implements ArticleView {

    @BindView(R.id.imageView_article_front_top_image)
    SimpleDraweeView imageViewArticleFrontTopImage;
    @BindView(R.id.article_rela_special_tag)
    TextView articleRelaSpecialTag;
    @BindView(R.id.article_title)
    TextView articleTitle;
    @BindView(R.id.article_writer_textView)
    TextView articleWriterTextView;
    @BindView(R.id.article_writer_detail_textView)
    TextView articleWriterDetailTextView;
    @BindView(R.id.article_writer_head_imgview)
    SimpleDraweeView articleWriterHeadImgview;
    @BindView(R.id.article_web_view)
    WebView articleWebView;
    @BindView(R.id.article_like_framelayout)
    FrameLayout articleLikeFramelayout;
    @BindView(R.id.article_comment_framelayout)
    FrameLayout articleCommentFramelayout;
    @BindView(R.id.article_share_framelayout)
    FrameLayout articleShareFramelayout;
    @BindView(R.id.article_bottom_linear)
    LinearLayout articleBottomLinear;
    @BindView(R.id.article_time_textView)
    TextView articleTimeTextView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.article_like_count_textView)
    TextView articleLikeCountTextView;
    @BindView(R.id.article_comment_count_textView)
    TextView articleCommentCountTextView;
    @BindView(R.id.article_share_textView)
    TextView articleShareTextView;
    @BindView(R.id.article_comment_write_comment_textView)
    TextView articleCommentWriteCommentTextView;
    @BindView(R.id.article_comment_xuxian)
    View articleCommentXuxian;
    @BindView(R.id.article_comment_no_comment_imageView)
    ImageView articleCommentNoCommentImageView;
    @BindView(R.id.article_comment_no_comment_textView)
    TextView articleCommentNoCommentTextView;
    @BindView(R.id.article_comment_detail_head_imageView)
    SimpleDraweeView articleCommentDetailHeadImageView;
    @BindView(R.id.article_comment_detail_nikename_textView)
    TextView articleCommentDetailNikenameTextView;
    @BindView(R.id.article_comment_detail_time_textView)
    TextView articleCommentDetailTimeTextView;
    @BindView(R.id.article_comment_detail_comment_content)
    TextView articleCommentDetailCommentContent;
    @BindView(R.id.article_comment_detail_reply_imageView)
    ImageView articleCommentDetailReplyImageView;
    @BindView(R.id.article_comment_detail_likeCount_textView)
    TextView articleCommentDetailLikeCountTextView;
    @BindView(R.id.article_comment_detail_like_imageView)
    ImageView articleCommentDetailLikeImageView;
    @BindView(R.id.article_comment_layout)
    LinearLayout articleCommentLayout;
    @BindView(R.id.article_comment_more_comment_textView)
    TextView articleCommentMoreCommentTextView;
    @BindView(R.id.article_comment_more_comment_layout)
    FrameLayout articleCommentMoreCommentLayout;
    @BindView(R.id.article_comment_sub_layout)
    LinearLayout articleCommentSubLayout;
    @BindView(R.id.InterrelatedRecyclerView)
    NoScrollRecyclerView InterrelatedRecyclerView;
    @BindView(R.id.article_refresh)
    MaterialRefreshLayout ArticleRefresh;

    private int id;
    private String url;
    private ArticlePresenter presenter;
    private String commentUrl;
    private String interrelatedUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra("Data");
        id = (int) bundle.get("id");
        url = "http://api.eqingdan.com/v1/articles/" + id + "";
        presenter = new ArticlPresenterImpl(this);
        presenter.wakeUpModelfindArticleTittle(url);
        commentUrl = "http://api.eqingdan.com/v1/comments/hot?target_type=article&target_id=" + id + "&page=1&per_page=4";
        presenter.wakeUpModelfindArticleComment(commentUrl);
        interrelatedUrl = "http://api.eqingdan.com/v1/articles/" + id + "/related-articles";
        presenter.wakeUpModelfindArticleInterrelated(interrelatedUrl);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        articleWebView.destroy();
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this, getWindow().getDecorView());
        ArticleRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                presenter.wakeUpModelfindArticleTittle(url);
                presenter.wakeUpModelfindArticleComment(commentUrl);
                presenter.wakeUpModelfindArticleInterrelated(interrelatedUrl);
            }
        });
        setWebView();
    }

    private void setWebView(){
        WebSettings settings = articleWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        articleWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //在当前WebView加载url
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                articleWebView.loadUrl("javascript:(function(){\n" +
                        "var objs = document.getElementsByTagName(\"img\");  \n" +
                        "for(var i=0;i<objs.length;i++)  \n" +
                        "{\n" +
                        "    objs[i].onclick=function()  \n" +
                        "    \t{\n" +
                        "        \tBWF.clickImg(this.src);  \n" +
                        "    \t}\n" +
                        "}\n" +
                        "    })()");

                Toast.makeText(Article_Activity.this, "注入成功", Toast.LENGTH_SHORT).show();
            }
        });
        settings.setSupportZoom(false);  //设置webview是否支持缩放
        settings.setDefaultTextEncodingName("utf-8");//设置默认编码
        articleWebView.addJavascriptInterface(new JavaScripInterface(),"BWF");
    }

    class JavaScripInterface{
        @android.webkit.JavascriptInterface
        public void clickImg(String url){
            Toast.makeText(Article_Activity.this, url, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Article_Activity.this,WebImgActivity.class);
            intent.putExtra("url",url);
            startActivity(intent);
        }
    }



    @Override
    public void onBackPressed() {
        if(articleWebView.canGoBack()){ //如果WebView能返回
            articleWebView.goBack(); //返回上一个网页
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_article;
    }

    @Override
    public void showTitle(ResponseArticleTitleAndHtml responseArticleTitleAndHtml) {
        ArticleRefresh.finishRefresh();
        articleTitle.setText(responseArticleTitleAndHtml.getData().getTitle());
        imageViewArticleFrontTopImage.setImageURI(responseArticleTitleAndHtml.getData().getFeaturedImageUrl());
        articleWriterHeadImgview.setImageURI(responseArticleTitleAndHtml.getData().getAuthor().getAvatarUrl());
        articleTimeTextView.setText(responseArticleTitleAndHtml.getData().getPublishedAtDiffForHumans());
        articleWriterTextView.setText(responseArticleTitleAndHtml.getData().getAuthor().getNickname());
        articleWriterDetailTextView.setText(responseArticleTitleAndHtml.getData().getAuthor().getTagline());
        articleWebView.loadUrl(responseArticleTitleAndHtml.getData().getLinks().getHtml());
        articleLikeCountTextView.setText("喜欢(" + responseArticleTitleAndHtml.getData().getLikeCount() + ")");
        articleCommentCountTextView.setText("评论(" + responseArticleTitleAndHtml.getData().getCommentCount() + ")");
    }

    @Override
    public void getTitleFailed() {
        Log.d("Article_Activity", "failed");
    }

    @Override
    public void showInterrelated(ResponseArticleInterrelated response) {
        ArticleRefresh.finishRefresh();
        List<ResponseArticleInterrelated.DataBean.ArticlesBean> data = response.getData().getArticles();
        InterrelatedRecyclterViewAdapter adapter = new InterrelatedRecyclterViewAdapter(data,this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        InterrelatedRecyclerView.setAdapter(adapter);
        InterrelatedRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void getInterrelatedFailed() {
        Log.d("Interrelated", "Failed");
    }

    /**
     * article_comment_detail_head_imageView
     * article_comment_detail_comment_content
     * article_comment_detail_nikename_textView
     * article_comment_detail_time_textView
     * article_comment_detail_likeCount_textView
     *
     * @param comment
     */

    @Override
    public void showComment(ResponseArticleComment comment) {
        ArticleRefresh.finishRefresh();
        List<ResponseArticleComment.DataBean.CommentsBean> comments =
                comment.getData().getComments();
        if (!comments.isEmpty()) {
            articleCommentNoCommentImageView.setVisibility(View.GONE);
            articleCommentNoCommentTextView.setVisibility(View.GONE);
            if (comments.size() == 4) {
                articleCommentMoreCommentTextView.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < comments.size(); i++) {
                View view = articleCommentSubLayout.getChildAt(i);
                view.setVisibility(View.VISIBLE);
                TextView nickName = (TextView) view.findViewById(R.id.article_comment_detail_nikename_textView);
                TextView likeCount = (TextView) view.findViewById(R.id.article_comment_detail_likeCount_textView);
                TextView comment_content = (TextView) view.findViewById(R.id.article_comment_detail_comment_content);
                TextView time = (TextView) view.findViewById(R.id.article_comment_detail_time_textView);
                ImageView headImg = (ImageView) view.findViewById(R.id.article_comment_detail_head_imageView);
                nickName.setText(comments.get(i).getUser().getNickname());
                likeCount.setText(comments.get(i).getUpvoteCount() + "");
                comment_content.setText(comments.get(i).getBody());
                time.setText(comments.get(i).getCreatedAtDiffForHumans());
                headImg.setImageURI(Uri.parse(comments.get(i).getUser().getAvatarUrl()));
            }
        }
    }

    @Override
    public void getCommentFailed() {
        Log.d("Comment", "failed");
    }
}
