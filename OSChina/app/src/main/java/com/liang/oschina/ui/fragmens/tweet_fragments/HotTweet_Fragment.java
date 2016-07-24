package com.liang.oschina.ui.fragmens.tweet_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.liang.oschina.R;
import com.liang.oschina.beans.Tweet_List;
import com.liang.oschina.ui.adapters.tweet_adapters.HotTweet_LVAdapter;
import com.liang.oschina.utils.AnimUtils;
import com.liang.oschina.utils.Constant;
import com.liang.oschina.utils.HttpHelper;
import com.liang.oschina.utils.JsonParseUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class HotTweet_Fragment extends Fragment{
    private View mLayout;
    private ListView mListView ;
    private HotTweet_LVAdapter adapter ;
    private ImageView mEmptyView ;
    private List<Tweet_List> list = null ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        mLayout = inflater.inflate(R.layout.tweet_hotfragment,container,false);
        mListView = (ListView) mLayout.findViewById(R.id.tweet_hot_listView);
        mEmptyView = (ImageView) mLayout.findViewById(R.id.tweet_hot_emptyImg);
        initView();

        if(bundle != null){
            Tweet_List[] tweets = (Tweet_List[]) bundle.getParcelableArray("tweets");
            if(tweets!=null && tweets.length!=0){
                list.clear();
                list.addAll(Arrays.asList(tweets));
                adapter.notifyDataSetChanged();
            }else {
                //网络下载数据
                getDataFromNet();
            }
        }else {
            //网络下载数据
            getDataFromNet();
        }

        return mLayout;
    }

    private void initView() {
        list = new ArrayList<>();
        adapter = new HotTweet_LVAdapter(list);
        AnimUtils.setReversalAnim(mEmptyView);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(mEmptyView);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(list==null || list.size()==0) return ;

        Tweet_List[]  tweets = new Tweet_List[list.size()];
        list.toArray(tweets);
        outState.putParcelableArray("tweets", tweets);
    }

    /**
     * 从网络下载数据
     */
    private void getDataFromNet() {

        String url = Constant.tweet_path_hot+1;
        HttpHelper helper = HttpHelper.getInstance();
        helper.httpRequest(url, new HttpHelper.StringCallBack() {
            @Override
            public void getBack(Object string) {
                String str = (String) string;
                List<Tweet_List> tweet = JsonParseUtils.getTweetList(str);
                if (tweet != null) {
                    list.addAll(tweet);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
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
