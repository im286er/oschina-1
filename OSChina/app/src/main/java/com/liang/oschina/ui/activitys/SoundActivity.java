package com.liang.oschina.ui.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.liang.oschina.R;

public class SoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sound);
        initToolBar();
    }
    private void initToolBar() {
        Toolbar tool = (Toolbar) findViewById(R.id.osc_toolBar);
        tool.setTitle("弹一弹");
        tool.inflateMenu(R.menu.sound_send_tweet);
        tool.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        tool.setTitleTextColor(Color.WHITE);
        //退出
        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SoundActivity.this.finish();
            }
        });
    }
}
