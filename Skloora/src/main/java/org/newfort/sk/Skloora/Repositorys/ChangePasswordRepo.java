package org.newfort.sk.Skloora.Repositorys;

import org.mindrot.jbcrypt.BCrypt;
import org.newfort.sk.Skloora.DBPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ChangePasswordRepo {

    @Autowired
    private DBPool DBP;

    public boolean ChangePassword(String email, String password) throws SQLException
    {
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement("UPDATE skloora.user_data SET password = ? WHERE email = ?");
        pst.setString(1, hash);
        pst.setString(2, email);

        int count = pst.executeUpdate();

        pst.close();
        con.close();
        
        return count > 0;
    }

}
