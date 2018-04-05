package com.example.v2ex_client.MainActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.v2ex_client.ListFragment.ListFragment;
import com.example.v2ex_client.base.BaseFragment;
import com.example.v2ex_client.base.BasePresent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 肖宇轩 on 2018/4/2.
 */

public class MainPresent extends BasePresent<MainView> {

    public void setPagerAdapter() {
        List<BaseFragment> fragments = new ArrayList<>();
        String[] tabs = {"Latest", "Hot"};

        //实例化Latest页面
        Bundle latestBundle = new Bundle();
        latestBundle.putString("type", "latest_post");
        ListFragment latestFragment = new ListFragment();
        latestFragment.setArguments(latestBundle);

        //实例化Hot页面
        Bundle hotBundle = new Bundle();
        hotBundle.putString("type", "hot_post");
        ListFragment hotFragment = new ListFragment();
        hotFragment.setArguments(hotBundle);

        fragments.add((BaseFragment)latestFragment);
        fragments.add((BaseFragment)hotFragment);
        if (isViewAttached()) {
            getView().setPageAdapter(new PagerAdapter(
                    getView().getSupportFragmentManager(),
                    fragments,
                    tabs));
        }
    }


    public class PagerAdapter extends FragmentPagerAdapter {

        private List<BaseFragment> fragments;
        private String[] tabs;

        public PagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] tabs) {
            super(fm);
            this.fragments = fragments;
            this.tabs = tabs;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}
