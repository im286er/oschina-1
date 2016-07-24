package com.liang.oschina.ui.fragmens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liang.oschina.R;
import com.liang.oschina.ui.activitys.ScannerActivity;
import com.liang.oschina.ui.activitys.ShakeActivity;
import com.liang.oschina.ui.activitys.SoftOpenActivity;
import com.liang.oschina.ui.adapters.Found_LV_Adapter;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class Fragment_Found extends Fragment{
    private View mLayout ;
    private ListView mListView ;
    private Intent mIntent ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.fragment_found,container,false);
        initView();
        return mLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView() {

        mListView = (ListView) mLayout.findViewById(R.id.found_listView);
        mListView.setAdapter(new Found_LV_Adapter());

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               switch (position){
                   case 0:
                       mIntent = new Intent(getActivity(), SoftOpenActivity.class);
                       startActivity(mIntent);
                       break;
                   case 1:

                       break;
                   case 2:
                       mIntent = new Intent(getActivity(), ScannerActivity.class);
                       startActivity(mIntent);
                       break;
                   case 3:
                       mIntent = new Intent(getActivity(), ShakeActivity.class);
                       startActivity(mIntent);
                       break;
                    default:
               }
            }
        });
    }

}
