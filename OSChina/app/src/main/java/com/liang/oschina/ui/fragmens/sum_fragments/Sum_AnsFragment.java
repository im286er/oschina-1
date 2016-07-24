package com.liang.oschina.ui.fragmens.sum_fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;

import com.liang.oschina.R;
import com.liang.oschina.beans.Answer_Post;
import com.liang.oschina.customsViews.Sum_Answer_ListView;
import com.liang.oschina.ui.activitys.PostDetailActivity;
import com.liang.oschina.ui.adapters.sum_adapters.Answer_LVAdapter;
import com.liang.oschina.utils.Constant;
import com.liang.oschina.utils.HttpHelper;
import com.liang.oschina.utils.XmlParseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class Sum_AnsFragment extends Fragment {
    @BindViews({R.id.answer_radio_ask, R.id.answer_radio_share, R.id.answer_radio_total, R.id.answer_radio_occupation, R.id.answer_radio_office})
    public RadioButton[] btn;

    public Sum_Answer_ListView mListView;
    private View mLayout;
    private Answer_LVAdapter adapter;
    private List<Answer_Post> posts;

    private int checkedRadioId = R.id.answer_radio_ask; //默认是提问选中
    private Intent intent ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        mLayout = inflater.inflate(R.layout.sum_answerfragment, container, false);
        mListView = (Sum_Answer_ListView) mLayout.findViewById(R.id.answer_listView);
        ButterKnife.bind(this, mLayout);
        initView();
        if (bundle != null) {
            Object[] ob = bundle.getParcelableArray("posts");
            if (ob != null && ob.length != 0 && ob instanceof Answer_Post[]) {
                Answer_Post[] ap = (Answer_Post[]) ob;
                posts.clear();
                posts.addAll(Arrays.asList(ap));
                int id = bundle.getInt("radio_id");
                if (id != 0) {
                    checkedRadioId = id;
                    adapter.notifyDataSetChanged();//唤醒数据
                }
            }else{
               //网络下载数据 (默认下载提问的数据)
                getDateFromNet(checkedRadioId);
            }
        }else{
            //网络下载数据 （(默认下载提问的数据)）
            getDateFromNet(checkedRadioId);
        }
        enableForTrue(checkedRadioId);
        return mLayout;
    }

    /**
     * 从网络下载数据
     */
    private void getDateFromNet(int list_id) {
        String url ="";
        switch (list_id){
            case R.id.answer_radio_ask:
                url = Constant.answer_path_ask + 1;
                break;
            case R.id.answer_radio_share:
                url = Constant.answer_path_share + 1;
                break;
            case R.id.answer_radio_total:
                url = Constant.answer_path_total + 1;
                break;
            case R.id.answer_radio_occupation:
                url = Constant.answer_path_opp + 1;
                break;
            case R.id.answer_radio_office:
                url = Constant.answer_path_office + 1;
        }

        HttpHelper helper = HttpHelper.getInstance();
        helper.httpRequest(url, new HttpHelper.CallBack() {
            @Override
            public void getBack(Object inputStream) {
                List<Answer_Post> list = XmlParseUtils.getAnswersList((InputStream) inputStream);
                if(list!= null && list.size() != 0){
                    posts.clear();
                    posts.addAll(list);
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

    private void initView() {
        posts = new ArrayList<>();
        adapter = new Answer_LVAdapter(posts);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent  = new Intent(getActivity(), PostDetailActivity.class);
                intent.putExtra("post_id",id);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (posts == null || posts.size() == 0) return;
        Answer_Post[] ap = new Answer_Post[posts.size()];
        posts.toArray(ap);
        outState.putParcelableArray("posts", ap);
        outState.putInt("radio_id", checkedRadioId);
    }


    @OnClick({R.id.answer_radio_ask, R.id.answer_radio_share, R.id.answer_radio_total, R.id.answer_radio_occupation, R.id.answer_radio_office})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.answer_radio_ask:
                checkedRadioId = R.id.answer_radio_ask;

                //网络下载数据
                getDateFromNet(checkedRadioId);

                break;
            case R.id.answer_radio_share:
                checkedRadioId = R.id.answer_radio_share;

                //网络下载数据
                getDateFromNet(checkedRadioId);
                break;
            case R.id.answer_radio_total:
                checkedRadioId = R.id.answer_radio_total;

                //网络下载数据
                getDateFromNet(checkedRadioId);
                break;
            case R.id.answer_radio_occupation:
                checkedRadioId = R.id.answer_radio_occupation;

                //网络下载数据
                getDateFromNet(checkedRadioId);
                break;
            case R.id.answer_radio_office:
                checkedRadioId = R.id.answer_radio_office;

                //网络下载数据
                getDateFromNet(checkedRadioId);
        }
        enableForTrue(checkedRadioId);
    }

    //设置选中的按钮不可再次单击 ，且改变他的color颜色
    private void enableForTrue(int id) {
        for (int i = 0; i < btn.length; i++) {
            if (id == btn[i].getId()) {
                btn[i].setEnabled(false);
                btn[i].setTextColor(Color.parseColor("#06970d"));
            } else {
                btn[i].setEnabled(true);
                btn[i].setTextColor(Color.LTGRAY);
                btn[i].setTextColor(Color.parseColor("#646363"));
            }
        }
    }
}
