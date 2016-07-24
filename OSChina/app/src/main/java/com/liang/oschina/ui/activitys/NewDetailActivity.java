package com.liang.oschina.ui.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.liang.oschina.R;
import com.liang.oschina.beans.DetailUtils;
import com.liang.oschina.utils.Constant;
import com.liang.oschina.utils.HttpHelper;
import com.liang.oschina.utils.JsonParseUtils;
import com.liang.oschina.utils.TimeFormatUtils;
import com.liang.oschina.utils.UnitConversionUtils;
import com.readystatesoftware.viewbadger.BadgeView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewDetailActivity extends AppCompatActivity {

    @BindView(R.id.news_detail_content)
    public WebView mWebView;
    @BindViews({R.id.news_detail_action_text, R.id.news_detail_action_jude,
            R.id.news_detail_action_write, R.id.news_detail_action_favor, R.id.news_detail_action_repost})
    public ImageView[] mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_detail);
        initToolBar();
        ButterKnife.bind(this);
        initView();
    }

    private void initToolBar() {
        Toolbar tool = (Toolbar) findViewById(R.id.osc_toolBar);
        tool.setTitle("资讯详情");
        tool.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        tool.setTitleTextColor(Color.WHITE);
        //退出
        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mWebView != null){
                    if(mWebView.canGoBack()){
                        mWebView.goBack();
                        return ;
                    }
                }
                NewDetailActivity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(mWebView != null){
            if(mWebView.canGoBack()){
                mWebView.goBack();
                return ;
            }
        }
        super.onBackPressed();
    }

    private void initView() {
        Intent intent = getIntent();
        long id = intent.getLongExtra("new_id", 0);

        String url = Constant.news_detail_path + id ;

        //加载网页
        HttpHelper helper = HttpHelper.getInstance();
        helper.httpRequest(url, new HttpHelper.StringCallBack() {
            @Override
            public void getBack(Object string) {
               final DetailUtils.NewsDetail newDetail = JsonParseUtils.getNewDetails((String) string);
                if (newDetail != null ) {;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mWebView.getSettings().setJavaScriptEnabled(true);
                            mWebView.setWebViewClient(new WebViewClient());
                            mWebView.getSettings().setDefaultTextEncodingName("utf-8");
                            mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                            // settings.setUseWideViewPort(true);
                           // settings.setLoadWithOverviewMode(true);
                           // 设置加载进来的页面自适应手机屏幕

                            String content = "<h2>"+newDetail.title +"</h2>\n<font color=\"green\">"+ newDetail.author +"</font> \n&nbsp;&nbsp;"+
                                    TimeFormatUtils.getTimesAway(newDetail.pubDate) +"\n"+newDetail.body;

                            mWebView.loadData(content, "text/html; charset=UTF-8", null);

                            //设置评论数
                            BadgeView  badgeView = new BadgeView(NewDetailActivity.this,mBtn[1]);
                            badgeView.setText(newDetail.commentCount + "");
                            badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);//位置

                            badgeView.setBadgeBackgroundColor(Color.parseColor("#037b11"));

                            badgeView.setTextColor(Color.WHITE);
                            badgeView.setTextSize(UnitConversionUtils.dip2px(NewDetailActivity.this, 5));
                            badgeView.toggle();
                        }
                    });
                }
            }

            @Override
            public void onErr(IOException e) {
                Log.i("TAG", "getBack: " +"网络数据解析异常");
            }
        });

    }

    @OnClick({R.id.news_detail_action_text, R.id.news_detail_action_jude,
            R.id.news_detail_action_write, R.id.news_detail_action_favor, R.id.news_detail_action_repost})
    public void onActionClick(View view) {
        switch (view.getId()) {
            case R.id.news_detail_action_text:
                Log.i("TAG", "onActionClick: 单击了text" + view.getId());
                break;
            case R.id.news_detail_action_jude:
                Log.i("TAG", "onActionClick: 单击了jude" + view.getId());
                break;
            case R.id.news_detail_action_write:
                Log.i("TAG", "onActionClick: 单击了write" + view.getId());
                break;
            case R.id.news_detail_action_favor:
                Log.i("TAG", "onActionClick: 单击了favor" + view.getId());
                break;
            case R.id.news_detail_action_repost:
                Log.i("TAG", "onActionClick: 单击了repost" + view.getId());

        }
    }

}
