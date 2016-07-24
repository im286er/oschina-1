package com.liang.oschina.beans;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class News_List implements Parcelable{

    private int id  ;  //详情的 id
    private String title ; //标题
    private int commentCount ; //评论数
    private String pubDate ; //发表日期
    private String body ;
    public News_List(){

    }
    public News_List(int id, String title,
                     int commentCount, String pubDate,String body ) {
        this.id = id;
        this.title = title;
        this.commentCount = commentCount;
        this.pubDate = pubDate;
        this.body = body ;
    }

    protected News_List(Parcel in) {
        id = in.readInt();
        title = in.readString();
        commentCount = in.readInt();
        pubDate = in.readString();
        body = in.readString() ;
    }

    public static final Creator<News_List> CREATOR = new Creator<News_List>() {
        @Override
        public News_List createFromParcel(Parcel in) {
            return new News_List(in);
        }

        @Override
        public News_List[] newArray(int size) {
            return new News_List[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(commentCount);
        dest.writeString(pubDate);
        dest.writeString(body);
    }

    @Override
    public String toString() {
        return "News_List{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", commentCount=" + commentCount +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
