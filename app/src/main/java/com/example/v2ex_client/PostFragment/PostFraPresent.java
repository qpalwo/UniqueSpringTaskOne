package com.example.v2ex_client.PostFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
import com.example.v2ex_client.model.Bean.Post;
import com.example.v2ex_client.model.Bean.Reply;
import com.example.v2ex_client.model.DataUtil;
import com.example.v2ex_client.model.JsoupUtils;

import org.jsoup.Jsoup;

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
            getView().initPostContent(
                    "temp",
                    DataUtil.timeStampToStr(post.getCreated()));
//            jsoup.getPostCheckedTimes(post.getUrl(), new CallBack<String>() {
//                String checked = null;
//                @Override
//                public void onSuccess(String data) {
//                    checked = data;
//                }
//
//                @Override
//                public void onFailure(String msg) {
//
//                }
//
//                @Override
//                public void onError() {
//
//                }
//
//                @Override
//                public void onComplete() {
//                    getView().initPostContent(
//                            checked,
//                            DataUtil.timeStampToStr(post.getCreated()));
//                }
//            });
        }
    }

    void refreshData() {
        JsoupUtils jsoup = new JsoupUtils();
        jsoup.getReplies(post.getUrl(), new CallBack<List<Reply>>() {
            @Override
            public void onSuccess(List<Reply> data) {
                //刷新帖子内容
                initPostContent();
                //刷新回复列表
                PostReplyAdapter postReplyAdapter = (PostReplyAdapter) getView().getPostRecycler().getAdapter();
                postReplyAdapter.changeData(data);
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
                    getView().getContext()//,
                  //  new ArrayList<Reply>()
            ));
        }
    }

    private interface ItemOnClickListener {
        void onItemClick(View view, int position);
    }

    class PostReplyAdapter extends RecyclerView.Adapter<PostReplyAdapter.ViewHolder> {

        private List<Reply> replies;
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

        PostReplyAdapter(Context context) {
            this.context = context;
            this.replies = new ArrayList<>();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate
                    (R.layout.reply_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(view, new ItemOnClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Reply reply = replies.get(position);
            holder.userName.setText(reply.getReplyMember().getUsername());
            holder.postTime.setText(reply.getTime());
            holder.postReplyNumber.setText(reply.getReplyNumber());
            holder.replyContent.setText(reply.getReplyContent());
            Glide.with(context)
                    .load(reply.getReplyMember().getAvatar_normal())
                    .into(holder.postImg);
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

        public void addData(List<Reply> replies) {
            if (replies != null) {
                this.replies.addAll(replies);
                notifyDataSetChanged();
            }
        }

    }
}
