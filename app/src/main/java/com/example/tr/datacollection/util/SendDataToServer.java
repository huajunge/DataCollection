package com.example.tr.datacollection.util;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by TR on 2017/7/18.
 */

public class SendDataToServer {
    private String url ="";
    public static final int SEND_SUCCESS=0X123;
    public static final int SEND_FAIL=0X124;
    private Handler handler;
    boolean sss = false;
    public SendDataToServer(Handler handler,String url){
        this.handler = handler;
        this.url = url;
        Log.i("httptest","ready");
    }

    public void SendDataToServer(String name,String pwd){
        final Map<String,String>map = new HashMap<String, String>();
        map.put("name",name);
        map.put("pwd",pwd);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.i("httptest","进入");
                    if(sendGetRequest(map,url,"utf-8")) {
                        handler.sendEmptyMessage(SEND_SUCCESS);
                        Log.i("httptest","成");
                    }else {
                       // Toast.makeText(, "", Toast.LENGTH_SHORT).show();
                        handler.sendEmptyMessage(SEND_FAIL);
                        Log.i("httptest","失");
                    }
                }catch (Exception e){
                    
                }
            }
        }).start();
    }
    public void SendDataToServer(String data){
        final Map<String,String>map = new HashMap<String, String>();
        map.put("data",data);
      //  map.put("pwd",pwd);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.i("httptest","进入");
                    if(sendGetRequest(map,url,"utf-8")) {
                        handler.sendEmptyMessage(SEND_SUCCESS);
                        Log.i("httptest","成");
                    }else {
                        // Toast.makeText(, "", Toast.LENGTH_SHORT).show();
                        handler.sendEmptyMessage(SEND_FAIL);
                        Log.i("httptest","失");
                    }
                }catch (Exception e){

                }
            }
        }).start();
    }
    private boolean sendGetRequest(Map<String,String>param,String url,String encoding) throws IOException  {
       final StringBuffer sb = new StringBuffer(url);
        Log.i("httptest","链 kais");
        if(!url.equals("")&!param.isEmpty()){
            Log.i("httptest","链ing");
            sb.append("?");
            for(Map.Entry<String,String>entry:param.entrySet()){
                sb.append(entry.getKey()+"=");
                sb.append(URLEncoder.encode(entry.getValue(),encoding));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length()-1);//删除最后的&
        }
        Log.i("httptest","链ing2");

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
           //     HttpsURLConnection conn = null;
                try{
                   // conn  = (HttpsURLConnection) new URL(sb.toString()).openConnection();
                    URL url1 = new URL(sb.toString());
                    conn = (HttpURLConnection) url1.openConnection();
                    Log.i("httptest","链ing3");
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    Log.i("httptest","链ing4");
                    if(conn.getResponseCode()==200){
                        Log.i("httptest","链cg");
                        sss  = true;
                    }
                }catch (Exception e){
                    Log.i("httptest","链ingii"+e.toString());
                }
            }
        }).start();

        return sss;
    }
}
