package com.example.v2ex_client.MemberFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.v2ex_client.R;
import com.example.v2ex_client.base.BasePresent;
import com.example.v2ex_client.base.CallBack;
import com.example.v2ex_client.model.Bean.Member;
import com.example.v2ex_client.model.Bean.MemberPost;
import com.example.v2ex_client.model.Bean.MemberReply;
import com.example.v2ex_client.model.Bean.Post;
import com.example.v2ex_client.model.DataUtil;
import com.example.v2ex_client.model.JsoupUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 肖宇轩 on 2018/4/5.
 */

public class MemberFraPresent extends BasePresent<MemberView> {
    Member member;

    MemberFraPresent(Member member) {
        this.member = member;
    }

    void setAdapter() {
        RecyclerView recyclerView;
        if (isViewAttached()) {
            recyclerView = getView().getMemberRecycler();
            recyclerView.setAdapter(new MemberRecyclerAdapter(
                    getView().getContext(),
                    member));
        }
    }

    public void refreshData(){
        JsoupUtils jsoup = new JsoupUtils();
        jsoup.getMemberPosts(member.getUrl(), new CallBack<List<MemberPost>>() {
            @Override
            public void onSuccess(List<MemberPost> data) {
                if(isViewAttached()){
                    MemberRecyclerAdapter memberRecyclerAdapter = (MemberRecyclerAdapter) getView()
                            .getMemberRecycler().getAdapter();
                    memberRecyclerAdapter.changePostData(data);
                }
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });

        jsoup.getMemberReplies(member.getUrl(), new CallBack<List<MemberReply>>() {
            @Override
            public void onSuccess(List<MemberReply> data) {
                if (isViewAttached()){
                    MemberRecyclerAdapter memberRecyclerAdapter = (MemberRecyclerAdapter)getView()
                            .getMemberRecycler().getAdapter();
                    memberRecyclerAdapter.changeReplyData(data);
                }
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void addPostFragment(Post post) {
        if (isViewAttached()) {
            getView().addPostFragment(post);
        }
    }

    private void addMemberFragment(Member member){
        if (isViewAttached()){
            getView().addMemberFragment(member);
        }
    }


    private interface ItemOnClickListener {
        void onItemClick(View view, int position);
    }

    private void changeMember(final Member member){
        JsoupUtils jsoupUtils = new JsoupUtils();
        jsoupUtils.getMemberInfo(member.getUsername(), new CallBack<Member>() {
            Member newMember;
            @Override
            public void onSuccess(Member data) {
                addMemberFragment(member);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    class MemberRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<MemberPost> memberPosts;

        private List<MemberReply> memberReplies;

        private Member member;

        private Context context;

        class MemberDetailHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @BindView(R.id.member_img)
            ImageView memberImg;
            @BindView(R.id.member_name)
            TextView memberName;
            @BindView(R.id.member_no)
            TextView memberNo;
            @BindView(R.id.member_time)
            TextView memberTime;
            ItemOnClickListener itemOnClickListener;

            public MemberDetailHolder(View itemView, ItemOnClickListener itemOnClickListener) {
                super(itemView);
                this.itemOnClickListener = itemOnClickListener;
                ButterKnife.bind(this, itemView);
            }

            @Override
            public void onClick(View v) {
                itemOnClickListener.onItemClick(v, getAdapterPosition());
            }
        }

        class MemberPostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            @BindView(R.id.member_title)
            TextView memberTitle;
            @BindView(R.id.tag)
            TextView tag;
            @BindView(R.id.member_id)
            TextView memberName;
            @BindView(R.id.time)
            TextView time;
            @BindView(R.id.member_last_reply)
            TextView memberLastReply;
            ItemOnClickListener itemOnClickListener;


            public MemberPostHolder(View itemView, ItemOnClickListener itemOnClickListener) {
                super(itemView);
                this.itemOnClickListener = itemOnClickListener;
                ButterKnife.bind(this, itemView);
            }

            @Override
            @OnClick({R.id.member_title, R.id.tag, R.id.member_id, R.id.member_last_reply})
            public void onClick(View v) {
                itemOnClickListener.onItemClick(v, getAdapterPosition());
            }

        }

        class MemberReplyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            @BindView(R.id.member_replied_created_name)
            TextView memberRepliedCreatedName;
            @BindView(R.id.member_replied_node)
            TextView memberRepliedNode;
            @BindView(R.id.member_replied_title)
            TextView memberRepliedTitle;
            @BindView(R.id.member_replied_time)
            TextView memberRepliedTime;
            @BindView(R.id.member_reply_content)
            TextView memberReplyContent;

            ItemOnClickListener itemOnClickListener;

            public MemberReplyHolder(View itemView, ItemOnClickListener itemOnClickListener) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                this.itemOnClickListener = itemOnClickListener;
            }

            @Override
            @OnClick({R.id.member_replied_created_name, R.id.member_replied_node, R.id.member_replied_title, R.id.member_replied_time})
            public void onClick(View v) {
                itemOnClickListener.onItemClick(v, getAdapterPosition());
            }

        }

        MemberRecyclerAdapter(Context context, Member member) {
            this.context = context;
            this.member = member;
            memberPosts = new ArrayList<>();
            memberReplies = new ArrayList<>();
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 0;//  用户信息
            } else if (position > 0 && memberPosts.size() > 0) {
                return 1;//  用户帖子
            } else if (position > memberPosts.size()) {
                return 2;//  用户回复
            } else {
                return 4;
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == 0) {
                View view = LayoutInflater.from(context).inflate
                        (R.layout.member_detail_item, parent, false);
                MemberDetailHolder memberDetailHolder = new MemberDetailHolder(view, new ItemOnClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //无需点击事件
                    }
                });
                return memberDetailHolder;

            } else if (viewType == 1) {
                View view = LayoutInflater.from(context).inflate
                        (R.layout.member_post_item, parent, false);
                MemberPostHolder memberPostHolder = new MemberPostHolder(view, new ItemOnClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (view.getId()) {
                            case R.id.member_title:
                                //TODO 获取详细帖子信息
                                addPostFragment(memberPosts.get(position - 1).getPost());
                                break;
                            case R.id.tag:
                                break;
                            case R.id.member_id:

                                break;
                            case R.id.member_last_reply:
                                changeMember(memberPosts.get(position - 1).getLastReply());
                                break;
                        }
                    }
                });
                return memberPostHolder;
            } else if (viewType == 2) {
                View view = LayoutInflater.from(context).inflate
                        (R.layout.member_reply_item, parent, false);
                MemberReplyHolder memberReplyHolder = new MemberReplyHolder(view, new ItemOnClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (view.getId()) {
                            case R.id.member_replied_created_name:
                                changeMember(memberReplies.get(position - memberPosts.size() - 1).getRepliedCreatedMember());
                                break;
                            case R.id.member_replied_node:
                                break;
                            case R.id.member_replied_title:
                                //TODO 帖子具体信息
                                addPostFragment(memberReplies.get(position - memberPosts.size() - 1).repliedPost);
                                break;
                        }
                    }
                });
                return memberReplyHolder;
            } else {
                //TODO  用户无回复或者用户无主题时的展示
                return null;
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MemberDetailHolder) {
                MemberDetailHolder memberDetailHolder = (MemberDetailHolder) holder;
                memberDetailHolder.memberName.setText(member.getUsername());
                memberDetailHolder.memberNo.setText("V2EX第" + member.getId() + "号会员");
                memberDetailHolder.memberTime.setText(DataUtil.convertTimeToFormat(member.getCreated()));
                Glide.with(context)
                        .load(member.getAvatar_normal())
                        .into(memberDetailHolder.memberImg);
            } else if (holder instanceof MemberPostHolder) {
                MemberPost memberPost = memberPosts.get(position - 1);
                MemberPostHolder memberPostHolder = (MemberPostHolder) holder;
                memberPostHolder.memberTitle.setText(memberPost.getPost().getTitle());
                memberPostHolder.tag.setText(memberPost.getNode());
                memberPostHolder.memberName.setText(member.getUsername());
                memberPostHolder.memberLastReply.setText(memberPost.getLastReply().getUsername());
                memberPostHolder.time.setText(memberPost.getTimeAndLast());
            } else if (holder instanceof MemberReplyHolder) {
                MemberReply memberReply = memberReplies.get(position - memberPosts.size());
                MemberReplyHolder memberReplyHolder = (MemberReplyHolder) holder;
                memberReplyHolder.memberRepliedNode.setText(memberReply.repliedPost.getNode().getName());
                memberReplyHolder.memberRepliedCreatedName.setText(memberReply.repliedCreatedMember.getUsername());
                memberReplyHolder.memberRepliedTime.setText(memberReply.repliedTime);
                memberReplyHolder.memberRepliedTitle.setText(memberReply.repliedPost.getTitle());
                memberReplyHolder.memberReplyContent.setText(memberReply.repliedContent);
            }
        }

        @Override
        public int getItemCount() {
            return memberPosts.size() + memberReplies.size() + 1;
        }

        public void addReplyData(List<MemberReply> replies) {
            if (replies != null) {
                this.memberReplies.addAll(replies);
                notifyDataSetChanged();
            }
        }

        public void changeReplyData(List<MemberReply> replies) {
            if (replies != null) {
                this.memberReplies = replies;
                notifyDataSetChanged();
            }
        }

        public void addPostData(List<MemberPost> posts) {
            if (posts != null) {
                this.memberPosts.addAll(posts);
                notifyDataSetChanged();
            }
        }

        public void changePostData(List<MemberPost> posts) {
            if (posts != null) {
                this.memberPosts = posts;
                notifyDataSetChanged();
            }
        }


    }


}
