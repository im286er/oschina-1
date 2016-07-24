package com.liang.oschina.ui.adapters.sum_adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.liang.oschina.R;
import com.liang.oschina.beans.Answer_Post;
import com.liang.oschina.utils.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class Answer_LVAdapter extends BaseAdapter {
    private List<Answer_Post> posts = null;

    public Answer_LVAdapter(List<Answer_Post> posts) {
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts == null ? 0 : posts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return posts.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.sum_answer_lv_item, parent, false);
            holder = new ViewHolder();
            ButterKnife.bind(holder, view);
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();
        holder.tv_title.setText(posts.get(position).getTitle() + "");
        holder.tv_author.setText(posts.get(position).getAuthor()+"");
        holder.tv_skims.setText(posts.get(position).getViewCount()+"");
        holder.tv_commentCount.setText(posts.get(position).getCommentCount()+"");

        holder.tv_content.setText(posts.get(position).getBody()+"");

        //时间
        holder.tv_pubDate.setText(TimeFormatUtils.getTimesAway(
                posts.get(position).getPubDate())+"");
        //头像
        String url = posts.get(position).getPortrait();

        if(url!=null && !url.equals("")) {
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(url))
                    .setAutoPlayAnimations(true)
                    .build();
            holder.portrait.setController(controller);
        }else{
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse("res://"+parent.getContext().getPackageName() +
                            "/"+R.mipmap.ic_launcher))
                    .setAutoPlayAnimations(true)
                    .build();
            holder.portrait.setController(controller);
        }
        return view;
    }

    public class ViewHolder {
        @BindView(R.id.answer_img_portrait)
        public SimpleDraweeView portrait;//头像
        @BindView(R.id.answer_tv_title)
        public TextView tv_title;
        @BindView(R.id.answer_tv_author)
        public TextView tv_author;
        @BindView(R.id.answer_tv_pubDate)
        public TextView tv_pubDate;
        @BindView(R.id.answer_tv_skim)
        public TextView tv_skims; //浏览数
        @BindView(R.id.answer_tv_commentCount)
        public TextView tv_commentCount;

        @BindView(R.id.answer_tv_content)
        public TextView tv_content;
    }
}
