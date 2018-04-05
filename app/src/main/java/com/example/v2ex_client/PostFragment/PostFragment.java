package com.example.v2ex_client.PostFragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.v2ex_client.R;
import com.example.v2ex_client.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 肖宇轩 on 2018/4/5.
 */

public class PostFragment extends BaseFragment implements PostView {
    @BindView(R.id.post_title)
    TextView postTitle;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.creat_time)
    TextView creatTime;
    @BindView(R.id.post_checked)
    TextView postChecked;
    @BindView(R.id.user_img)
    ImageView userImg;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.post_recycler)
    RecyclerView postRecycler;
    Unbinder unbinder;

    @Override
    public int getContentViewId() {
        return R.layout.post_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
