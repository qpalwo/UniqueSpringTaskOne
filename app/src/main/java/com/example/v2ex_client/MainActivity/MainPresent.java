package com.example.v2ex_client.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        getView().setPageAdapter(new PagerAdapter(
                getView().getSupportFragmentManager(),
                fragments, tabs));
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
