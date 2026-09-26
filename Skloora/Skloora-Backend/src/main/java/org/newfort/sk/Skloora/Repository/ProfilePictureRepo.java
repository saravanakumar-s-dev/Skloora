package org.newfort.sk.Skloora.Repository;

import org.newfort.sk.Skloora.DBPool;
import org.newfort.sk.Skloora.Utility.ProfileMediaFileSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ProfilePictureRepo {

    @Autowired
    private DBPool pool;

    @Autowired
    private ProfileMediaFileSave fs;


    public boolean SavePic(String email, MultipartFile file) throws IOException, SQLException {

        String location = fs.Save(file);

        Connection con = pool.ds.getConnection();

        PreparedStatement pst = con.prepareStatement("UPDATE skloora.profile SET dp_location = ? WHERE email = ?");
        pst.setString(1, location);
        pst.setString(2, email);

        int count = pst.executeUpdate();

        return count > 0;
    }

}
