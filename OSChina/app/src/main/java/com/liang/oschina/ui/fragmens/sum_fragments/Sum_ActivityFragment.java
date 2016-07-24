package com.liang.oschina.ui.fragmens.sum_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liang.oschina.R;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class Sum_ActivityFragment extends Fragment {

    private View mLayout ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.sum_activityfragment,container,false);
        return mLayout;
    }
}
