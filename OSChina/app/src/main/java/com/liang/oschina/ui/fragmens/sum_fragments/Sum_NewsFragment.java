package com.liang.oschina.ui.fragmens.sum_fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.liang.oschina.R;
import com.liang.oschina.beans.News_List;
import com.liang.oschina.ui.adapters.sum_adapters.News_LVAdapter;
import com.liang.oschina.utils.AnimUtils;
import com.liang.oschina.utils.Constant;
import com.liang.oschina.utils.HttpHelper;
import com.liang.oschina.utils.XmlParseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class Sum_NewsFragment extends Fragment {

    private ListView mListView;
    private View mLayout;

    private News_LVAdapter adapter;
    private List<News_List> news_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.sum_newsfragment, container, false);
        initView();

        if (savedInstanceState != null) {
            Parcelable[] parcelables = savedInstanceState.getParcelableArray("news_list");
            if(parcelables != null && parcelables.length != 0
                    && parcelables instanceof News_List[]){
                News_List[] blog = (News_List[]) parcelables;
                Log.i("TAG", "onCreateView: 进来了 saveBundle中那数据");
                news_list.clear();
                news_list.addAll(Arrays.asList(blog));
                adapter.notifyDataSetChanged();
            } else {
                //网络下载数据
                getDataFromNet();
            }
        } else {
            //网络下载数据
            getDataFromNet();
        }

        return mLayout;
    }

    private void initView() {
        // mPtr = (PtrClassicFrameLayout) mLayout.findViewById(R.id.rotate_header_list_view_frame);
        news_list = new ArrayList<>();
        mListView = (ListView) mLayout.findViewById(R.id.sum_fragment_listView);

        adapter = new News_LVAdapter(news_list);
        mListView.setAdapter(adapter);
        ImageView image = (ImageView) mLayout.findViewById(R.id.sum_fragment_empty);
        AnimUtils.setReversalAnim(image);
        mListView.setEmptyView(image);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (news_list == null || news_list.size() == 0) return;
        News_List[] list = new News_List[news_list.size()];
        news_list.toArray(list) ;
        outState.putParcelableArray("news_list", list);
    }

    /**
     * 从网络下载数据
     */
    private void getDataFromNet() {
        Log.i("TAG", "getDataFromNet: 进来了网络下载数据 ");
        String url = Constant.news_list_path +1;
        HttpHelper helper = HttpHelper.getInstance();
        helper.httpRequest(url, new HttpHelper.CallBack() {
            @Override
            public void getBack(Object is) {
                InputStream in = (InputStream) is;
                Log.i("TAG", "getInputStream: 开始xml数据解析");
                 //解析xml
                List<News_List> list  = XmlParseUtils.getNewsList(in);
                Log.i("TAG", "getInputStream:  得到 list"  + news_list);
                if(list != null){
                    news_list.addAll(list);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //唤醒adapter
                            Log.i("TAG", "run:  进来了 唤醒 adapter" );
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
            @Override
            public void onErr(IOException e) {
                Log.i("TAG", "onErr: 网络解析错误");
            }
        });
    }
}
