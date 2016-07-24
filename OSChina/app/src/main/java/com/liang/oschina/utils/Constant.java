package com.liang.oschina.utils;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class Constant {

    //新闻列表的地址
    public static String news_list_path = "http://www.oschina.net/action/api/news_list?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1" +
            "&pageSize=20&catalog=1&dataType=xml&page=";

    //新闻详情的地址
    public static String news_detail_path = "http://www.oschina.net/action/openapi/news_detail?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&id=";

    //博客列表的地址
    public static String blog_list_path = "http://www.oschina.net/action/api/blog_list?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&" +
            "pageSize=20&dataType=xml&page=";
    //博客详情列表
    public static String blog_detail_path = "http://www.oschina.net/action/openapi/blog_detail?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&&id=";

    //提问
   public static String answer_path_ask =" http://www.oschina.net/action/api/post_list?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&catalog=1&dataType=xml&page=";
    //分享
    public static String answer_path_share = "http://www.oschina.net/action/api/post_list?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&catalog=2&dataType=xml&page=";
    //综合
    public static String answer_path_total = "http://www.oschina.net/action/api/post_list?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&catalog=3&dataType=xml&page=";
    //职业
    public static String answer_path_opp = "http://www.oschina.net/action/api/post_list?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&catalog=100&dataType=xml&page=";
    //站务
    public static String answer_path_office = "http://www.oschina.net/action/api/post_list?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&catalog=4&dataType=xml&page=";

    public static String answer_path_post = "http://www.oschina.net/action/openapi/post_detail?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&id=";



    //动弹 news
    public static String tweet_path_news ="http://www.oschina.net/action/openapi/tweet_list?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&pageSize=20" +
            "&dataType=json&user=0&id=";
    //动弹 hot
    public static String tweet_path_hot ="http://www.oschina.net/action/openapi/tweet_list?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&pageSize=20&dataType=json&user=-1&id=";
    //动弹my
    public static String tweet_path_my ="http://www.oschina.net/action/openapi/tweet_list?" +
            "access_token=667f1d08-78fc-4b9a-b862-0c7e0b7192f1&pageSize=20&dataType=json&user=1&id=";
}
