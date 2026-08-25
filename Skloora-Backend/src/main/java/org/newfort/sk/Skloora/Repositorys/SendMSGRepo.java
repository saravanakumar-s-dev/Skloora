package org.newfort.sk.Skloora.Repositorys;

import org.newfort.sk.Skloora.DBPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SendMSGRepo {

    @Autowired
    private DBPool DBP;

    @Autowired
    private CookieRepo repo;

    public boolean SendMSG(String token, String id, String text) throws SQLException {


        String email = repo.TokenToEmail(token);

        Connection con = DBP.ds.getConnection();

        PreparedStatement pst2 = con.prepareStatement("SELECT * FROM skloora.msg_id WHERE msg_id = ?");
        pst2.setString(1, id);

        ResultSet rs2 = pst2.executeQuery();

        if (rs2.next()) {
            String getter = rs2.getString(1);

            PreparedStatement pst = con.prepareStatement("INSERT INTO skloora.msg VALUES(?,?,?)");
            pst.setString(1, email);
            pst.setString(2, getter);
            pst.setString(3, text);

            int count = pst.executeUpdate();

            pst.close();
            pst2.close();
            rs2.close();
            con.close();

            return count > 0;

        } else {
            return false;
        }

    }


}
