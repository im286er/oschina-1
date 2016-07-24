package com.liang.oschina.customsViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.liang.oschina.R;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class Sum_Answer_ListView extends ListView {

    private View mHead ;
    private Context context ;

    public Sum_Answer_ListView(Context context) {
        this(context, null, 0);
    }

    public Sum_Answer_ListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Sum_Answer_ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context ;
        initView();
    }

    private void initView() {
        mHead = LayoutInflater.from(context).inflate(R.layout.sum_answer_lv_header,null);
        this.addHeaderView(mHead);
    }

}
