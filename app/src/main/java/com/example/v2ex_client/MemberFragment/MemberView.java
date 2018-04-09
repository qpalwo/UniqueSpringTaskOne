package com.example.v2ex_client.MemberFragment;

import android.support.v7.widget.RecyclerView;

import com.example.v2ex_client.base.BaseView;
import com.example.v2ex_client.model.Bean.Member;
import com.example.v2ex_client.model.Bean.Post;

/**
 * Created by 肖宇轩 on 2018/4/5.
 */

public interface MemberView extends BaseView {

    RecyclerView getMemberRecycler();

    void addPostFragment(Post post);

    void addMemberFragment(Member member);
}
