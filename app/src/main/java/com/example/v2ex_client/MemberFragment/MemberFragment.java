package com.example.v2ex_client.MemberFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.v2ex_client.PostFragment.PostFragment;
import com.example.v2ex_client.R;
import com.example.v2ex_client.base.BaseFragment;
import com.example.v2ex_client.model.Bean.Member;
import com.example.v2ex_client.model.Bean.Post;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 肖宇轩 on 2018/4/5.
 */

public class MemberFragment extends BaseFragment implements MemberView {

    @BindView(R.id.member_recycler)
    RecyclerView memberRecycler;
    Unbinder unbinder;
    MemberFraPresent memberFraPresent;
    Member member;

    @Override
    public int getContentViewId() {
        return R.layout.member_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();
        member = (Member) bundle.getSerializable("Member");
        memberFraPresent = new MemberFraPresent(member);
        memberFraPresent.attachView(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        memberRecycler.setLayoutManager(linearLayoutManager);
        memberFraPresent.setAdapter();
        memberFraPresent.refreshData();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        memberFraPresent.detachView();
        unbinder.unbind();
    }

    public RecyclerView getMemberRecycler() {
        return memberRecycler;
    }

    @Override
    public void addPostFragment(Post post) {
        PostFragment postFragment = new PostFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Post", post);
        postFragment.setArguments(bundle);
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = childFragmentManager.beginTransaction();
        transaction.add(R.id.member_fragment, postFragment)
                .addToBackStack("post")
                .commit();

    }

    @Override
    public void addMemberFragment(Member member) {
        MemberFragment memberFragment = new MemberFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Member", member);
        memberFragment.setArguments(bundle);
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = childFragmentManager.beginTransaction();
        transaction.add(R.id.member_fragment, memberFragment)
                .addToBackStack("member")
                .commit();
    }

}
