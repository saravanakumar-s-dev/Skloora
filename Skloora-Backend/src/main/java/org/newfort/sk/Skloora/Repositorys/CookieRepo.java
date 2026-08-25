package org.newfort.sk.Skloora.Repositorys;

import org.newfort.sk.Skloora.DBPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CookieRepo {

    @Autowired
    private DBPool DBP;

    public boolean isValidToken(String token) throws SQLException {

        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement(
                "SELECT email FROM skloora.user_data WHERE login_token = ?");

        pst.setString(1, token);

        ResultSet rs = pst.executeQuery();

        boolean found = rs.next();

        rs.close();
        pst.close();
        con.close();

        return found;
    }

    public void Logout(String token) throws SQLException {

        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement(
                "UPDATE skloora.user_data SET login_token = NULL WHERE login_token = ?");

        pst.setString(1, token);

        pst.executeUpdate();

        pst.close();
        con.close();
    }

    public String TokenToEmail(String token) throws SQLException {

        Connection con = DBP.ds.getConnection();
        PreparedStatement pst = con.prepareStatement(
                "SELECT email FROM skloora.user_data WHERE login_token = ?");
        pst.setString(1, token);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String email = rs.getString("email");

            con.close();
            pst.close();
            rs.close();

            return email;

        } else {


            con.close();
            pst.close();
            rs.close();

            return "No email found for the email";
        }
    }

}
