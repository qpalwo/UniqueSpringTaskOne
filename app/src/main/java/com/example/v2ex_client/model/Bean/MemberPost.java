package com.example.v2ex_client.model.Bean;

import java.io.Serializable;

public class MemberPost implements Serializable{

    String Title;

    String TimeAndLast;

    String Node;

    String LastReply;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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

    public String getLastReply() {
        return LastReply;
    }

    public void setLastReply(String lastReply) {
        LastReply = lastReply;
    }
}
