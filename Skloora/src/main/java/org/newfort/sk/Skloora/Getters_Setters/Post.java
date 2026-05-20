package org.newfort.sk.Skloora.Getters_Setters;

import java.sql.Date;

public class Post {
    
    private String post_by;
    private String post_des;
    private String path;
    private String media_type;
    private Date post_date;


    public String getPost_by() {
        return post_by;
    }
    public void setPost_by(String post_by) {
        this.post_by = post_by;
    }
    public String getPost_des() {
        return post_des;
    }
    public void setPost_des(String post_des) {
        this.post_des = post_des;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getMedia_type() {
        return media_type;
    }
    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }
    public Date getPost_date() {
        return post_date;
    }
    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

}
