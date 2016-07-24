package com.liang.oschina.ui.fragmens;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.liang.oschina.R;
import com.liang.oschina.ui.adapters.My_LV_Adapter;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class Fragment_My extends Fragment {
    private View mLayout ;
    private ListView mListView ;
    private SimpleDraweeView mSimpleDraw;

    private boolean IsLogin = false ;//是否已经登录，默认未登录
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.fragment_my,container,false);

        return mLayout;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         initView();

    }

    private void initView() {

        mListView = (ListView) mLayout.findViewById(R.id.osc_my_lv);
        //设置适配器
        mListView.setAdapter(new My_LV_Adapter());

        mSimpleDraw = (SimpleDraweeView) mLayout.findViewById(R.id.my_loagin_img);
        DraweeController controller =  Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("res://" + getActivity().getPackageName())+"/"+
                        R.drawable.widget_dface)
                .build();

        mSimpleDraw.setController(controller);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0://登录
                        if(!IsLogin){
                            Toast.makeText(getContext(),"该功能开发中...",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1: // 我的消息
                        break;
                    case 2:  // 我的blog
                        break;
                    case 3:  // 我的团队
                        break;
                    case 4:  // 我的活动
                        break;
                    case 5:  // 我的便签
                        break;
                    case 6:  //  反馈
                        break;
                    case 7:  //  设置
                        break;
                    default:

                }
            }
        });
    }
}
