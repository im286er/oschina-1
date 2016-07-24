package com.liang.oschina.ui.adapters.sum_adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liang.oschina.R;
import com.liang.oschina.beans.News_List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/1 0001.
 */
public class News_LVAdapter extends BaseAdapter {
    private List<News_List> lists = null ;
    private SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
   // private list<DetailUtils>

    public News_LVAdapter(List<News_List> lists){
        this.lists = lists ;
    }
    @Override
    public int getCount() {
        return lists ==null ? 0: lists.size();
    }

    @Override
    public News_List getItem(int position) {
        return lists== null ? null :lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lists.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null ;
        if(view == null ){
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.sum_news_lv_item,parent,false);

            holder = new ViewHolder();
            holder.mTitle = (TextView) view.findViewById(R.id.news_title);
            holder.mBody = (TextView) view.findViewById(R.id.news_body);
            holder.mPubDate = (TextView) view.findViewById(R.id.news_pub);
            holder.mCount = (TextView) view.findViewById(R.id.news_commentCount);
            holder.mDate_Img = (ImageView) view.findViewById(R.id.news_subDate_img);
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();

        holder.mTitle.setText(lists.get(position).getTitle()+"");
        holder.mBody.setText(lists.get(position).getBody()+"");
        holder.mCount.setText(lists.get(position).getCommentCount()+"");

        long pubDate = 0;
        try {
            Date date = format.parse((lists.get(position).getPubDate()));
            pubDate = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long mCurrentDate = System.currentTimeMillis() ;
        long date  = (mCurrentDate - pubDate)/(1000*60*60*24);
        long hours =(mCurrentDate - pubDate)/(1000*60*60) ;
        long min =(mCurrentDate - pubDate)/(1000*60) ;

        if(date != 0){
            holder.mPubDate.setText(date+"天前");
            holder.mDate_Img.setVisibility(View.GONE);//今天图片设置不可见
        }else if(hours != 0){
            holder.mPubDate.setText(hours+"小时前");
            holder.mDate_Img.setVisibility(View.VISIBLE);
        }else if(min != 0){
            holder.mPubDate.setText(min+"分钟前");
            holder.mDate_Img.setVisibility(View.VISIBLE);
        }else{
            holder.mPubDate.setText((mCurrentDate - pubDate)/1000+"秒前");
            holder.mDate_Img.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public class ViewHolder{
        private TextView mTitle ;
        private TextView mBody ;
        private TextView mCount ;
        private TextView mPubDate ;
        private ImageView mDate_Img ;
    }
}
