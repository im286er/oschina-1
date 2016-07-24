package com.liang.oschina.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class Tweet_List implements Parcelable{
        public int id ;
        public int commentCount;
        public String author ;
        public String imgBig;
        public String portrait; //头像
        public String body ;
        public String pubDate;
        public String imgSmall ;

    public Tweet_List(){}
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgBig() {
        return imgBig;
    }

    public void setImgBig(String imgBig) {
        this.imgBig = imgBig;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }

    protected Tweet_List(Parcel in) {
            id = in.readInt();
            commentCount = in.readInt();
            author = in.readString();
            imgBig = in.readString();
            portrait = in.readString();
            body = in.readString();
            pubDate = in.readString();
            imgSmall = in.readString();
        }

        public static final Creator<Tweet_List> CREATOR = new Creator<Tweet_List>() {
            @Override
            public Tweet_List createFromParcel(Parcel in) {
                return new Tweet_List(in);
            }

            @Override
            public Tweet_List[] newArray(int size) {
                return new Tweet_List[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(commentCount);
            dest.writeString(author);
            dest.writeString(imgBig);
            dest.writeString(portrait);
            dest.writeString(body);
            dest.writeString(pubDate);
            dest.writeString(imgSmall);
        }
}

