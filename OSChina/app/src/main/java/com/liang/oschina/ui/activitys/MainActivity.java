package com.liang.oschina.ui.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.liang.oschina.R;
import com.liang.oschina.ui.fragmens.Fragment_Found;
import com.liang.oschina.ui.fragmens.Fragment_My;
import com.liang.oschina.ui.fragmens.Fragment_Summary;
import com.liang.oschina.ui.fragmens.Fragment_Tweet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup mRadio ;
    private ImageButton mQuickOption ;
    private QuickOptionView mOption ;
    private List<Fragment> mFragments  ;
    private Fragment mFragment ;
    private Fragment mCurrent ;
    private FragmentTransaction ft ;
    private FragmentManager fm ;
    private Intent intent ;
    private View mWrapperLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());//初始化 Fresco

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mWrapperLayout =
                LayoutInflater.from(this).inflate(R.layout.activity_main,null);

        initToolbar() ;
        initView();

    }

    private void initToolbar() {
        Toolbar tool = (Toolbar) findViewById(R.id.osc_toolBar);
        tool.setTitle("开源中国");
        tool.inflateMenu(R.menu.menu_osc);
        tool.setTitleTextColor(Color.WHITE);
        tool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });
        mQuickOption = (ImageButton) findViewById(R.id.osc_menu_add);
        mQuickOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOption == null){
                    mOption = new QuickOptionView(MainActivity.this);
                    mOption.showView();
                    //QuickOption 单击监听回调
                }else{
                  mOption.showView();
                }
                mWrapperLayout.setAlpha(0.6f);
                mOption.mQuickView.getCurrentFocus();
                mOption.setClickCallBack(new QuickOptionView.ClickCallBack() {
                    @Override
                    public void onClickBack(int id) {
                        quickOptionClick(id);
                    }
                });
            }
        });

    }

   private void  quickOptionClick(int id){
        switch (id){
            case R.id.quick_text:

                break;
            case R.id.quick_album:

                break;
            case R.id.quick_photo:

                break;
            case R.id.quick_vioce:
                intent = new Intent(this,SoundActivity.class);
                startActivity(intent);
                break;
            case R.id.quick_scan:
                intent = new Intent(this,ScannerActivity.class);
                startActivity(intent);
                break;
            case R.id.quick_note:

        }
    }

    @Override
    public void onBackPressed() {
        if(mOption !=null){
            if(mOption.mQuickView.isShowing()){
                mOption.dismissView();
                mWrapperLayout.setAlpha(1f);
                return ;
            }
        }
        super.onBackPressed();
    }

    private void initView() {
        mRadio = (RadioGroup) findViewById(R.id.osc_menu_radioGroup);
        initRadioButton();
        mFragments = new ArrayList<>();
        for(int i = 0 ;i< 4 ;i++){
            mFragments.add(null);
        }

        mFragment = new Fragment_Summary();
        mCurrent = mFragment ;
        mFragments.set(0, mFragment);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.osc_frame_content, mFragment, mFragment.getClass().getName());
        ft.commit();
    }

    public void initRadioButton(){
        RadioButton button ;
        for(int i = 0 ; i< mRadio.getChildCount() ;i++){
            if(i == 2) continue;
            button = (RadioButton) mRadio.getChildAt(i);
            button.setOnClickListener(this);
            if(button.isChecked()){
                button.setTextColor(Color.GREEN);
            }else {
                button.setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.osc_menu_summary:
                initFragment(0);
                break ;
            case R.id.osc_menu_dongtan:
                initFragment(1);
                break ;
            case R.id.osc_menu_add:

                break ;
            case R.id.osc_menu_found:
                initFragment(2);
                break ;
            case R.id.osc_menu_my:
                initFragment(3);
                break ;
        }
        initRadioButton();//重新改变texColor
    }

    private void initFragment(int i) {
        switch (i){
            case 0:
                fragmentsShowOrHide(0);
                break ;
            case 1:
                fragmentsShowOrHide(1);
                break ;
            case 2:
                fragmentsShowOrHide(2);
                break ;
            case 3:
                fragmentsShowOrHide(3);
        }
    }

    private void fragmentsShowOrHide(int i){
        ft = fm.beginTransaction() ;
        if(mFragments.get(i) != null
                && !mFragments.get(i).isHidden()){
            Log.i("TAG", "fragmentsShowOrHide: 进来了直接返回 " + "");
            return ;
        }
        if(mFragments.get(i) == null){
            switch(i){
                case 1:
                    mFragment = new Fragment_Tweet();
                    ft.add(R.id.osc_frame_content, mFragment,
                            mFragment.getClass().getName());
                    break;
                case 2:
                    mFragment = new Fragment_Found();
                    ft.add(R.id.osc_frame_content, mFragment,
                            mFragment.getClass().getName());
                    break ;
                case 3:
                    mFragment = new Fragment_My();
                    ft.add(R.id.osc_frame_content, mFragment,
                            mFragment.getClass().getName());
                    break ;

            }
            Log.i("TAG", "fragmentsShowOrHide: 进来了null new 对象 " + "");
            ft.hide(mCurrent);
            ft.show(mFragment);
            mFragments.set(i,mFragment);
            mCurrent = mFragment ;
        }else{
            Log.i("TAG", "fragmentsShowOrHide: 直接调用隐藏的 " + "");
            ft.hide(mCurrent);
            ft.show(mFragments.get(i));
            mCurrent = mFragments.get(i);
        }
        ft.commit() ;
    }
}
