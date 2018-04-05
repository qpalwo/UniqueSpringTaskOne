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
import com.example.v2ex_client.model.Bean.Reply;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 肖宇轩 on 2018/4/5.
 */

public class PostFraPresent extends BasePresent<PostView> {

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

        PostReplyAdapter(Context context, List<Reply> replies) {
            this.context = context;
            this.replies = replies;
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


    }
}
