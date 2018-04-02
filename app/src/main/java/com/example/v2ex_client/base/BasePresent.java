package com.example.v2ex_client.base;

/**
 * Created by 肖宇轩 on 2018/3/3.
 */

public class BasePresent <V extends BaseView> {

    private V mView;

    /**
     * 绑定view  初始化时调用
     * @param mView
     */
    public void attachView(V mView){
        this.mView = mView;
    }

    /**
     * 解绑view， ondestroy 调用
     */
    public void detachView(){
        this.mView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached(){
        return mView != null;
    }
    /**
     * 获取连接的view
     */
    public V getView(){
        return mView;
    }
}
