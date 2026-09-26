<<<<<<< HEAD
package org.newfort.sk.Skloora.Service;

import org.newfort.sk.Skloora.Model.Comment;
import org.newfort.sk.Skloora.Repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;


    // =========================================
    // ADD COMMENT
    // =========================================

    public void addComment(
            String post_by,
            String post_des,
            Date post_date,
            String comment_by,
            String comment_text
    ) throws SQLException {

        commentRepo.addComment(
                post_by,
                post_des,
                post_date,
                comment_by,
                comment_text
        );
    }


    // =========================================
    // GET COMMENTS
    // =========================================

    public List<Comment> getComments(
            String post_by,
            String post_des,
            Date post_date
    ) throws SQLException {

        return commentRepo.getComments(
                post_by,
                post_des,
                post_date
        );
    }
=======
package org.newfort.sk.Skloora.Service;

import org.newfort.sk.Skloora.Model.Comment;
import org.newfort.sk.Skloora.Repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;


    // =========================================
    // ADD COMMENT
    // =========================================

    public void addComment(
            String post_by,
            String post_des,
            Date post_date,
            String comment_by,
            String comment_text
    ) throws SQLException {

        commentRepo.addComment(
                post_by,
                post_des,
                post_date,
                comment_by,
                comment_text
        );
    }


    // =========================================
    // GET COMMENTS
    // =========================================

    public List<Comment> getComments(
            String post_by,
            String post_des,
            Date post_date
    ) throws SQLException {

        return commentRepo.getComments(
                post_by,
                post_des,
                post_date
        );
    }
>>>>>>> 43f612d06bc51106f5a6a8b8cdcc7ce5570bc9ec
}