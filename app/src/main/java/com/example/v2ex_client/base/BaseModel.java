package com.example.v2ex_client.base;

import android.content.Context;

import java.util.Map;

/**
 * Created by 肖宇轩 on 2018/3/3.
 */

public abstract class BaseModel <T>{

    //数据请求参数
    protected String[] mParams;

    /**
     * 设置数据请求参数
     * @param args 参数数组
     */
    public  BaseModel params(String... args){
        mParams = args;
        return this;
    }

    // 添加Callback并执行数据请求
    public abstract void execute(CallBack<T> callback);


    // 执行Get网络请求
    public void requestGetAPI(String url, CallBack<T> callback){

    }

    // 执行Post网络请求
    public void requestPostAPI(String url, String request, CallBack<T> callback){

    }

    //数据库获取数据
    public void getAllData(Context context, CallBack<T> CallBack){

    }

    //数据库添加数据
    public void insertData(Context context, Map map, CallBack<String> callBack){

    }

    //数据库修改数据
    public void editData(Context context, Map oldMap, Map newMap, CallBack<T> callBack){

    }

    //数据库删除数据
    public void deleteData(Context context, Map map, CallBack<String> callBack){

    }
}
