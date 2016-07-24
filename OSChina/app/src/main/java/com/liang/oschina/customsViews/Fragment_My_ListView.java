package com.liang.oschina.customsViews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liang.oschina.R;

/**
 * Created by Administrator on 2016/7/1 0001.
 */
public class Fragment_My_ListView extends ListView {
    private View mHeader ;
    private Context context ;
    public Fragment_My_ListView(Context context) {
        this(context, null, 0);
    }

    public Fragment_My_ListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Fragment_My_ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context ;
        initView();
    }

    private void initView() {
        mHeader = LayoutInflater.from(context).inflate(
                R.layout.my_lv_item_header,null);
        this.addHeaderView(mHeader);
        this.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TAG", "onItemClick: 单击了item");
            }
        });
    }
}
