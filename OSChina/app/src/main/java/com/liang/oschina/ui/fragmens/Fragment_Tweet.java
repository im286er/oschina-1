package com.liang.oschina.ui.fragmens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liang.oschina.R;
import com.liang.oschina.ui.adapters.Tweet_VPAdapter;


/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class Fragment_Tweet extends Fragment{

    private ViewPager mVP ;
    private  android.support.design.widget.TabLayout mTitle ;
    private View mLayout ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.fragment_tweet,container,false);

        return mLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mVP = (ViewPager) mLayout.findViewById(R.id.tweet_viewpager);
        mTitle = (TabLayout) mLayout.findViewById(R.id.tweet_title);
        //创建适配器
        mVP.setAdapter(new Tweet_VPAdapter(getChildFragmentManager()));
        //Viewpager  必须要下有Adapter后才能够关联
        mTitle.setupWithViewPager(mVP);
        mTitle.setTabMode(TabLayout.MODE_FIXED);
    }
}
