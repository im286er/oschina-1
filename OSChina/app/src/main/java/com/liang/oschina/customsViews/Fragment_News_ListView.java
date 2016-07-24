package com.liang.oschina.customsViews;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liang.oschina.ui.activitys.NewDetailActivity;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class Fragment_News_ListView extends ListView{
  private Context context ;
    public Fragment_News_ListView(Context context) {
        this(context, null, 0);
    }

    public Fragment_News_ListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Fragment_News_ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
       this.setOnItemClickListener(new OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Intent intent = new Intent(context, NewDetailActivity.class);
               intent.putExtra("new_id",id);
               context.startActivity(intent);
           }
       });

    }
}
