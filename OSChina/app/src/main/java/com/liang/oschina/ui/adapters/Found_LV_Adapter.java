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
 * Created by Administrator on 2016/7/1 0001.
 */
public class Found_LV_Adapter extends BaseAdapter{
    private int[] datas;
    private int[] images;

    public Found_LV_Adapter() {
        datas = new int[]{R.string.found_explore,R.string.found_user,
                R.string.found_scan,R.string.found_shake};
        images = new int[]{R.drawable.icon_explore_friends,
                R.drawable.icon_explore_finduser,R.drawable.icon_explore_scan,
                R.drawable.icon_explore_shake};
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
                    inflate(R.layout.found_lv_item, parent, false);
            holder = new ViewHolder();
            view.setTag(holder);
            holder.tv = (TextView) view.findViewById(R.id.found_lv_item_tv);
            holder.iv = (ImageView) view.findViewById(R.id.found_lv_item_img);
            holder.line = view.findViewById(R.id.found_lv_item_line);
        }

        holder = (ViewHolder) view.getTag();
        holder.iv.setImageResource(images[position]);
        holder.tv.setText(datas[position]);
        if (position == 1) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();

            params.height = UnitConversionUtils.dip2px(parent.getContext(),20);
            holder.line.setLayoutParams(params);
            holder.line.setBackgroundResource(R.color.listview_line);
            holder.line.setEnabled(true);
            holder.line.setOnClickListener(null);
        }
        return view;
    }

    class ViewHolder {
        private ImageView iv;
        private TextView tv;
        private View line;
    }
}
