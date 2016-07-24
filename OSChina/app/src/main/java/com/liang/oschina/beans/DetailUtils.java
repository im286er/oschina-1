package com.liang.oschina.beans;

/**
 * Created by Administrator on 2016/7/1 0001.
 */
public class DetailUtils {
    //资讯
    public class NewsDetail{
        public int id ;
        public int commentCount ;
        public int favorite ;
        public String author ;
        public String title;
        public String body ;
        public String pubDate ;
    }


    //博客
    public class BlogDetail{
        public int id ;
        public int favorite ;
        public int commentCount;
        public String body ;
        public String pubDate ;
        public String author;
        public String title ;
        public String url ;

    }

    //帖子
    public class AnswerDetail{
        public int id ;
        public int viewCount ;
        public int favorite;
        public String pubDate;
        public String title;
        public String body;
        public String portrait;
        public String url;
        public String tags;
        public String author ;
        public String answerCount ;

    }
}
