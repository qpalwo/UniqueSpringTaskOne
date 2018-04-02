package com.example.v2ex_client.base;

import android.content.Context;

/**
 * Created by 肖宇轩 on 2018/3/3.
 */

public interface BaseView {

    /**
     * 显示正在加载view
     */
    void showLoading();
    /**
     * 关闭正在加载view
     */
    void hideLoading();
    /**
     * 显示提示
     * @param msg
     */
    void showToast(String msg);


    /**
     * 获取上下文
     * @return 上下文
     */
    Context getContext();
}
