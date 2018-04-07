package com.example.v2ex_client.model.Bean;

import java.io.Serializable;

/**
 * Created by 肖宇轩 on 2018/4/3.
 */

public class Post implements Serializable{


    /**
     * id : 444082
     * title : iPhoneX 用了才不到一周突然就 GG 了，无法冲电。
     * url : http://www.v2ex.com/t/444082
     * content : 下午出门记得电量还不少，中间拿出来用突然感觉很卡顿，一看电量只有有 1%了。
     插上交流电源屏幕一直显示“还剩 0 ”，右上角没有充电标识，一直显示 1%电量。换了 N 个线和电源无果，Reset 无果，搜索 google 有些人有同样状况，但是都没办法。
     国行，256，看来只能换机了。
     从 3gs 用 IOS 到现在，第二次碰到 iPhone 没用几天就出问题。都是国行。
     * content_rendered : 下午出门记得电量还不少，中间拿出来用突然感觉很卡顿，一看电量只有有 1%了。<br />插上交流电源屏幕一直显示“还剩 0 ”，右上角没有充电标识，一直显示 1%电量。换了 N 个线和电源无果，Reset 无果，搜索 google 有些人有同样状况，但是都没办法。<br />国行，256，看来只能换机了。<br />从 3gs 用 IOS 到现在，第二次碰到 iPhone 没用几天就出问题。都是国行。
     * replies : 0
     * member : {"id":74142,"username":"shywings","tagline":"","avatar_mini":"//cdn.v2ex.com/gravatar/d4d331e800d4481094e02e0830f9d093?s=24&d=retro","avatar_normal":"//cdn.v2ex.com/gravatar/d4d331e800d4481094e02e0830f9d093?s=48&d=retro","avatar_large":"//cdn.v2ex.com/gravatar/d4d331e800d4481094e02e0830f9d093?s=73&d=retro"}
     * node : {"id":8,"name":"iphone","title":"iPhone","title_alternative":"iPhone","url":"http://www.v2ex.com/go/iphone","topics":5230,"avatar_mini":"//cdn.v2ex.com/navatar/c9f0/f895/8_mini.png?m=1517229863","avatar_normal":"//cdn.v2ex.com/navatar/c9f0/f895/8_normal.png?m=1517229863","avatar_large":"//cdn.v2ex.com/navatar/c9f0/f895/8_large.png?m=1517229863"}
     * created : 1522760675
     * last_modified : 1522760675
     * last_touched : 1522760495
     */

    private String id;
    private String title;
    private String url;
    private String content;
    private String content_rendered;
    private int replies;
    private Member member;
    private Node node;
    private int created;
    private int last_modified;
    private int last_touched;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_rendered() {
        return content_rendered;
    }

    public void setContent_rendered(String content_rendered) {
        this.content_rendered = content_rendered;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(int last_modified) {
        this.last_modified = last_modified;
    }

    public int getLast_touched() {
        return last_touched;
    }

    public void setLast_touched(int last_touched) {
        this.last_touched = last_touched;
    }


}
