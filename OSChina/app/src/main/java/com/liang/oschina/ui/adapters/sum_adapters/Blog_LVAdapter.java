package com.liang.oschina.ui.adapters.sum_adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liang.oschina.R;
import com.liang.oschina.beans.Blog_List;
import com.liang.oschina.utils.TimeFormatUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class Blog_LVAdapter extends BaseAdapter {
    private List<Blog_List> lists = null;

    public Blog_LVAdapter(List<Blog_List> lists) {
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public Blog_List getItem(int position) {
        return lists == null ? null : lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lists.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null ;
        if(view == null){
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.sum_blog_lv_item,parent,false);
            holder = new ViewHolder();

            holder.iv_originate = (ImageView) view.findViewById(R.id.blog_img_originate);
            holder.iv_recommend = (ImageView) view.findViewById(R.id.blog_img_recoment);
            holder.iv_repint = (ImageView) view.findViewById(R.id.blog_img_repint);

            holder.title = (TextView) view.findViewById(R.id.blog_tv_title);
            holder.content = (TextView) view.findViewById(R.id.blog_tv_content);
            holder.author = (TextView) view.findViewById(R.id.blog_tv_author);
            holder.mCount = (TextView) view.findViewById(R.id.blog_tv_commentCount);
            holder.mSkim = (TextView) view.findViewById(R.id.blog_tv_skim);
            holder.pubDate = (TextView) view.findViewById(R.id.blog_tv_pubDate);

            view.setTag(holder);
        }

        holder = (ViewHolder) view.getTag();
        //设置文本属性
        holder.title.setText(lists.get(position).getTitle()+"");
        holder.content.setText(lists.get(position).getBody()+"");
        holder.author.setText(lists.get(position).getAuthor()+"");
        holder.mCount.setText(lists.get(position).getCommentCount()+"");

        //设置好显示图片
        int type = lists.get(position).getType() ;
        switch (type){
            case 1://原创
                holder.iv_originate.setVisibility(View.VISIBLE);
                holder.iv_repint.setVisibility(View.INVISIBLE);
                break;
            case 4: //转载
                holder.iv_originate.setVisibility(View.INVISIBLE);
                holder.iv_repint.setVisibility(View.VISIBLE);
                break;
            default:
                holder.iv_originate.setVisibility(View.INVISIBLE);
                holder.iv_repint.setVisibility(View.INVISIBLE);
        }

        //设置对应时间
        holder.pubDate.setText(TimeFormatUtils.getTimesAway
                (lists.get(position).getPubDate())+"");

        return view;
    }

    public class ViewHolder {
        private ImageView iv_recommend;//推荐
        private ImageView iv_originate;// 原著
        private ImageView iv_repint;//装载

        private TextView title ;
        private TextView content ;
        private TextView author ;
        private TextView mCount ;
        private TextView mSkim ;//浏览数
        private TextView pubDate ;

    }
}
