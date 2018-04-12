package com.example.v2ex_client.MainActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.v2ex_client.R;
import com.example.v2ex_client.base.BaseActivity;
import com.example.v2ex_client.model.ACache;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {
    MainPresent mainPresent;
    @BindView(R.id.page_tab)
    PagerTabStrip pageTab;
    @BindView(R.id.view_page)
    ViewPager viewPage;

    //ASimpleCache File
    public File ACACHE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresent = new MainPresent();
        mainPresent.attachView(this);
        mainPresent.setPagerAdapter();
        ACACHE = new File(getCacheDir(), ACache.CACHE_NAME);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresent.detachView();
    }

    @Override
    public void onBackPressed(){
        //showToast("点击到返回了");
        super.onBackPressed();

    }

    @Override
    public void setPageAdapter(MainPresent.PagerAdapter pageAdapter) {
        viewPage.setAdapter(pageAdapter);
    }


}
