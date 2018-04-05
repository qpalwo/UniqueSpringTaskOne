package com.example.v2ex_client.model;

import com.example.v2ex_client.base.CallBack;
import com.example.v2ex_client.model.Bean.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 肖宇轩 on 2018/4/4.
 */

public class ResponseHandle {
    public static void postHandler(String json, CallBack<List<Post>> callBack)  {
        Gson gson = new Gson();
        List<Post> posts = gson.fromJson(json, new TypeToken<List<Post>>() {}.getType());
        callBack.onSuccess(posts);
        callBack.onComplete();
    }
}
