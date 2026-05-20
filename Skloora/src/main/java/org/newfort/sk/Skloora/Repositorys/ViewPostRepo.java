package org.newfort.sk.Skloora.Repositorys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.newfort.sk.Skloora.DBPool;
import org.newfort.sk.Skloora.Getters_Setters.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ViewPostRepo {

    @Autowired
    private DBPool DBP;

    public List<Post> getAllPost() throws SQLException
    {

        List<Post> list = new ArrayList<>();

        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM skloora.posts ORDER BY RANDOM()");
        ResultSet rs = pst.executeQuery();

        while(rs.next())
        {
            Post post = new Post();


            post.setPost_by(rs.getString(1));
            post.setPost_des(rs.getString(2));
            post.setPath(rs.getString(3));
            post.setPost_date(rs.getDate(4));

            String path = rs.getString(3).toLowerCase();

            if(path.endsWith(".png") ||
               path.endsWith(".jpg") ||
               path.endsWith(".jpeg") ||
               path.endsWith(".gif") ||
               path.endsWith(".webp"))
            {
                post.setMedia_type("img");
            }
            else
            {
                post.setMedia_type("video");
            }

            list.add(post);
        }

        rs.close();
        pst.close();
        con.close();


        return list;
        
    }

}
