package com.example.v2ex_client.ListFragment;

import android.support.v7.widget.RecyclerView;

import com.example.v2ex_client.base.BaseView;

/**
 * Created by 肖宇轩 on 2018/4/3.
 */

public interface ListView extends BaseView {
    void setRecyclerAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter);
}