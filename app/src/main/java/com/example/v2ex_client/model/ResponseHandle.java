package com.example.v2ex_client.model;

import com.example.v2ex_client.base.CallBack;
import com.example.v2ex_client.model.Bean.Member;
import com.example.v2ex_client.model.Bean.Node;
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

    public static void memberHandler(String json, CallBack<Member> callBack){
        Gson gson = new Gson();
        Member member = gson.fromJson(json, Member.class);
        callBack.onSuccess(member);
        callBack.onComplete();
    }

    public static void nodeHandler(String json, CallBack<Node> callBack){
        Gson gson = new Gson();
        Node node = gson.fromJson(json, Node.class);
        callBack.onSuccess(node);
        callBack.onComplete();
    }
}
