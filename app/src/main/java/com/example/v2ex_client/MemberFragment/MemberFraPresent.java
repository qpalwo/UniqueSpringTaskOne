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
import com.example.v2ex_client.model.Bean.Member;
import com.example.v2ex_client.model.Bean.MemberPost;
import com.example.v2ex_client.model.DataUtil;

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

    MemberFraPresent(Member member){
        this.member = member;
    }

    void setAdapter(){
        RecyclerView recyclerView;
        if (isViewAttached()){
            recyclerView = getView().getMemberRecycler();
            recyclerView.setAdapter(new MemberRecyclerAdapter(
                    getView().getContext(),
                    member));
        }
    }

    private interface ItemOnClickListener {
        void onItemClick(View view, int position);
    }

    class MemberRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<MemberPost> memberPosts;

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

        MemberRecyclerAdapter(Context context, Member member) {
            this.context = context;
            this.member = member;
            memberPosts = new ArrayList<>();
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 0;//  用户信息
            } else {
                return 1;//  用户帖子
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

                    }
                });
                return memberDetailHolder;

            } else {
                View view = LayoutInflater.from(context).inflate
                        (R.layout.member_post_item, parent, false);
                MemberPostHolder memberPostHolder = new MemberPostHolder(view, new ItemOnClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (view.getId()) {
                            case R.id.member_title:
                                break;
                            case R.id.tag:
                                break;
                            case R.id.member_id:
                                break;
                            case R.id.member_last_reply:
                                break;
                        }
                    }
                });
                return memberPostHolder;

            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MemberDetailHolder){
                MemberDetailHolder memberDetailHolder = (MemberDetailHolder)holder;
                memberDetailHolder.memberName.setText(member.getUsername());
                memberDetailHolder.memberNo.setText("V2EX第" + member.getId() + "号会员");
                memberDetailHolder.memberTime.setText(DataUtil.convertTimeToFormat(member.getCreated()));
                Glide.with(context)
                        .load(member.getAvatar_normal())
                        .into(memberDetailHolder.memberImg);
            } else if (holder instanceof MemberPostHolder){
                MemberPost memberPost = memberPosts.get(position);
                MemberPostHolder memberPostHolder = (MemberPostHolder)holder;
                memberPostHolder.memberTitle.setText(memberPost.getTitle());
                memberPostHolder.tag.setText(memberPost.getNode());
                memberPostHolder.memberName.setText(member.getUsername());
                memberPostHolder.memberLastReply.setText(memberPost.getLastReply());
                memberPostHolder.time.setText(memberPost.getTimeAndLast());
            }
        }

        @Override
        public int getItemCount() {
            return memberPosts.size() + 1;
        }


    }


}
