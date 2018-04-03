package com.example.v2ex_client.ListFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.v2ex_client.R;
import com.example.v2ex_client.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 肖宇轩 on 2018/4/3.
 */

public class ListFragment extends BaseFragment implements ListView {

    @BindView(R.id.list_recycler_view)
    RecyclerView listRecyclerView;
    Unbinder unbinder;
    ListFraPresent listFraPresent;


    @Override
    public int getContentViewId() {
        return R.layout.list_fragment;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        listFraPresent = new ListFraPresent();
        listFraPresent.attachView(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        listFraPresent.detachView();
    }


    @Override
    public void setRecyclerAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listRecyclerView.setLayoutManager(layoutManager);
        listRecyclerView.setAdapter(adapter);
    }
}
