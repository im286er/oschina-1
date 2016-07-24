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
import com.liang.oschina.ui.adapters.Sum_VP_Adapter;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class Fragment_Summary extends Fragment{
    private ViewPager mVP ;
    private  android.support.design.widget.TabLayout mTitle ;
    private View mLayout ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.fragment_summary,container,false);

        return mLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mVP = (ViewPager) mLayout.findViewById(R.id.sum_viewpager);
        mTitle = (TabLayout) mLayout.findViewById(R.id.sum_title);
        //创建适配器
        mVP.setAdapter(new Sum_VP_Adapter(getChildFragmentManager()));
        //Viewpager  必须要下有Adapter后才能够关联
        mTitle.setupWithViewPager(mVP);
        mTitle.setTabMode(TabLayout.MODE_FIXED);

    }
}
