package com.liang.oschina.ui.activitys;

import android.graphics.Color;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.liang.oschina.R;

import java.io.IOException;
import java.util.HashMap;

public class ShakeActivity extends AppCompatActivity {
    //震动管理器
    private SensorManager mSensor ;

    private Vibrator mVibrator ;//震动类

    //声音池
    private SoundPool sndPool;
    private HashMap<Integer, Integer> soundPoolMap =
            new HashMap<Integer, Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shake);
        initToolBar();
        initView();

    }
    private void initToolBar() {
        Toolbar tool = (Toolbar) findViewById(R.id.osc_toolBar);
        tool.setTitle("摇一摇");
        tool.inflateMenu(R.menu.sound_send_tweet);
        tool.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        tool.setTitleTextColor(Color.WHITE);
        //退出
        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShakeActivity.this.finish();
            }
        });
    }

    private void initView() {
        mSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }


    /**
     * 记载声音
     */
    private void loadSound() {
        sndPool =  new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
       // sndPool = new SoundPool.Builder().build();
        new Thread() {
            public void run() {
                try {
                    soundPoolMap.put(
                            0,
                            sndPool.load(getAssets().openFd(
                                    "sound/shake_sound_male.mp3"), 1));

                    soundPoolMap.put(
                            1,
                            sndPool.load(getAssets().openFd(
                                    "sound/shake_match.mp3"), 1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
