package com.liang.oschina.ui.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;

import com.liang.oschina.R;
import com.liang.oschina.beans.DetailUtils;
import com.liang.oschina.utils.Constant;
import com.liang.oschina.utils.HttpHelper;
import com.liang.oschina.utils.JsonParseUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class BlogDetailActivity extends AppCompatActivity {
    @BindView(R.id.blog_detail_webview)
    public WebView mWebView;
    @BindView(R.id.blog_detail_judge)
    public EditText mEditText;
    @BindViews({R.id.blog_detail_favor, R.id.blog_detail_share})
    public ImageButton[] mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_blog_detail);
        initToolBar();
        ButterKnife.bind(this);
        initView();
    }

    private void initToolBar() {
        Toolbar tool = (Toolbar) findViewById(R.id.osc_toolBar);
        tool.setTitle("博客详情");
        tool.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        tool.setTitleTextColor(Color.WHITE);
        //退出
        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView != null) {
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                        return;
                    }
                }
                BlogDetailActivity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mWebView != null) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return;
            }
        }
        super.onBackPressed();
    }

    private void initView() {
        Intent intent = getIntent();
        long id = intent.getLongExtra("blog_id", 0);

        //下载数据
        String url = Constant.blog_detail_path + id;
        HttpHelper helper = HttpHelper.getInstance();
        helper.httpRequest(url, new HttpHelper.StringCallBack() {
            @Override
            public void getBack(Object string) {
                final DetailUtils.BlogDetail blog = JsonParseUtils.getBlogDetails((String) string);

                if (blog != null) {
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           mWebView.getSettings().setJavaScriptEnabled(true);
                           mWebView.setWebViewClient(new WebViewClient());
                           mWebView.getSettings().setDefaultTextEncodingName("utf-8");
                           mWebView.getSettings().setLayoutAlgorithm(WebSettings.
                                   LayoutAlgorithm.SINGLE_COLUMN);
                        /*   mWebView.getSettings().setUseWideViewPort(true);
                           mWebView.getSettings().setLoadWithOverviewMode(true);*/
                           mWebView.loadData(blog.body,"text/html; charset=UTF-8",null);
                       }
                   });
                }
            }

            @Override
            public void onErr(IOException e) {

            }
        });
    }
}
