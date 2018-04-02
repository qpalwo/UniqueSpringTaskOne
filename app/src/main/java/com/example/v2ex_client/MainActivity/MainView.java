package com.example.v2ex_client.MainActivity;

import android.support.v4.app.FragmentManager;

import com.example.v2ex_client.base.BaseView;

/**
 * Created by 肖宇轩 on 2018/4/2.
 */

public interface MainView extends BaseView {

    void setPageAdapter(MainPresent.PagerAdapter pageAdapter);

    FragmentManager getSupportFragmentManager();
}
