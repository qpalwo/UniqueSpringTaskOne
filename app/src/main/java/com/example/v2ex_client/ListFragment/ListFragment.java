package com.example.v2ex_client.ListFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.v2ex_client.MemberFragment.MemberFragment;
import com.example.v2ex_client.PostFragment.PostFragment;
import com.example.v2ex_client.R;
import com.example.v2ex_client.base.BaseFragment;
import com.example.v2ex_client.model.Bean.Member;
import com.example.v2ex_client.model.Bean.Post;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 肖宇轩 on 2018/4/3.
 */

public class ListFragment extends BaseFragment implements ListView {


    @BindView(R.id.list_recycler_view)
    RecyclerView listRecyclerView;
    @BindView(R.id.smart_refresh)
    RefreshLayout refreshLayout;
    Unbinder unbinder;
    ListFraPresent listFraPresent;

    @Override
    public int getContentViewId() {
        return R.layout.list_fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        listFraPresent = new ListFraPresent();
        listFraPresent.attachView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listRecyclerView.setLayoutManager(layoutManager);
        listFraPresent.setAdapter();
        //刷新加载设置
        initRefreshLayout(getArguments().getString("type"));
        refreshLayout.autoRefresh();
        listFraPresent.refreshList(getArguments().getString("type"));
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        listFraPresent.detachView();
    }

    @Override
    public RecyclerView getListRecyclerView() {
        return listRecyclerView;
    }


    private void initRefreshLayout(final String type) {
        if (isAttachedContext()) {
            refreshLayout.setRefreshHeader(
                    new BezierRadarHeader(getContext()));//设置Header
            refreshLayout.setRefreshFooter(
                    new ClassicsFooter(getContext()));//设置Footer
        }
        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）

        refreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
        refreshLayout.setFooterHeight(100);//Footer标准高度（显示上拉高度>=标准高度 触发加载）

        refreshLayout.setHeaderMaxDragRate(2);//最大显示下拉高度/Header标准高度
        refreshLayout.setFooterMaxDragRate(2);//最大显示下拉高度/Footer标准高度
        refreshLayout.setHeaderTriggerRate(1);//触发刷新距离 与 HeaderHeight 的比率1.0.4
        refreshLayout.setFooterTriggerRate(1);//触发加载距离 与 FooterHeight 的比率1.0.4

        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        refreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        refreshLayout.setEnablePureScrollMode(false);//是否启用纯滚动模式
        refreshLayout.setEnableNestedScroll(false);//是否启用嵌套滚动
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        refreshLayout.setEnableHeaderTranslationContent(true);//是否下拉Header的时候向下平移列表或者内容
        refreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        refreshLayout.setEnableLoadMoreWhenContentNotFull(true);//是否在列表不满一页时候开启上拉加载功能
        refreshLayout.setEnableFooterFollowWhenLoadFinished(false);//是否在全部加载结束之后Footer跟随内容1.0.4
        refreshLayout.setEnableOverScrollDrag(false);//是否启用越界拖动（仿苹果效果）1.0.4
        refreshLayout.setEnableScrollContentWhenRefreshed(true);//是否在刷新完成时滚动列表显示新的内容 1.0.5

        refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
        refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                listFraPresent.refreshList(type);
            }
        });

//        refreshLayout.autoRefresh();//自动刷新
//        refreshLayout.autoLoadMore();//自动加载
//        refreshLayout.autoRefresh(400);//延迟400毫秒后自动刷新
//        refreshLayout.autoLoadMore(400);//延迟400毫秒后自动加载
//        refreshlayout.finishRefresh();//结束刷新
//        refreshlayout.finishLoadMore();//结束加载
//        refreshlayout.finishRefresh(3000);//延迟3000毫秒后结束刷新
//        refreshlayout.finishLoadMore(3000);//延迟3000毫秒后结束加载
//        refreshlayout.finishRefresh(false);//结束刷新（刷新失败）
//        refreshlayout.finishLoadMore(false);//结束加载（加载失败）
//        refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
//        refreshLayout.setNoMoreData(false);//恢复没有更多数据的原始状态 1.0.5
    }



    public void addPostFragment(Post post){
        refreshLayout.setEnableRefresh(false);
        PostFragment postFragment = new PostFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Post", post);
        postFragment.setArguments(bundle);
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = childFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, postFragment)
                .addToBackStack("post")
                .commit();
    }

    public void addMemberFragment(Member member) {
        refreshLayout.setEnableRefresh(false);
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
    public boolean onBackPressed(){
        refreshLayout.setEnableRefresh(true);
        return super.onBackPressed();
    }

    public RefreshLayout getRefreshLayout() {
        return refreshLayout;
    }


}
