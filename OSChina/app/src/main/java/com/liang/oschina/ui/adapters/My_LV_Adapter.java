package com.liang.oschina.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liang.oschina.R;
import com.liang.oschina.utils.UnitConversionUtils;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class My_LV_Adapter extends BaseAdapter {
    private int[] datas;
    private int[] images;
    public My_LV_Adapter() {
        datas = new int[]{R.string.my_news,R.string.my_blog,R.string.my_team,
                R.string.my_activity,R.string.my_label,R.string.my_feebback,R.string.my_setting};

        images = new int[]{R.drawable.icon_my_message,R.drawable.icon_my_blog,
                R.drawable.icon_my_team,R.drawable.ic_my_event,R.drawable.icon_my_note,
                R.drawable.ic_feedback,R.drawable.ic_my_setup};
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Object getItem(int position) {
        return datas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.my_lv_item, parent, false);
            holder = new ViewHolder();
            view.setTag(holder);
            holder.tv = (TextView) view.findViewById(R.id.my_lv_item_tv);
            holder.iv = (ImageView) view.findViewById(R.id.my_lv_item_img);
            holder.line =  view.findViewById(R.id.my_lv_item_line);
        }

        holder = (ViewHolder) view.getTag();
        holder.iv.setImageResource(images[position]);
        holder.tv.setText(datas[position]);
        if (position == 4) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();
            params.leftMargin = 0;
            params.height = UnitConversionUtils.dip2px(parent.getContext(), 20);
            holder.line.setLayoutParams(params);
            holder.line.setBackgroundResource(R.color.listview_line);
            holder.line.setEnabled(true);
            holder.line.setOnClickListener(null);
        }

        if (position == 6) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                    holder.line.getLayoutParams();
            holder.line.setVisibility(View.GONE);
            params.bottomMargin = 20;
            holder.line.setLayoutParams(params);

        }

        return view;
    }

    class ViewHolder {
        private ImageView iv;
        private TextView tv;

        private View line;
    }
}
