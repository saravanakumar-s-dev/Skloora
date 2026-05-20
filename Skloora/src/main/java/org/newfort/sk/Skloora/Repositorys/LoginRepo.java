package org.newfort.sk.Skloora.Repositorys;

import org.mindrot.jbcrypt.BCrypt;
import org.newfort.sk.Skloora.DBPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class LoginRepo 
{

    @Autowired
    private DBPool DBP;



    public String Login(String email, String password)throws SQLException
    {

        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM skloora.user_data WHERE email = ?");
        pst.setString(1, email);

        ResultSet rs = pst.executeQuery();

        if(rs.next())
        {
            String stored_hash = rs.getString("password");

            if(BCrypt.checkpw(password, stored_hash))
            {
                rs.close();
                pst.close();
                con.close();

                return "Success";
            }
            else
            {
                 rs.close();
                pst.close();
                con.close();

                return "Wrong password";
            }
        }
        else
        { 
                rs.close();
                pst.close();
                con.close();

             return "no user found";
        }

    }

}
