package org.newfort.sk.Skloora.Repositorys;

import org.mindrot.jbcrypt.BCrypt;
import org.newfort.sk.Skloora.DBPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.UUID;

@Repository
public class ForgotPasswordRepo {

    @Autowired
    private DBPool DBP;

    public String Forgot(String email)throws SQLException
    {
        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM skloora.user_data WHERE email = ?");
        pst.setString(1, email);

        ResultSet rs = pst.executeQuery();

            rs.next();

            String password =  UUID.randomUUID().toString();

            String hash = BCrypt.hashpw(password, BCrypt.gensalt());

            PreparedStatement pst2 = con.prepareStatement("UPDATE skloora.user_data SET password = ? WHERE email = ?");
            pst2.setString(1, hash);
            pst2.setString(2, email);

            int count = pst2.executeUpdate();

            if(count > 0)
            {
                rs.close();
                pst.close();
                pst2.close();
                con.close();

                return password;
            }
            else
            {
                rs.close();
                pst.close();
                pst2.close();
                con.close();

                return "Failed to change the password";
            }
        

}

}
