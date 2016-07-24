package com.liang.oschina.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/1 0001.
 */
public class HttpHelper {

    private static  OkHttpClient client ;
    private static  HttpHelper helper ;
    private HttpHelper(){
        client = new OkHttpClient() ;
    }
    public final static HttpHelper getInstance(){
        if(helper == null){
            synchronized (HttpHelper.class){
                if(helper== null){
                    helper = new HttpHelper();
                }
            }
        }
        return helper ;
    }

    /**
     * @param url
     */
    public  void httpRequest(String url,final CallBack cb){
        if(url == null) return ;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                    if(cb != null) {
                        cb.onErr(e);
                    }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(cb != null){
                    if(cb instanceof StringCallBack){
                        StringCallBack stringCallBack = (StringCallBack) cb;
                        stringCallBack.getBack(response.body().string());

                    }else if(cb instanceof BitmapCallBack){

                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        BitmapCallBack bitmapCallBack = (BitmapCallBack) cb;
                        bitmapCallBack.getBack(bitmap);
                    }else{
                        cb.getBack(response.body().byteStream());
                    }
                }

            }
        });
    }

    //字符串回调接口
    public interface  StringCallBack extends CallBack{
        @Override
        public void getBack(Object string);
        @Override
        public void onErr(IOException e);
    }


    //图片回调接口
    public interface BitmapCallBack extends CallBack {
        @Override
        public void getBack(Object bitmap);
        @Override
        public void onErr(IOException e);
    }


    //返回响应
    public  interface  CallBack{
        public void getBack(Object inputStream);
        public void onErr(IOException e);
    }


    public  void httpRequestByPost(String url){
        Request request = new Request.Builder()
                .url(url)
               // .post()
                .build();
    }


    public Map<String,String>  getParams(){

        return null ;
    }

}

