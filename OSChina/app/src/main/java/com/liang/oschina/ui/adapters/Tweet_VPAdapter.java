package com.liang.oschina.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.liang.oschina.ui.fragmens.tweet_fragments.HotTweet_Fragment;
import com.liang.oschina.ui.fragmens.tweet_fragments.MyTweet_Fragment;
import com.liang.oschina.ui.fragmens.tweet_fragments.NewTweet_Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class Tweet_VPAdapter extends FragmentStatePagerAdapter {

    private String[] title = new String[]{"最新动弹","最热动弹","我的动弹"};
    private List<Fragment> mSubFragments = new ArrayList<>();
    private Fragment mSubFragment;
    public Tweet_VPAdapter(FragmentManager fm) {
        super(fm);
        for(int i= 0 ;i <3 ;i++){
            mSubFragments.add(null);
        }
    }
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                if(mSubFragments.get(position) == null){
                    mSubFragment = new NewTweet_Fragment();
                    mSubFragments.set(0, mSubFragment);
                }
                break ;
            case 1:
                if(mSubFragments.get(position) == null){
                    mSubFragment = new HotTweet_Fragment();
                    mSubFragments.set(1,mSubFragment);
                }
                break ;

            case 2:
                if(mSubFragments.get(position) == null){
                    mSubFragment = new MyTweet_Fragment();

                    mSubFragments.set(2,mSubFragment);
                }
        }
        Log.i("TAG", "getItem: " + mSubFragments.size());
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
