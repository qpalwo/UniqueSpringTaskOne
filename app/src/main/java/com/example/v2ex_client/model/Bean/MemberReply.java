package com.example.v2ex_client.model.Bean;

import java.io.Serializable;

public class MemberReply implements Serializable {
    public Post repliedPost;

    public Member repliedCreatedMember;

    public String repliedContent;

    public String repliedTime;

    public Post getRepliedPost() {
        return repliedPost;
    }

    public void setRepliedPost(Post repliedPost) {
        this.repliedPost = repliedPost;
    }

    public Member getRepliedCreatedMember() {
        return repliedCreatedMember;
    }

    public void setRepliedCreatedMember(Member repliedCreatedMember) {
        this.repliedCreatedMember = repliedCreatedMember;
    }

    public String getRepliedContent() {
        return repliedContent;
    }

    public void setRepliedContent(String repliedContent) {
        this.repliedContent = repliedContent;
    }

    public String getRepliedTime() {
        return repliedTime;
    }

    public void setRepliedTime(String repliedTime) {
        this.repliedTime = repliedTime;
    }
}
