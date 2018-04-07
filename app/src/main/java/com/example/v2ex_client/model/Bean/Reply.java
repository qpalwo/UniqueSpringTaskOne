package com.example.v2ex_client.model.Bean;

import java.io.Serializable;

/**
 * Created by 肖宇轩 on 2018/4/5.
 */

public class Reply implements Serializable{
    Member replyMember;

    String replyContent;

    String time;

    String replyNumber;

    public String getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(String replyNumber) {
        this.replyNumber = replyNumber;
    }



    public Member getReplyMember() {
        return replyMember;
    }

    public void setReplyMember(Member replyMember) {
        this.replyMember = replyMember;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }




}
