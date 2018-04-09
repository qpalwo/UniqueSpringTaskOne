package com.example.v2ex_client.model.Bean;

import java.io.Serializable;

public class MemberPost implements Serializable{


    Post post;

    String TimeAndLast;

    String Node;

    Member LastReply;


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


    public String getTimeAndLast() {
        return TimeAndLast;
    }

    public void setTimeAndLast(String timeAndLast) {
        TimeAndLast = timeAndLast;
    }

    public String getNode() {
        return Node;
    }

    public void setNode(String node) {
        Node = node;
    }

    public Member getLastReply() {
        return LastReply;
    }

    public void setLastReply(Member lastReply) {
        LastReply = lastReply;
    }
}
