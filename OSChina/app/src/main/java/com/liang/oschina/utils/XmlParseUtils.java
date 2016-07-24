package com.liang.oschina.utils;

import android.util.Log;

import com.liang.oschina.beans.Answer_Post;
import com.liang.oschina.beans.Blog_List;
import com.liang.oschina.beans.News_List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class XmlParseUtils {

    /**
     * 获得新闻列表
     * @param is
     * @return
     */
    public static List<News_List> getNewsList(InputStream is) {

        List<News_List> news_lists = null;
        News_List news = null;
        try {
            XmlPullParser parser =
                    XmlPullParserFactory.newInstance().newPullParser();

            parser.setInput(is, "utf-8");

            int event = parser.getEventType();
            Log.i("TAG", "getNewsList:  进来了 Xml解析");
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        news_lists = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("news".equals(parser.getName())) {
                            news = new News_List();
                        }
                        if ("id".equals(parser.getName())) {
                            int id = Integer.parseInt(parser.nextText().trim());

                            news.setId(id);
                        }
                        if ("title".equals(parser.getName())) {
                            String title = parser.nextText().trim();

                            news.setTitle(title);
                        }
                        if ("body".equals(parser.getName())) {
                            String body = parser.nextText().trim();

                            news.setBody(body);
                        }

                        if ("pubDate".equals(parser.getName())) {
                            String pubDate = parser.nextText().trim();

                            news.setPubDate(pubDate);
                        }

                        if ("commentCount".equals(parser.getName())) {
                            int commentCount = Integer.parseInt(parser.nextText().trim());

                            news.setCommentCount(commentCount);
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if ("news".equals(parser.getName())) {
                            news_lists.add(news);//添加到列表
                        }
                        break;
                }
                event = parser.next();
            }

            return news_lists;
        } catch (XmlPullParserException e) {
            Log.i("TAG", "getNewsList:  、Xml解析 异常");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("TAG", "getNewsList:  、Xml解析 异常");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得博客列表
     * @param is
     * @return
     */
    public static List<Blog_List> getBlogList(InputStream is) {
        List<Blog_List> blog_lists = null;
        Blog_List blog = null;
        try {
            XmlPullParser parser =
                    XmlPullParserFactory.newInstance().newPullParser();

            parser.setInput(is, "utf-8");

            int event = parser.getEventType();
            Log.i("TAG", "getBlogList:  进来了 Xml解析");
            while(event != XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_DOCUMENT :
                        blog_lists = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("blog".equals(parser.getName())) {
                            blog = new Blog_List();
                        }
                        if("id".equals(parser.getName().trim())){
                            blog.setId(Integer.parseInt(parser.nextText().trim()));
                        }

                        if("title".equals(parser.getName().trim())){
                            blog.setTitle(parser.nextText().trim());
                        }

                        if("body".equals(parser.getName().trim())){
                            blog.setBody(parser.nextText().trim());
                        }
                        if("pubDate".equals(parser.getName().trim())){
                            blog.setPubDate(parser.nextText().trim());
                        }

                        if("authorname".equals(parser.getName().trim())){
                            blog.setAuthor(parser.nextText().trim());
                        }
                        if("commentCount".equals(parser.getName().trim())){
                            blog.setCommentCount(Integer.parseInt(parser.nextText().trim()));
                        }

                        if("documentType".equals(parser.getName().trim())){
                            blog.setType(Integer.parseInt(parser.nextText().trim()));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("blog".equals(parser.getName())) {
                            blog_lists.add(blog);//添加到列表
                        }
                }
                event = parser.next() ;
            }

            return blog_lists;
        } catch (XmlPullParserException e) {
            Log.i("TAG", "getBlogList:   Xml解析异常");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("TAG", "getBlogList:   Xml解析异常");
            e.printStackTrace();
        }

        return null;
    }


    /**
     *  获得问答列表
     * @param is
     * @return
     */
    public static List<Answer_Post> getAnswersList(InputStream is) {
        List<Answer_Post> answer_Posts = null;
        Answer_Post answer_post = null;
        try {
            XmlPullParser parser =
                    XmlPullParserFactory.newInstance().newPullParser();

            parser.setInput(is, "utf-8");

            int event = parser.getEventType();

            while(event != XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_DOCUMENT :
                        answer_Posts = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("post".equals(parser.getName())) {
                            answer_post = new Answer_Post();
                        }else if("id".equals(parser.getName().trim())){

                            answer_post.setId(Integer.parseInt(parser.nextText().trim()));

                        }else if("title".equals(parser.getName().trim())){
                            answer_post.setTitle(parser.nextText().trim());
                        }else if("body".equals(parser.getName().trim())){

                            answer_post.setBody(parser.nextText().trim());
                        }else if("pubDate".equals(parser.getName().trim())){

                            answer_post.setPubDate(parser.nextText().trim());
                        }else if("author".equals(parser.getName().trim())){

                            answer_post.setAuthor(parser.nextText().trim());
                        }else if("answerCount".equals(parser.getName().trim())){

                            answer_post.setCommentCount(Integer.parseInt(parser.nextText().trim()));
                        }else if("viewCount".equals(parser.getName().trim())){
                            answer_post.setViewCount(Integer.parseInt(parser.nextText().trim()));

                        }else if("portrait".equals(parser.getName().trim())){
                            answer_post.setPortrait(parser.nextText().trim());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("post".equals(parser.getName())) {
                            answer_Posts.add(answer_post);//添加到列表
                        }
                }
                event = parser.next() ;
            }

            return answer_Posts;
        } catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }
}