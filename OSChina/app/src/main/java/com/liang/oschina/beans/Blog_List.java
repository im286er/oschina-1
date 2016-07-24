package com.liang.oschina.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class Blog_List implements Parcelable {

    private int id ;//博客id
    private String author ;//投递者名称
    private String title ;//博客标题
    private String pubDate ;//发布日期
    private int commentCount ;//评论数
    private int type ;// 1 为 原创 4 为转载
    private String body ; //内容

    public Blog_List(){}
    public Blog_List(Parcel in) {
        id = in.readInt();
        author = in.readString();
        title = in.readString();
        pubDate = in.readString();
        commentCount = in.readInt();
        type = in.readInt();
        body = in.readString() ;
    }

    public static final Creator<Blog_List> CREATOR = new Creator<Blog_List>() {
        @Override
        public Blog_List createFromParcel(Parcel in) {
            return new Blog_List(in);
        }

        @Override
        public Blog_List[] newArray(int size) {
            return new Blog_List[size];
        }
    };

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Blog_List{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", commentCount=" + commentCount +
                ", type=" + type +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(pubDate);
        dest.writeInt(commentCount);
        dest.writeInt(type);
        dest.writeString(body);
    }
}
