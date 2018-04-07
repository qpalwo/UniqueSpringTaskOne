package com.example.v2ex_client.PostFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.v2ex_client.R;
import com.example.v2ex_client.base.BasePresent;
import com.example.v2ex_client.base.CallBack;
import com.example.v2ex_client.model.Bean.Post;
import com.example.v2ex_client.model.Bean.Reply;
import com.example.v2ex_client.model.JsoupUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 肖宇轩 on 2018/4/5.
 */

public class PostFraPresent extends BasePresent<PostView> {

    Post post;

    PostFraPresent(Post post) {
        this.post = post;
    }

    void initPostContent() {
        JsoupUtils jsoup = new JsoupUtils();
        if (isViewAttached()) {
            jsoup.getPostCheckedTimes(post.getUrl(), new CallBack<String>() {
                @Override
                public void onSuccess(String data) {
                    if (isViewAttached()) {
                        PostReplyAdapter postReplyAdapter = (PostReplyAdapter) getView().getPostRecycler().getAdapter();
                        postReplyAdapter.changeData(data);
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
    }

    void refreshData() {
        //刷新帖子内容
        initPostContent();
        JsoupUtils jsoup = new JsoupUtils();
        jsoup.getReplies(post.getUrl(), new CallBack<List<Reply>>() {
            @Override
            public void onSuccess(List<Reply> data) {
                //刷新回复列表
                if (isViewAttached()) {
                    PostReplyAdapter postReplyAdapter = (PostReplyAdapter) getView().getPostRecycler().getAdapter();
                    postReplyAdapter.changeData(data);
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

    void setAdapter() {
        RecyclerView recyclerView;
        if (isViewAttached()) {
            recyclerView = getView().getPostRecycler();
            recyclerView.setAdapter(new PostReplyAdapter(
                    getView().getContext(),
                    post
            ));
        }
    }

    private interface ItemOnClickListener {
        void onItemClick(View view, int position);
    }

    class PostReplyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Reply> replies;
        private Post post;
        private String checkedAndTime;
        private Context context;

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            @BindView(R.id.post_img)
            ImageView postImg;
            @BindView(R.id.user_name)
            TextView userName;
            @BindView(R.id.post_time)
            TextView postTime;
            @BindView(R.id.reply_content)
            TextView replyContent;
            @BindView(R.id.post_reply_number)
            TextView postReplyNumber;
            @BindView(R.id.reply_card)
            CardView replyCard;
            ItemOnClickListener itemOnClickListener;

            public ViewHolder(View itemView, ItemOnClickListener itemOnClickListener) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                this.itemOnClickListener = itemOnClickListener;
            }

            @Override
            @OnClick(R.id.user_name)
            public void onClick(View view) {
                itemOnClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        class PostDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ItemOnClickListener itemOnClickListener;
            @BindView(R.id.post_title)
            TextView postTitle;
            @BindView(R.id.user_name)
            TextView userName;
            @BindView(R.id.creat_time_checked)
            TextView creatTimeChecked;
            @BindView(R.id.user_img)
            ImageView userImg;
            @BindView(R.id.content)
            TextView content;

            public PostDetailViewHolder(View itemView, ItemOnClickListener itemOnClickListener) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                this.itemOnClickListener = itemOnClickListener;
            }

            @Override
            @OnClick({R.id.user_name, R.id.user_img})
            public void onClick(View view) {
                itemOnClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        PostReplyAdapter(Context context, Post post) {
            this.context = context;
            this.post = post;
            this.replies = new ArrayList<>();
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 0;//  帖子详内容
            } else {
                return 1;//  回复内容
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == 0) {
                View view = LayoutInflater.from(context).inflate
                        (R.layout.post_detail_item, parent, false);
                PostDetailViewHolder viewHolder = new PostDetailViewHolder(view, new ItemOnClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                });
                return viewHolder;

            } else {
                View view = LayoutInflater.from(context).inflate
                        (R.layout.reply_item, parent, false);
                ViewHolder viewHolder = new ViewHolder(view, new ItemOnClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                });
                return viewHolder;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ViewHolder) {
                ViewHolder viewHolder = (ViewHolder) holder;
                Reply reply = replies.get(position);
                if (reply != null) {
                    viewHolder.userName.setText(reply.getReplyMember().getUsername());
                    viewHolder.postTime.setText(reply.getTime());
                    viewHolder.postReplyNumber.setText(reply.getReplyNumber());
                    viewHolder.replyContent.setText(reply.getReplyContent());
                    Glide.with(context)
                            .load(reply.getReplyMember().getAvatar_normal())
                            .into(viewHolder.postImg);
                }
            } else if (holder instanceof PostDetailViewHolder) {
                PostDetailViewHolder viewHolder = (PostDetailViewHolder) holder;
                if (post != null && checkedAndTime != null) {
                    viewHolder.content.setText(Html.fromHtml(post.getContent_rendered()));
                    viewHolder.creatTimeChecked.setText(checkedAndTime);
                    viewHolder.postTitle.setText(post.getTitle());
                    viewHolder.userName.setText(post.getMember().getUsername());
                    Glide.with(context)
                            .load(post.getMember().getAvatar_normal())
                            .into(viewHolder.userImg);
                }
            }

        }

        @Override
        public int getItemCount() {
            return replies.size();
        }

        public void changeData(List<Reply> list) {
            if (list != null) {
                this.replies = list;
                notifyDataSetChanged();
            }
        }

        public void changeData(String checkedAndTime) {
            if (checkedAndTime != null) {
                this.checkedAndTime = checkedAndTime;
                notifyDataSetChanged();
            }
        }

        public void addData(List<Reply> replies) {
            if (replies != null) {
                this.replies.addAll(replies);
                notifyDataSetChanged();
            }
        }

    }
}
