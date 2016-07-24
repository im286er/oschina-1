package com.liang.oschina.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class Answer_Post implements Parcelable {

    private int id ;  //id
    private int answerCount ; //评论数
    private int viewCount ;//浏览数
    private String title ; //标题
    private String portrait ;//头像地址
    private String author ;//作者名字
    private String pubDate ;//发布日期
    private String body ;// 发布内容


    public Answer_Post(){}


    protected Answer_Post(Parcel in) {
        id = in.readInt();
        answerCount = in.readInt();
        viewCount = in.readInt();
        title = in.readString();
        portrait = in.readString();
        author = in.readString();
        pubDate = in.readString();
        body = in.readString();

    }

    public static final Creator<Answer_Post> CREATOR = new Creator<Answer_Post>() {
        @Override
        public Answer_Post createFromParcel(Parcel in) {
            return new Answer_Post(in);
        }

        @Override
        public Answer_Post[] newArray(int size) {
            return new Answer_Post[size];
        }
    };

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

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

    public int getCommentCount() {
        return answerCount;
    }

    public void setCommentCount(int commentCount) {
        this.answerCount = commentCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(answerCount);
        dest.writeInt(viewCount);
        dest.writeString(title);
        dest.writeString(portrait);
        dest.writeString(author);
        dest.writeString(pubDate);
        dest.writeString(body);
    }
}
