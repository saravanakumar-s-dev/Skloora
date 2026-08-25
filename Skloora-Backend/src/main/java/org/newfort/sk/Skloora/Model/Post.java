package org.newfort.sk.Skloora.Model;

import lombok.Data;

import java.sql.Date;

@Data
public class Post {

    private String post_by;
    private String post_des;
    private String path;
    private String media_type;
    private Date post_date;

}
