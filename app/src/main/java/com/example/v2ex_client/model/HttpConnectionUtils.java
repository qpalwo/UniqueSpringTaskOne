package com.example.v2ex_client.model;

import android.os.Handler;

import com.example.v2ex_client.base.CallBack;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by 肖宇轩 on 2018/4/4.
 */

public class HttpConnectionUtils {

    public static final String V2EX_HOT = "https://www.v2ex.com/api/topics/hot.json";
    public static final String V2EX_LATEST = "https://www.v2ex.com/api/topics/latest.json";
    public static final String V2EX_USER = "https://www.v2ex.com/api/members/show.json?";

    public static void getResponse(final String type, final String request, final String address, final CallBack<String> callBack){


        if (type.equals("GET") || type.equals("POST")){
            //  此处使用handler完成线程间通讯
            final Handler handler = new Handler();
            new Thread(new Runnable() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                StringBuilder response = new StringBuilder();
                @Override
                public void run() {
                    try {
                        //设置请求参数
                        URL url = new URL(address);
                        connection = (HttpURLConnection)url.openConnection();
                        connection.setRequestMethod(type);
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);

                        if(type.equals("POST")){
                            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                            outputStream.writeBytes(request);
                        }
                        //获取输入
                        InputStream inputStream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        String temp;
                        while ((temp = reader.readLine()) != null){
                            response.append(temp);
                        }
                        inputStream.close();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(response.toString());
                                callBack.onComplete();
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if(reader != null){
                                reader.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (connection != null){
                            connection.disconnect();
                        }
                    }
                }
            }).start();

        }else {
            callBack.onFailure("请输入正确的请求类型");
        }


    }
}
