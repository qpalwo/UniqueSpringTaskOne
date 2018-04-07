package com.example.v2ex_client.model.Bean;

import java.io.Serializable;

/**
 * Created by 肖宇轩 on 2018/4/3.
 */

public class Node implements Serializable{
    /**
     * id : 90
     * name : python
     * url : http://www.v2ex.com/go/python
     * title : Python
     * title_alternative : Python
     * topics : 8901
     * stars : 6073
     * header : 这里讨论各种 Python 语言编程话题，也包括 Django，Tornado 等框架的讨论。这里是一个能够帮助你解决实际问题的地方。
     * footer : null
     * created : 1278683336
     * avatar_mini : //cdn.v2ex.com/navatar/8613/985e/90_mini.png?m=1519290047
     * avatar_normal : //cdn.v2ex.com/navatar/8613/985e/90_normal.png?m=1519290047
     * avatar_large : //cdn.v2ex.com/navatar/8613/985e/90_large.png?m=1519290047
     */

    private int id;
    private String name;
    private String url;
    private String title;
    private String title_alternative;
    private int topics;
    private int stars;
    private String header;
    private String footer;
    private int created;
    private String avatar_mini;
    private String avatar_normal;
    private String avatar_large;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_alternative() {
        return title_alternative;
    }

    public void setTitle_alternative(String title_alternative) {
        this.title_alternative = title_alternative;
    }

    public int getTopics() {
        return topics;
    }

    public void setTopics(int topics) {
        this.topics = topics;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Object getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getAvatar_mini() {
        return avatar_mini;
    }

    public void setAvatar_mini(String avatar_mini) {
        this.avatar_mini = avatar_mini;
    }

    public String getAvatar_normal() {
        return avatar_normal;
    }

    public void setAvatar_normal(String avatar_normal) {
        this.avatar_normal = avatar_normal;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
    }
}
