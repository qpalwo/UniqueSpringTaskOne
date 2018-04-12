package com.example.v2ex_client.ListFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.v2ex_client.R;
import com.example.v2ex_client.base.BasePresent;
import com.example.v2ex_client.base.CallBack;
import com.example.v2ex_client.model.Bean.Member;
import com.example.v2ex_client.model.Bean.Node;
import com.example.v2ex_client.model.Bean.Post;
import com.example.v2ex_client.model.DataUtil;
import com.example.v2ex_client.model.HttpConnectionUtils;
import com.example.v2ex_client.model.JsoupUtils;
import com.example.v2ex_client.model.ResponseHandle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 肖宇轩 on 2018/4/3.
 */

class ListFraPresent extends BasePresent<ListView> {

    private List<Node> nodes;

    void setAdapter() {
        PostItemAdapter postItemAdapter = new PostItemAdapter(
                getView().getContext(),
                new ArrayList<Post>());
        getView().getListRecyclerView().setAdapter(postItemAdapter);
    }

    void refreshList(String type) {
        String requestAddress = null;
        if (type.equals("hot_post")) {
            requestAddress = HttpConnectionUtils.V2EX_HOT;
        } else if (type.equals("latest_post")) {
            requestAddress = HttpConnectionUtils.V2EX_LATEST;
        }
        HttpConnectionUtils.getResponse(
                "GET",
                null,
                requestAddress,
                new CallBack<String>() {
                    String data;

                    @Override
                    public void onSuccess(String data) {
                        this.data = data;
                    }

                    @Override
                    public void onFailure(String msg) {
                        //getView().showToast(msg);
                        getView().getRefreshLayout().finishRefresh(false);
                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onComplete() {
                        ResponseHandle.postHandler(data,
                                new CallBack<List<Post>>() {
                                    @Override
                                    public void onSuccess(List<Post> data) {
                                        PostItemAdapter postItemAdapter = (PostItemAdapter) getView().getListRecyclerView().getAdapter();
                                        postItemAdapter.changeData(data);
                                    }

                                    @Override
                                    public void onFailure(String msg) {
                                        getView().getRefreshLayout().finishRefresh(false);
                                    }

                                    @Override
                                    public void onError() {

                                    }

                                    @Override
                                    public void onComplete() {
                                        getView().getRefreshLayout().finishRefresh();
                                    }
                                });
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

    private void detailMember(final Member member) {
        JsoupUtils jsoupUtils = new JsoupUtils();
        jsoupUtils.getMemberInfo(member.getUsername(), new CallBack<Member>() {
            @Override
            public void onSuccess(Member data) {
                addMemberFragment(data);
            }

            @Override
            public void onFailure(String msg) {
                getView().showToast(msg);
            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private class NodeItemAdapter extends RecyclerView.Adapter<NodeItemAdapter.ViewHolder> {

        ItemOnClickListener itemOnClickListener;

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ItemOnClickListener itemOnClickListener;
            TextView node_text;
            CardView node_card;

            public ViewHolder(View itemView, ItemOnClickListener itemOnClickListener) {
                super(itemView);
                this.itemOnClickListener = itemOnClickListener;
                node_card = (CardView) itemView;
                node_text = (TextView) itemView.findViewById(R.id.node_text);
            }

            @Override
            public void onClick(View view) {
                if (itemOnClickListener != null) {
                    itemOnClickListener.onItemClick(view, getAdapterPosition());
                }
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getView().getContext()).inflate
                    (R.layout.node_item, parent, false);
            return new ViewHolder(view, itemOnClickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Node node = nodes.get(position);
            holder.node_text.setText(node.getName());
        }

        @Override
        public int getItemCount() {
            if (nodes == null) {
                return 0;
            } else {
                return nodes.size() + 1;
            }
        }


    }

    public class PostItemAdapter extends RecyclerView.Adapter<PostItemAdapter.ViewHolder> {

        private List<Post> posts;
        private Context context;

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ItemOnClickListener itemOnClickListener;

            @BindView(R.id.post_img)
            ImageView postImg;
            @BindView(R.id.post_title)
            TextView postTitle;
            @BindView(R.id.post_lab)
            Button postLab;
            @BindView(R.id.post_author)
            TextView postAuthor;
            @BindView(R.id.post_time)
            TextView postTime;
            @BindView(R.id.post_last_reply)
            TextView postLastReply;
            @BindView(R.id.post_reply_number)
            TextView postReplyNumber;
            @BindView(R.id.post_item)
            CardView postItem;

            public ViewHolder(View itemView, ItemOnClickListener itemOnClickListener) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                this.itemOnClickListener = itemOnClickListener;
            }

            @Override
            @OnClick({R.id.post_title, R.id.post_lab, R.id.post_author, R.id.post_last_reply, R.id.post_reply_number})
            public void onClick(View view) {
                if (itemOnClickListener != null) {
                    itemOnClickListener.onItemClick(view, getAdapterPosition());
                }
            }
        }

        PostItemAdapter(Context context, List<Post> posts) {
            this.context = context;
            this.posts = posts;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate
                    (R.layout.post_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(view, new ItemOnClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    switch (view.getId()) {
                        case R.id.post_title:
                            addPostFragment(posts.get(position));
                            break;
                        case R.id.post_lab:
                            break;
                        case R.id.post_author:
                            detailMember(posts.get(position).getMember());
                            break;
                        case R.id.post_last_reply:
                           // addMemberFragment();
                            break;
                        case R.id.post_reply_number:
                            addPostFragment(posts.get(position));
                            break;
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Post post = posts.get(position);
            holder.postTitle.setText(post.getTitle());
            holder.postAuthor.setText(post.getMember().getUsername());
            holder.postLab.setText(post.getNode().getName());
            holder.postReplyNumber.setText(post.getReplies() + "");
            holder.postTime.setText(DataUtil.convertTimeToFormat(post.getLast_touched()));

            Glide.with(getView().getContext())
                    .load("https:" + post.getMember().getAvatar_large())
                    .into(holder.postImg);
        }

        @Override
        public int getItemCount() {
            if (posts == null) {
                return 0;
            } else {
                return posts.size();
            }
        }

        public void changeData(List<Post> posts) {
            this.posts = posts;
            notifyDataSetChanged();
        }

        public void addData(List<Post> posts) {
            this.posts.addAll(posts);
            notifyDataSetChanged();
        }

    }

}
