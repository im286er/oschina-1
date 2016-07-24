package com.liang.oschina.utils;

import com.alibaba.fastjson.JSON;
import com.liang.oschina.beans.DetailUtils;
import com.liang.oschina.beans.Tweet_List;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class JsonParseUtils {

/*    public static List<Answer_Post> getAnswerList(String str){
        List<Answer_Post> list = null;
        try {
            JSONObject json = new JSONObject(str);
            org.json.JSONArray array = json.getJSONArray("post_list");
            list = JSON.parseArray(array.toString(),Answer_Post.class);

            return list ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null ;
    }*/

    /**
     * 获得动弹列表
     * @param str
     * @return
     */
    public static List<Tweet_List>  getTweetList(String str){
        List<Tweet_List> list = null;
        try {
            JSONObject json = new JSONObject(str);
            org.json.JSONArray array = json.getJSONArray("tweetlist");
            list = JSON.parseArray(array.toString(),Tweet_List.class);

            return list ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null ;
    }

    /**
     * 获得资讯详情
     * @param str
     * @return
     */
    public static DetailUtils.NewsDetail getNewDetails(String str){

        try {
            JSONObject json = new JSONObject(str);
            DetailUtils.NewsDetail newsDetail = new DetailUtils().new NewsDetail();
            newsDetail.id = json.optInt("id");
            newsDetail.body = json.optString("body");
            newsDetail.commentCount = json.optInt("commentCount");
            newsDetail.pubDate = json.optString("pubDate");
            newsDetail.favorite = json.optInt("favorite");
            newsDetail.title = json.optString("title");
            newsDetail.author = json.optString("author");
            return newsDetail ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null ;
    }

    /**
     * 获得博客详情
     * @param str
     * @return
     */
    public static DetailUtils.BlogDetail getBlogDetails(String str){

        try {
            JSONObject json = new JSONObject(str);
            DetailUtils.BlogDetail blogDetail = new DetailUtils().new BlogDetail();
            blogDetail.id = json.optInt("id");
            blogDetail.body = json.optString("body");
            blogDetail.commentCount = json.optInt("commentCount");
            blogDetail.pubDate = json.optString("pubDate");
            blogDetail.favorite = json.optInt("favorite");
            blogDetail.title = json.optString("title");
            blogDetail.author = json.optString("author");
            blogDetail.url = json.optString("url");
            return blogDetail ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null ;
    }

    /**
     * 获得帖子详情
     * @param str
     * @return
     */
    public static DetailUtils.AnswerDetail getTweetDetails(String str){

        try {
            JSONObject json = new JSONObject(str);
            DetailUtils.AnswerDetail answerDetail = new DetailUtils().new AnswerDetail();
            answerDetail.id = json.optInt("id");
            answerDetail.body = json.optString("body");
            answerDetail.viewCount = json.optInt("viewCount");
            answerDetail.pubDate = json.optString("pubDate");
            answerDetail.favorite = json.optInt("favorite");
            answerDetail.title = json.optString("title");
            answerDetail.portrait = json.optString("portrait");
            answerDetail.author = json.optString("author");
            answerDetail.tags = json.optString("tags");
            answerDetail.url = json.optString("url");
            answerDetail.answerCount = json.optString("answerCount");
            return answerDetail ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null ;
    }
/*  answerCount ,  "author":"romeo2015",
    "id":2187228,
    "viewCount":54,
    "title":"model转bean",
    "portrait":"http://static.oschina.net/uploads/user/1156/2312245_50.jpg?t=1422434722000",
    "body":

      "pubDate":"2016-07-07 18:36:22",
    "favorite":0,
    "url":"http://www.oschina.net/question/2312245_2187228",
    "tags*/

}


