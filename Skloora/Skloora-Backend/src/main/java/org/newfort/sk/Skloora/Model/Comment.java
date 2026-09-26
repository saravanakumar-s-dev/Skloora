package org.newfort.sk.Skloora.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private long comment_id;

    private String post_by;

    private String post_des;

    private Date post_date;

    private String comment_by;

    private String comment_text;

    private Timestamp comment_date;
}