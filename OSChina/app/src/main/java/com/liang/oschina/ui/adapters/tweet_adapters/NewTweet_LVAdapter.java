package com.liang.oschina.ui.adapters.tweet_adapters;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.liang.oschina.R;
import com.liang.oschina.beans.Tweet_List;
import com.liang.oschina.utils.HttpHelper;
import com.liang.oschina.utils.TimeFormatUtils;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class NewTweet_LVAdapter extends BaseAdapter{
    List<Tweet_List>  list = null ;

    public NewTweet_LVAdapter(List<Tweet_List>  list){
        this.list = list ;
    }

    @Override
    public int getCount() {
        return list == null ? 0 :list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View view,  ViewGroup parent) {
       ViewHolder holder = null;
        if(view == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_lv_item,parent,false);
            holder = new ViewHolder();
            ButterKnife.bind(holder,view);
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();

        holder.tv_username.setText(list.get(position).getAuthor()+"");
        holder.tv_content.setText(Html.fromHtml(list.get(position).getBody())+"");
        holder.tv_count.setText(list.get(position).getCommentCount()+"");

        //获取时间
        holder.tv_pubDate.setText(TimeFormatUtils.getTimesAway(
                list.get(position).getPubDate())+"");

        //获取头像
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(list.get(position).getPortrait()))
                .setAutoPlayAnimations(true)
                .build();
        holder.portrait.setController(controller);

        //获取小图片
        if(!TextUtils.isEmpty(list.get(position).getImgSmall())){
            final String imgUrl = list.get(position).getImgSmall();
            holder.img_small.setTag(imgUrl);
            HttpHelper helper = HttpHelper.getInstance();
           final ViewHolder finalHolder = holder ;
            helper.httpRequest(imgUrl, new HttpHelper.BitmapCallBack() {
                @Override
                public void getBack(Object bitmap) {
                    final Bitmap img = (Bitmap) bitmap;
                  new Handler(Looper.getMainLooper()).post(new Runnable() {
                      @Override
                      public void run() {
                          if(imgUrl.equals((String)(finalHolder.img_small.getTag()))){
                              finalHolder.img_small.setImageBitmap(img);
                          }
                      }
                  });
                }

                @Override
                public void onErr(IOException e) {

                }
            });
        }
        return view;
    }

    public class ViewHolder{

        @BindView(R.id.tweet_tv_username)
        public TextView tv_username ;  //用户名
        @BindView(R.id.tweet_tv_content)
        public TextView tv_content ;  // 内容
        @BindView(R.id.tweet_tv_commentCount)
        public TextView tv_count ;  // 评论数
        @BindView(R.id.tweet_tv_pubDate)
        public TextView tv_pubDate ;//发布时间

        @BindView(R.id.tweet_img_portrait)
        public SimpleDraweeView portrait ; //头像
        @BindView(R.id.tweet_small_img)
        public ImageView img_small ; //内容小图片
    }
}
