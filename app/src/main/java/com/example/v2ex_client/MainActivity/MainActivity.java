package com.example.v2ex_client.MainActivity;

import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

import com.example.v2ex_client.R;
import com.example.v2ex_client.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {
    MainPresent mainPresent;
    @BindView(R.id.page_tab)
    PagerTabStrip pageTab;
    @BindView(R.id.view_page)
    ViewPager viewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresent = new MainPresent();
        mainPresent.attachView(this);
        mainPresent.setPagerAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresent.detachView();
    }

    @Override
    public void setPageAdapter(MainPresent.PagerAdapter pageAdapter) {
        viewPage.setAdapter(pageAdapter);
    }


}
