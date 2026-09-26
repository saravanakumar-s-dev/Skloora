package org.newfort.sk.Skloora.Repository;

import org.newfort.sk.Skloora.DBPool;
import org.newfort.sk.Skloora.Model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepo {

    @Autowired
    private DBPool DBP;


    // =========================================
    // ADD COMMENT
    // =========================================

    public void addComment(
            String post_by,
            String post_des,
            java.sql.Date post_date,
            String comment_by,
            String comment_text
    ) throws SQLException {

        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement(
                """
                INSERT INTO skloora.comments
                (
                    post_by,
                    post_des,
                    post_date,
                    comment_by,
                    comment_text
                )
                VALUES (?, ?, ?, ?, ?)
                """
        );

        pst.setString(1, post_by);
        pst.setString(2, post_des);
        pst.setDate(3, post_date);
        pst.setString(4, comment_by);
        pst.setString(5, comment_text);

        pst.executeUpdate();

        pst.close();
        con.close();
    }


    // =========================================
    // GET COMMENTS
    // =========================================

    public List<Comment> getComments(
            String post_by,
            String post_des,
            java.sql.Date post_date
    ) throws SQLException {

        List<Comment> comments = new ArrayList<>();

        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement(
                """
                SELECT
                    comment_id,
                    comment_by,
                    comment_text,
                    comment_date
                FROM skloora.comments
                WHERE post_by = ?
                  AND post_des = ?
                  AND post_date = ?
                ORDER BY comment_date ASC
                """
        );

        pst.setString(1, post_by);
        pst.setString(2, post_des);
        pst.setDate(3, post_date);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Comment comment = new Comment();

            comment.setComment_id(
                    rs.getLong("comment_id")
            );

            comment.setComment_by(
                    rs.getString("comment_by")
            );

            comment.setComment_text(
                    rs.getString("comment_text")
            );

            comment.setComment_date(
                    rs.getTimestamp("comment_date")
            );

            comments.add(comment);
        }

        rs.close();
        pst.close();
        con.close();

        return comments;
    }

}