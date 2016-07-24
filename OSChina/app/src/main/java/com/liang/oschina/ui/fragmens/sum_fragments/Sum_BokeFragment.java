package com.liang.oschina.ui.fragmens.sum_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liang.oschina.R;
import com.liang.oschina.beans.Blog_List;
import com.liang.oschina.ui.activitys.BlogDetailActivity;
import com.liang.oschina.ui.adapters.sum_adapters.Blog_LVAdapter;
import com.liang.oschina.utils.Constant;
import com.liang.oschina.utils.HttpHelper;
import com.liang.oschina.utils.XmlParseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class Sum_BokeFragment extends Fragment {

    @BindView(R.id.sum_blog_listview)
    public ListView mListView;
    private View mLayout;

    private Blog_LVAdapter adapter;
    private List<Blog_List> blog_lists;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.sum_bokefragment,container,false);
        initView() ;

        if(savedInstanceState != null){
            Parcelable [] parcelables = savedInstanceState.getParcelableArray("blog");
            if(parcelables != null && parcelables.length != 0
                    && parcelables instanceof Blog_List[]){
                Blog_List[] blog = (Blog_List[]) parcelables;
                blog_lists.clear();
                blog_lists.addAll(Arrays.asList(blog));
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

    /**
     * 从网络下载数据
     */
    private void getDataFromNet() {
        String url = Constant.blog_list_path +1;
        HttpHelper helper = HttpHelper.getInstance();
        helper.httpRequest(url, new HttpHelper.CallBack() {
            @Override
            public void getBack(Object is) {
               InputStream in = (InputStream) is;
                //解析xml
                List<Blog_List> list  = XmlParseUtils.getBlogList(in);
                Log.i("TAG", "getInputStream:  得到 list"  + blog_lists);
                if(list != null){
                    blog_lists.addAll(list);
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

    private void initView() {
        ButterKnife.bind(this,mLayout);
        blog_lists = new ArrayList<>();
        adapter = new Blog_LVAdapter(blog_lists);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
                intent.putExtra("blog_id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
       if(blog_lists == null || blog_lists.size() == 0) return ;

        Blog_List[] blog = new Blog_List[blog_lists.size()];
        blog_lists.toArray(blog);
        outState.putParcelableArray("blog",blog);
    }


}
