package com.example.v2ex_client.PostFragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.v2ex_client.MemberFragment.MemberFragment;
import com.example.v2ex_client.R;
import com.example.v2ex_client.base.BaseFragment;
import com.example.v2ex_client.model.Bean.Member;
import com.example.v2ex_client.model.Bean.Post;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 肖宇轩 on 2018/4/5.
 */

public class PostFragment extends BaseFragment implements PostView {
    private Post post;
    private PostFraPresent postFraPresent;
    @BindView(R.id.post_recycler)
    RecyclerView postRecycler;
    Unbinder unbinder;

    @Override
    public int getContentViewId() {
        return R.layout.post_fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();
        post = (Post) bundle.getSerializable("Post");
        postFraPresent = new PostFraPresent(post);
        postFraPresent.attachView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        postRecycler.setLayoutManager(layoutManager);
        postFraPresent.setAdapter();
        postFraPresent.refreshData();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
       // postFraPresent.refreshData();
    }

    public RecyclerView getPostRecycler() {
        return postRecycler;
    }

    @Override
    public void addMemberFragment(Member member) {
        MemberFragment memberFragment = new MemberFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Member", member);
        memberFragment.setArguments(bundle);
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = childFragmentManager.beginTransaction();
        transaction.add(R.id.post_fragment, memberFragment)
                .addToBackStack("member")
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        postFraPresent.detachView();
    }


}
