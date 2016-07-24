package com.liang.oschina.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.liang.oschina.ui.fragmens.sum_fragments.Sum_ActivityFragment;
import com.liang.oschina.ui.fragmens.sum_fragments.Sum_AnsFragment;
import com.liang.oschina.ui.fragmens.sum_fragments.Sum_BokeFragment;
import com.liang.oschina.ui.fragmens.sum_fragments.Sum_NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class Sum_VP_Adapter extends FragmentStatePagerAdapter{
    /*private int[]  title = new int[]{R.string.news_zixun,R.string.news_blog,
            R.string.news_answer,R.string.news_activity};*/
    private String[] title = new String[]{"资讯","博客","问答","活动"};
    private List<Fragment> mSubFragments = new ArrayList<>();
    private Fragment mSubFragment;
    public Sum_VP_Adapter(FragmentManager fm) {
        super(fm);
        for(int i= 0 ;i <4 ;i++){
            mSubFragments.add(null);
        }
    }
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                if(mSubFragments.get(position) == null){
                    mSubFragment = new Sum_NewsFragment();
                    mSubFragments.set(0, mSubFragment);
                }
                break ;
            case 1:
                if(mSubFragments.get(position) == null){
                    mSubFragment = new Sum_BokeFragment();
                    mSubFragments.set(1,mSubFragment);
                }
                break ;

            case 2:
                if(mSubFragments.get(position) == null){
                    mSubFragment = new Sum_AnsFragment();

                    mSubFragments.set(2,mSubFragment);
                }
                break ;
            case 3:
                if(mSubFragments.get(position) == null){
                    mSubFragment = new Sum_ActivityFragment();
                    mSubFragments.set(3,mSubFragment);
                }
                break ;
        }
        Log.i("TAG", "getItem: "+ mSubFragments.size());
        return mSubFragments.get(position);
    }

    @Override
    public int getCount() {
        return mSubFragments == null ? 0:title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position] ;
    }
}
