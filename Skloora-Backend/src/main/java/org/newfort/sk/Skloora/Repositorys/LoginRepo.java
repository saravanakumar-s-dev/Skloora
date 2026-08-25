package org.newfort.sk.Skloora.Repositorys;

import org.mindrot.jbcrypt.BCrypt;
import org.newfort.sk.Skloora.DBPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class LoginRepo {

    @Autowired
    private DBPool DBP;


    public String Login(String email, String password) throws SQLException {

        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement(
                "SELECT * FROM skloora.user_data WHERE email=?");
        pst.setString(1, email);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            String storedHash = rs.getString("password");

            if (BCrypt.checkpw(password, storedHash)) {

                String token = UUID.randomUUID().toString();

                PreparedStatement update = con.prepareStatement(
                        "UPDATE skloora.user_data SET login_token=? WHERE email=?");

                update.setString(1, token);
                update.setString(2, email);

                update.executeUpdate();

                PreparedStatement pst3 = con.prepareStatement(
                        "INSERT INTO skloora.pendingmails VALUES(?,?,?)");

                pst3.setString(1, email);
                pst3.setString(2, "Login");
                pst3.setString(3, "Pending");

                pst3.executeUpdate();

                update.close();
                pst3.close();
                rs.close();
                pst.close();
                con.close();

                return token;
            }

            rs.close();
            pst.close();
            con.close();

            return "Wrong password";
        }

        rs.close();
        pst.close();
        con.close();

        return "no user found";
    }


}
