package com.example.v2ex_client.PostFragment;

import android.support.v7.widget.RecyclerView;

import com.example.v2ex_client.base.BaseView;
import com.example.v2ex_client.model.Bean.Member;

/**
 * Created by 肖宇轩 on 2018/4/5.
 */

public interface PostView extends BaseView {
    RecyclerView getPostRecycler();

    void addMemberFragment(Member member);


}
