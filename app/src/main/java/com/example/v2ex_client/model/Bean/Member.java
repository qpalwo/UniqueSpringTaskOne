package com.example.v2ex_client.model.Bean;

import java.io.Serializable;

/**
 * Created by 肖宇轩 on 2018/4/3.
 */

public class Member implements Serializable{

    /**
     * status : found
     * id : 1
     * url : http://www.v2ex.com/member/Livid
     * username : Livid
     * website :
     * twitter :
     * psn :
     * github :
     * btc :
     * location :
     * tagline : Gravitated and spellbound
     * bio : Remember the bigger green
     * avatar_mini : //cdn.v2ex.com/avatar/c4ca/4238/1_mini.png?m=1466415272
     * avatar_normal : //cdn.v2ex.com/avatar/c4ca/4238/1_normal.png?m=1466415272
     * avatar_large : //cdn.v2ex.com/avatar/c4ca/4238/1_large.png?m=1466415272
     * created : 1272203146
     */

    private int id;
    private String url;
    private String username;
    private String website;
    private String github;
    private String location;
    private String tagline;
    private String bio;
    private String avatar_mini;
    private String avatar_normal;
    private String avatar_large;
    private int created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }
}
