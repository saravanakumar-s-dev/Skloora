package org.newfort.sk.Skloora.Repositorys;

import org.newfort.sk.Skloora.DBPool;
import org.newfort.sk.Skloora.Utility.FileSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class CreatePostRepo {

    @Autowired
    private DBPool DBP;

    @Autowired
    private FileSave fs;

    @Autowired
    private CookieRepo repo;

    public boolean CreatePost(String token, String post_des, MultipartFile file) throws SQLException, IOException {

        String email = repo.TokenToEmail(token);

        Connection con = DBP.ds.getConnection();

        String location = fs.Save(file);

        PreparedStatement pst = con.prepareStatement("INSERT INTO skloora.posts VALUES(?,?,?)");
        pst.setString(1, email);
        pst.setString(2, post_des);
        pst.setString(3, location);

        int count = pst.executeUpdate();

        pst.close();
        con.close();

        return count > 0;

    }

}
